package fr.univartois.butinfo.r304.bomberman.model.movables.enemy.movementstrategy;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.Enemy;
import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.IEnemyStrategy;

public class MirrorPlayerMovementsStrategy implements IEnemyStrategy {
    @Override
    public boolean move(long delta, BombermanGame game, Enemy enemy, boolean b) {
        if(!b){
            double playerHorizontalSpeed = game.getPlayer().getHorizontalSpeed();
            double playerVerticalSpeed = game.getPlayer().getVerticalSpeed();


            // L'objet n'a atteint aucun obstacle
            enemy.setHorizontalSpeed(-playerHorizontalSpeed);
            enemy.setVerticalSpeed(-playerVerticalSpeed);
            return true;
        }
        return false;
    }
}
