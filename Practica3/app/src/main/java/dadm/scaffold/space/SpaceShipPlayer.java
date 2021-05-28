package dadm.scaffold.space;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import dadm.scaffold.R;
import dadm.scaffold.ScaffoldActivity;
import dadm.scaffold.counter.GameFragment;
import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.engine.ScreenGameObject;
import dadm.scaffold.engine.Sprite;
import dadm.scaffold.input.InputController;
import dadm.scaffold.sound.GameEvent;

public class SpaceShipPlayer extends Sprite {

    private static int INITIAL_BULLET_POOL_AMOUNT = 24;
    private static long TIME_BETWEEN_BULLETS = 1000;
    List<Bullet> bullets = new ArrayList<Bullet>();
    private long timeSinceLastFire;

    private int maxX;
    private int maxY;
    private double speedFactor;
    private boolean vulnerable;
    private final int tiempoVulnerable = 2000;
    private int contadorVulnerable;
    private int naveOriginal;
    private int naveEscudo;


    public SpaceShipPlayer(GameEngine gameEngine, int nave){
        super(gameEngine, nave);
        naveOriginal = nave;
        switch (nave){
            case R.drawable.nave_azul:
                INITIAL_BULLET_POOL_AMOUNT = 8;
                TIME_BETWEEN_BULLETS = 250;
                naveEscudo = R.drawable.nave_azul_escudo;
                break;

            case R.drawable.nave_roja:
                INITIAL_BULLET_POOL_AMOUNT = 48;
                TIME_BETWEEN_BULLETS = 2000;
                naveEscudo = R.drawable.nave_roja_escudo;
                break;

            case R.drawable.nave_verde:
                INITIAL_BULLET_POOL_AMOUNT = 24;
                TIME_BETWEEN_BULLETS = 1000;
                naveEscudo = R.drawable.nave_verde_escudo;
                break;
        }
        speedFactor = pixelFactor * 100d / 1000d; // We want to move at 100px per second on a 400px tall screen
        maxX = gameEngine.width - width;
        maxY = gameEngine.height - height;
        vulnerable = true;
        contadorVulnerable = 0;

        initBulletPool(gameEngine);
    }

    private void initBulletPool(GameEngine gameEngine) {
        for (int i=0; i < INITIAL_BULLET_POOL_AMOUNT; i++) {
            bullets.add(new Bullet(gameEngine));
        }
    }

    private Bullet getBullet() {
        if (bullets.isEmpty()) {
            return null;
        }
        return bullets.remove(0);
    }

    void releaseBullet(Bullet bullet) {
        bullets.add(bullet);
    }


    @Override
    public void startGame() {
        positionX = maxX/2;
        positionY = maxY;

    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        // Get the info from the inputController
        updatePosition(elapsedMillis, gameEngine.theInputController);
        checkFiring(elapsedMillis, gameEngine);
        if(!vulnerable){
            contadorVulnerable += elapsedMillis;
            if(contadorVulnerable >= tiempoVulnerable){
                this.cambiarSprite(gameEngine, naveOriginal);
                vulnerable = true;
                contadorVulnerable = 0;
            }
        }
    }

    private void updatePosition(long elapsedMillis, InputController inputController) {
        positionX += speedFactor * inputController.horizontalFactor * elapsedMillis;
        if (positionX < 0) {
            positionX = 0;
        }
        if (positionX > maxX) {
            positionX = maxX;
        }
        positionY += speedFactor * inputController.verticalFactor * elapsedMillis;
        if (positionY < 0) {
            positionY = 0;
        }
        if (positionY > maxY) {
            positionY = maxY;
        }
    }

