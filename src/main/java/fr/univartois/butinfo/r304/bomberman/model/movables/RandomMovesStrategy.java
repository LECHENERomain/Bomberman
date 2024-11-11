package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import java.util.Random;

public class RandomMovesStrategy implements IEnemyStrategy{

    Random rand = new Random();
    @Override
    public boolean move(long delta, BombermanGame game, Enemy enemy, boolean b) {
        // On met à jour la position de l'objet sur l'axe x.
        int limitMaxX = game.getWidth() - enemy.getWidth();
        double newX = enemy.xPosition.get() + (enemy.horizontalSpeed * delta) / 1000;
        if ((newX < 0) || (newX > limitMaxX)) {
            // L'objet a atteint la limite sur l'axe x.
            enemy.setHorizontalSpeed(-enemy.horizontalSpeed);
        }

        // On met à jour la position de l'objet sur l'axe y.
        int limitMaxY = game.getHeight() - enemy.getHeight();
        double newY = enemy.yPosition.get() + (enemy.verticalSpeed * delta) / 1000;
        if ((newY < 0) || (newY > limitMaxY)) {
            // L'objet a atteint la limite sur l'axe y.
            enemy.setVerticalSpeed(-enemy.verticalSpeed);
        }

        // On vérifie qu'il n'y a pas un obstacle.
        if (enemy.isOnWall((int) newX, (int) newY)) {
            // L'objet a atteint un mur.
            enemy.setHorizontalSpeed(-enemy.horizontalSpeed);
            enemy.setVerticalSpeed(-enemy.verticalSpeed);
        }

        // L'objet n'a atteint aucun obstacle
        if (rand.nextInt(0, 300) == 0) {
            enemy.setHorizontalSpeed(-enemy.horizontalSpeed);
            enemy.setVerticalSpeed(-enemy.verticalSpeed);
        }
        enemy.xPosition.set(newX);
        enemy.yPosition.set(newY);
        return true;
    }
}
