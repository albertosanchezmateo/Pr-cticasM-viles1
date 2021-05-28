package dadm.scaffold.counter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import dadm.scaffold.BaseFragment;
import dadm.scaffold.R;
import dadm.scaffold.ScaffoldActivity;


public class ScoreFragment extends BaseFragment implements View.OnClickListener {


    private TextView scoreText;
    private int scoreTotal = 0;
    private Button btn_menu;

    public ScoreFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scoreText = (TextView) view.findViewById(R.id.txt_score);
        btn_menu = (Button) view.findViewById(R.id.btn_volverAlMenu);
        scoreTotal = 0;
        btn_menu.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        ((ScaffoldActivity) getActivity()).navigateToFragment(new MainMenuFragment());
    }

    public void ponerPuntuacion(int score){
        scoreTotal = score;
        scoreText.setText("¡HAS PERDIDO! Has conseguido " + scoreTotal + " puntos");
    }
}