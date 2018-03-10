package br.com.android.gamecocos2d.engine;

import android.content.Context;
import android.view.SurfaceView;

/**
 * Created by JHUNIIN on 10/03/2018.
 */

public class Game extends SurfaceView implements Runnable {
    private boolean running;
    private Thread renderThread;

    public Game(Context context) {
        super(context);
        this.running = false;
        this.renderThread = null;
    }

    @Override
    public void run() {
        while (running){
            System.out.println("Running ....");
        }
    }

    public void resume(){
        this.running = true;
        this.renderThread = new Thread(this);
        this.renderThread.start();
    }
}
