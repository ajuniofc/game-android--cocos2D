package br.com.android.gamecocos2d.engine;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import br.com.android.gamecocos2d.R;

/**
 * Created by JHUNIIN on 10/03/2018.
 */

public class Game extends SurfaceView implements Runnable {
    private Context context;
    private boolean running;
    private Thread renderThread;
    private SurfaceHolder holder;
    private Paint paint;
    private int playerX = 300, playerY = 300, playerRadius = 50;
    private int enemyX, enemyY, enemyRadius = 50;
    private double distance;
    private boolean gameOver;
    private int score;


    public Game(Context context) {
        super(context);
        this.context = context;
        this.running = false;
        this.renderThread = null;
        this.paint = new Paint();
        holder = getHolder();
    }

    @Override
    public void run() {
        while (running){
            // verifica se a tela ja esta pronta
            if (!holder.getSurface().isValid()){
                continue;
            }

            // bloqueia o canvas
            Canvas canvas = holder.lockCanvas();

            // desenha background
            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),
                    R.drawable.sky), 0, 0, null);

            // desenha o player
            drawPlayer(canvas);
            // desenha o inimigo
            drawEnemy(canvas);

            // checa se tem colisao
            checkCollision(canvas);

            if (gameOver){
                stopGame(canvas);
                break;
            }

            drawScore(canvas);

            drawButtons(canvas);

            // atualiza e libera o canvas
            holder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawPlayer(Canvas canvas) {
        paint.setColor(Color.GREEN);
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.nave),
                playerX - 50, playerY - 50, null);
    }

    private void drawEnemy(Canvas canvas){
        paint.setColor(Color.RED);
        enemyRadius++;
        canvas.drawCircle(enemyX, enemyY, enemyRadius, paint);
    }

    private void drawButtons(Canvas canvas){
        // Restart
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        canvas.drawText("Restart", 50, 300, paint);
        // Exit
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        canvas.drawText("Exit", 50, 500, paint);
    }

    public void moveDown(int pixels){
        playerY += pixels;
    }

    private void checkCollision(Canvas canvas){
        distance = Math.pow(playerY - enemyY, 2)
                    + Math.pow(playerX - enemyX, 2);
        distance =Math.sqrt(distance);

        if (distance <= playerRadius + enemyRadius){
            gameOver = true;
        }
    }

    public void addScore(int points){
        score += points;
    }

    public void init() {
        enemyX = enemyY = enemyRadius = 0;
        playerX = playerY = 300;
        playerRadius = 50;
        gameOver = false;
        score = 0;
    }

    public void resume(){
        this.running = true;
        this.renderThread = new Thread(this);
        this.renderThread.start();
    }

    private void drawScore(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        canvas.drawText(String.valueOf(score), 50, 200, paint);
    }

    private void stopGame(Canvas canvas){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        paint.setTextSize(100);
        String gameOver = String.valueOf(context.getResources().getText(R.string.game_over));
        canvas.drawText(gameOver, 150, canvas.getHeight()/2, paint);
    }
}
