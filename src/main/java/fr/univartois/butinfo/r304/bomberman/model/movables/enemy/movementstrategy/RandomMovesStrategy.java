package fr.univartois.butinfo.r304.bomberman.model.movables.enemy.movementstrategy;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.Enemy;
import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.IEnemyStrategy;

import java.util.Random;

public class RandomMovesStrategy implements IEnemyStrategy {

    Random rand = new Random();
    @Override
    public boolean move(long delta, BombermanGame game, Enemy enemy, boolean b) {


        if(!b){
            // L'objet n'a atteint aucun obstacle
            if (rand.nextInt(0, 150) == 0) {
                enemy.setHorizontalSpeed(-enemy.getHorizontalSpeed());
            }
            if (rand.nextInt(0, 200) == 0) {
                enemy.setVerticalSpeed(-enemy.getVerticalSpeed());
            }
            return true;
        }
        return false;
    }
}
