package br.com.android.gamecocos2d;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import br.com.android.gamecocos2d.engine.Game;

public class GameActivity extends Activity {
    private Game mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mGame = new Game(this);

        setContentView(mGame);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGame.resume();
    }
}
