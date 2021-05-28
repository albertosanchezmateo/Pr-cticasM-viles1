package dadm.scaffold.space;

import dadm.scaffold.R;
import dadm.scaffold.counter.GameFragment;
import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.engine.ScreenGameObject;
import dadm.scaffold.engine.Sprite;
import dadm.scaffold.sound.GameEvent;

public class Bullet extends Sprite {

    private double speedFactor;

    private SpaceShipPlayer parent;

    public Bullet(GameEngine gameEngine){
        super(gameEngine, R.drawable.bullet);

        speedFactor = gameEngine.pixelFactor * -300d / 1000d;
    }

    @Override
    public void startGame() {}

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        //positionY += speedFactor * elapsedMillis;
        if(direction == -3){
            positionY -= speedFactor * elapsedMillis;
            positionX -= speedFactor * elapsedMillis;
        }
        else if(direction == -2){
            positionX -= speedFactor * elapsedMillis;
        }
        else if(direction == -1){
            positionY += speedFactor * elapsedMillis;
            positionX -= speedFactor * elapsedMillis;
        }
        else if(direction == 0){
            positionY += speedFactor * elapsedMillis;
        }
        else if(direction == 1){
            positionY += speedFactor * elapsedMillis;
            positionX += speedFactor * elapsedMillis;
        }
        else if(direction == 2){
            positionX += speedFactor * elapsedMillis;
        }
        else if(direction == 3){
            positionY -= speedFactor * elapsedMillis;
            positionX += speedFactor * elapsedMillis;
        }
        else if(direction == 4){
            positionY -= speedFactor * elapsedMillis;
        }

        if (positionY < -height || positionY > gameEngine.height + height || positionX < -width || positionX > gameEngine.width + width) {
            gameEngine.removeGameObject(this);
            // And return it to the pool
            parent.releaseBullet(this);
        }
    }


    public void init(SpaceShipPlayer parentPlayer, double initPositionX, double initPositionY, int dir) {
        positionX = initPositionX - width/2;
        positionY = initPositionY - height/2;
        direction = dir;
        if(direction == -1 || direction == 3){
            rotation = 45;
        }else if(direction == 1 || direction == -3){
            rotation = -45;
        }else if(direction == 0 || direction == 4){
            rotation = 0;
        }else if(direction == -2 || direction == 2){
            rotation = 90;
        }
        parent = parentPlayer;
    }

    private void removeObject(GameEngine gameEngine) {
        gameEngine.removeGameObject(this);
        // And return it to the pool
        parent.releaseBullet(this);
    }

    @Override
    public void onCollision(GameEngine gameEngine, ScreenGameObject otherObject) {
        if (otherObject instanceof Asteroid) {
            // Remove both from the game (and return them to their pools)
            this.speedFactor = 0;
            speedFactor = gameEngine.pixelFactor * -300d / 1000d;
            removeObject(gameEngine);
            Asteroid a = (Asteroid) otherObject;
            a.removeObject(gameEngine);
            gameEngine.onGameEvent(GameEvent.AsteroidHit);
            // Add some score
            GameFragment.ganarPuntos();
        }
    }
}
