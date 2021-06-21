package dadm.scaffold.counter;

import android.content.Context;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import dadm.scaffold.BaseFragment;
import dadm.scaffold.R;
import dadm.scaffold.ScaffoldActivity;
import dadm.scaffold.engine.FramesPerSecondCounter;
import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.engine.GameView;
import dadm.scaffold.input.JoystickInputController;
import dadm.scaffold.space.GameController;
import dadm.scaffold.space.SpaceShipPlayer;




public class GameFragment extends BaseFragment implements View.OnClickListener {
    private GameEngine theGameEngine;
    public static ImageView[] vidas = new ImageView[5];
    public static int vidasTotales;
    int naveID;
    private static TextView scoreText;
    public static int puntuacion;
    private static GameFragment.FragScoreListener scoreListener;
    public static Button btn_pause;
    private static int dificultad;


    public GameFragment() {
    }

    public interface FragScoreListener{
        void onScoreListener(int score);
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //naveID = 1;
        puntuacion = 0;
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vidas[0] = (ImageView) view.findViewById(R.id.vida1);
        vidas[1] = (ImageView) view.findViewById(R.id.vida2);
        vidas[2] = (ImageView) view.findViewById(R.id.vida3);
        vidas[3] = (ImageView) view.findViewById(R.id.vida4);
        vidas[4] = (ImageView) view.findViewById(R.id.vida5);
        scoreText = (TextView) view.findViewById(R.id.txt_Score);
        puntuacion = 0;
        btn_pause = (Button) view.findViewById(R.id.btn_play_pause);
        view.findViewById(R.id.btn_play_pause).setOnClickListener(this);

        final ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
            @Override
            public void onGlobalLayout(){
                //Para evitar que sea llamado múltiples veces,
                //se elimina el listener en cuanto es llamado
                observer.removeOnGlobalLayoutListener(this);
                GameView gameView = (GameView) getView().findViewById(R.id.gameView);
                theGameEngine = new GameEngine(getActivity(), gameView);
                theGameEngine.setSoundManager(getScaffoldActivity().getSoundManager());
                theGameEngine.setTheInputController(new JoystickInputController(getView()));
                theGameEngine.addGameObject(new SpaceShipPlayer(theGameEngine, naveID));
                theGameEngine.addGameObject(new FramesPerSecondCounter(theGameEngine));
                theGameEngine.addGameObject(new GameController(theGameEngine));
                theGameEngine.startGame();
            }
        });


    }

    public void setValores(int numVidas, int nave){
        System.out.println("He entrado" + numVidas + "-" + nave);
        vidasTotales = numVidas;
        for(int i = 0; i < numVidas; i++){
            vidas[i].setVisibility(View.VISIBLE);
        }
        switch (nave){
            case 1:
                naveID = R.drawable.nave_azul;
                break;

            case 2:
                naveID = R.drawable.nave_roja;
                break;

            case 3:
                naveID = R.drawable.nave_verde;
                break;
        }

        dificultad = numVidas;

        scoreText.setText("Puntuación: " + puntuacion);
    }

    public static void ganarPuntos(){
        switch (dificultad){
            case 1:
                puntuacion += 100;
                break;
            case 3:
                puntuacion += 200;
                break;
            case 5:
                puntuacion += 300;
                break;
        }
        scoreListener.onScoreListener(puntuacion);
        scoreText.setText("Puntuación: " + puntuacion);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_play_pause) {
            pauseGameAndShowPauseDialog();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (theGameEngine.isRunning() && vidasTotales > 0){
            pauseGameAndShowPauseDialog();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        theGameEngine.stopGame();
    }

    @Override
    public boolean onBackPressed() {
        if (theGameEngine.isRunning()) {
            pauseGameAndShowPauseDialog();
            return true;
        }
        return false;
    }

    public void onUpdate(){
        if(ScaffoldActivity.numVidas <= 0){
            onBackPressed();
        }
    }

    private void pauseGameAndShowPauseDialog() {
        theGameEngine.pauseGame();
        if(ScaffoldActivity.numVidas != 0){
            new AlertDialog.Builder(getActivity())
                    .setTitle("Juego pausado")
                    .setMessage("¿Volver al juego?")
                    .setPositiveButton("Volver al juego", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            theGameEngine.resumeGame();
                        }
                    })
                    .setNegativeButton("Volver al menu principal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            theGameEngine.stopGame();
                            ((ScaffoldActivity)getActivity()).navigateBack();
                        }
                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            theGameEngine.resumeGame();
                        }
                    })
                    .create()
                    .show();
        }else{
            new AlertDialog.Builder(getActivity())
                    .setTitle("Has perdido")
                    .setPositiveButton("Salir al ranking", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            theGameEngine.stopGame();
                            ((ScaffoldActivity)getActivity()).Ranking();
                        }
                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            //((ScaffoldActivity)getActivity()).Ranking();
                        }
                    })
                    .create()
                    .show();
        }




    }

    private void playOrPause() {
        Button button = (Button) getView().findViewById(R.id.btn_play_pause);
        if (theGameEngine.isPaused()) {
            theGameEngine.resumeGame();
            button.setText(R.string.pause);
        }
        else {
            theGameEngine.pauseGame();
            button.setText(R.string.resume);
        }
    }

    public static void perderVida(){
        vidas[ScaffoldActivity.numVidas-1].setVisibility(View.INVISIBLE);
        vidasTotales--;
        if(vidasTotales <= 0){
            scoreText.setText("Pulsa el boton de pausa para salir");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof GameFragment.FragScoreListener){
            scoreListener = (GameFragment.FragScoreListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        scoreListener = null;
    }
}
