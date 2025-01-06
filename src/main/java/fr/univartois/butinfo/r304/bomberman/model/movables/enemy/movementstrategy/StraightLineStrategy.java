package fr.univartois.butinfo.r304.bomberman.model.movables.enemy.movementstrategy;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.Enemy;
import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.IEnemyStrategy;

public class StraightLineStrategy implements IEnemyStrategy {

    @Override
    public boolean move(long delta, BombermanGame game, Enemy enemy, boolean b) {


        if (!b) {
            enemy.setHorizontalSpeed(-enemy.getHorizontalSpeed());
            enemy.setVerticalSpeed(-enemy.getVerticalSpeed());
            return true;
        }

        return false;
    }
}
