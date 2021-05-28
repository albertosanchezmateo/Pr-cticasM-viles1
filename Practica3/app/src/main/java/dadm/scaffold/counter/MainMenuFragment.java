package dadm.scaffold.counter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import dadm.scaffold.BaseFragment;
import dadm.scaffold.R;
import dadm.scaffold.ScaffoldActivity;


public class MainMenuFragment extends BaseFragment implements View.OnClickListener {

    private MainMenuFragment.FragMenuListener listener;

    private boolean naveElegida;

    public MainMenuFragment() {
    }

    public interface FragMenuListener{
        void onVidaListener(int numVidas);
        void onNaveListener(int nave);

    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_easy).setOnClickListener(this);
        view.findViewById(R.id.btn_medium).setOnClickListener(this);
        view.findViewById(R.id.btn_hard).setOnClickListener(this);
        view.findViewById(R.id.btn_nave1).setOnClickListener(this);
        view.findViewById(R.id.btn_nave2).setOnClickListener(this);
        view.findViewById(R.id.btn_nave3).setOnClickListener(this);
        naveElegida = false;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_easy:
                if(naveElegida) {
                    listener.onVidaListener(5);
                    ((ScaffoldActivity) getActivity()).startGame();
                    break;
                }else{
                    Toast.makeText(getContext(), "Escoge primero una nave por favor", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_medium:
                if(naveElegida) {
                    listener.onVidaListener(3);
                    ((ScaffoldActivity) getActivity()).startGame();
                    break;
                }else{
                    Toast.makeText(getContext(), "Escoge primero una nave por favor", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_hard:
                if(naveElegida) {
                    listener.onVidaListener(1);
                    ((ScaffoldActivity) getActivity()).startGame();
                    break;
                }else{
                    Toast.makeText(getContext(), "Escoge primero una nave por favor", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_nave1:
                listener.onNaveListener(1);
                Toast.makeText(getContext(), "Has escogido la nave azul (Disparo simple)", Toast.LENGTH_SHORT).show();
                naveElegida = true;
                break;

            case R.id.btn_nave2:
                listener.onNaveListener(2);
                Toast.makeText(getContext(), "Has escogido la nave roja (Disparo omnidireccional)", Toast.LENGTH_SHORT).show();
                naveElegida = true;
                break;

            case R.id.btn_nave3:
                listener.onNaveListener(3);
                Toast.makeText(getContext(), "Has escogido la nave verde (Disparo triple)", Toast.LENGTH_SHORT).show();
                naveElegida = true;
                break;
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof MainMenuFragment.FragMenuListener){
            listener = (MainMenuFragment.FragMenuListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
