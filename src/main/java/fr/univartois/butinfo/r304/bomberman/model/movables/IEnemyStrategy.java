package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;

public interface IEnemyStrategy {
    public boolean move(long delta, BombermanGame game, Enemy enemy, boolean b);
}
