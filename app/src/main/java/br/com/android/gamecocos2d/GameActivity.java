package br.com.android.gamecocos2d;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import br.com.android.gamecocos2d.engine.Game;

public class GameActivity extends Activity implements View.OnTouchListener{
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
        mGame.setOnTouchListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGame.resume();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getX() < 100 && event.getY() > 290 &&
                event.getY() < 310) {
            mGame.init();
        }
        // Exit
        if(event.getX() < 100 && event.getY() > 490 &&
                event.getY() < 510) {
            System.exit(0);
        }

        mGame.moveDown(10);
        mGame.addScore(100);
        return true;
    }
}
