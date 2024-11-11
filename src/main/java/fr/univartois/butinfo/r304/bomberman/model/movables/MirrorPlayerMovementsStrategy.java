package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;

public class MirrorPlayerMovementsStrategy implements IEnemyStrategy{
    @Override
    public boolean move(long delta, BombermanGame game, Enemy enemy, boolean b) {
        double playerHorizontalSpeed = game.getPlayer().getHorizontalSpeed();
        double playerVerticalSpeed = game.getPlayer().getVerticalSpeed();

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
        enemy.setHorizontalSpeed(-playerHorizontalSpeed);
        enemy.setVerticalSpeed(-playerVerticalSpeed);
        enemy.xPosition.set(newX);
        enemy.yPosition.set(newY);
        return true;
    }
}
