package dadm.scaffold;

import android.media.AudioManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dadm.scaffold.counter.GameFragment;
import dadm.scaffold.counter.MainMenuFragment;
import dadm.scaffold.counter.ScoreFragment;
import dadm.scaffold.sound.SoundManager;

public class ScaffoldActivity extends AppCompatActivity implements MainMenuFragment.FragMenuListener, GameFragment.FragScoreListener{

    private static final String TAG_FRAGMENT = "content";
    GameFragment gameFrag;
    ScoreFragment scoreFrag;
    private SoundManager soundManager;

    public static int numVidas, idNave, puntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scaffold);
        gameFrag = new GameFragment();
        scoreFrag = new ScoreFragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainMenuFragment(), TAG_FRAGMENT)
                    .commit();
        }
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundManager = new SoundManager(getApplicationContext());
        soundManager.playMenuMusic();
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public void startGame() {
        // Navigate the the game fragment, which makes the start automatically
        navigateToFragment( gameFrag);
        gameFrag.setValores(numVidas, idNave);
        soundManager.playGameMusic();
    }

    public void navigateToFragment(BaseFragment dst) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, dst, TAG_FRAGMENT)
                .commitNow();
    }

    @Override
    public void onBackPressed() {
        final BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        if (fragment == null || !fragment.onBackPressed()) {
            super.onBackPressed();
        }
    }

    public void navigateBack() {
        // Do a push on the navigation history
        navigateToFragment(new MainMenuFragment());
        soundManager.playMenuMusic();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE);
            }
            else {
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }




    @Override
    public void onVidaListener(int vidas) {
        numVidas = vidas;
    }

    @Override
    public void onNaveListener(int nave) {
        idNave = nave;
        System.out.println("Nave: " + idNave);
    }

    @Override
    public void onScoreListener(int score) {
        puntuacion = score;
    }

    public void Ranking(){
        soundManager.playMenuMusic();
        navigateToFragment(scoreFrag);
        scoreFrag.ponerPuntuacion(puntuacion);
    }

}