    private void checkFiring(long elapsedMillis, GameEngine gameEngine) {
        //System.out.println("Municion: " + bullets.size());
        if (gameEngine.theInputController.isFiring && timeSinceLastFire > TIME_BETWEEN_BULLETS) {
            if(naveOriginal == R.drawable.nave_azul){
                Bullet bullet = getBullet();
                if (bullet == null) {
                    return;
                }else{
                    bullet.init(this, positionX + width/2, positionY, 0);
                    gameEngine.addGameObject(bullet);

                    timeSinceLastFire = 0;
                    gameEngine.onGameEvent(GameEvent.LaserFired);
                }
            }
            else if(naveOriginal == R.drawable.nave_roja){
                Bullet bulletLeftTop = getBullet();
                Bullet bulletCenterTop = getBullet();
                Bullet bulletRightTop = getBullet();
                Bullet bulletLeftDown = getBullet();
                Bullet bulletCenterDown = getBullet();
                Bullet bulletRightDown = getBullet();
                Bullet bulletLeft = getBullet();
                Bullet bulletRight= getBullet();
                if (bulletLeftTop == null || bulletCenterTop == null || bulletRightTop == null || bulletLeftDown == null || bulletCenterDown == null || bulletRightDown == null || bulletRight == null || bulletLeft == null) {
                    return;
                }else{
                    bulletLeftTop.init(this, positionX + width, positionY, -1);
                    bulletCenterTop.init(this, positionX + width/2, positionY, 0);
                    bulletRightTop.init(this, positionX, positionY, 1);
                    bulletLeftDown.init(this, positionX + width, positionY + height, -3);
                    bulletCenterDown.init(this, positionX + width/2, positionY + height, 4);
                    bulletRightDown.init(this, positionX, positionY + height, 3);
                    bulletLeft.init(this, positionX + width, positionY + height/2, -2);
                    bulletRight.init(this, positionX, positionY + height/2, 2);
                    gameEngine.addGameObject(bulletLeftTop);
                    gameEngine.addGameObject(bulletCenterTop);
                    gameEngine.addGameObject(bulletRightTop);
                    gameEngine.addGameObject(bulletLeftDown);
                    gameEngine.addGameObject(bulletCenterDown);
                    gameEngine.addGameObject(bulletRightDown);
                    gameEngine.addGameObject(bulletLeft);
                    gameEngine.addGameObject(bulletRight);

                    timeSinceLastFire = 0;
                    gameEngine.onGameEvent(GameEvent.LaserFired);
                }
            }
            else if(naveOriginal == R.drawable.nave_verde){
                Bullet bulletLeft = getBullet();
                Bullet bulletCenter = getBullet();
                Bullet bulletRight = getBullet();
                if (bulletLeft == null || bulletCenter == null || bulletRight == null) {
                    return;
                }else{
                    bulletLeft.init(this, positionX + width, positionY, -1);
                    bulletCenter.init(this, positionX + width/2, positionY, 0);
                    bulletRight.init(this, positionX, positionY, 1);
                    gameEngine.addGameObject(bulletLeft);
                    gameEngine.addGameObject(bulletCenter);
                    gameEngine.addGameObject(bulletRight);

                    timeSinceLastFire = 0;
                    gameEngine.onGameEvent(GameEvent.LaserFired);
                }
            }


        }
        else {
            timeSinceLastFire += elapsedMillis;
        }

    }

    @Override
    public void onCollision(GameEngine gameEngine, ScreenGameObject otherObject) {
        if (otherObject instanceof Asteroid) {

            //gameEngine.stopGame();
            Asteroid a = (Asteroid) otherObject;
            a.removeObject(gameEngine);

            if(vulnerable){
                GameFragment.perderVida();
                ScaffoldActivity.numVidas--;
                if(ScaffoldActivity.numVidas == 0){
                    //Quitar nave
                    //gameEngine.terminar();
                    gameEngine.removeGameObject(this);
                }
                gameEngine.onGameEvent(GameEvent.SpaceshipHit);
                this.cambiarSprite(gameEngine, naveEscudo);
                vulnerable = false;
            }

        }
    }
}
