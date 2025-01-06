package fr.univartois.butinfo.r304.bomberman.model.map.levelfactories;

import fr.univartois.butinfo.r304.bomberman.model.map.ILevelBuilder;
import fr.univartois.butinfo.r304.bomberman.model.map.IMapGenerator;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.MapUne;
import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.IEnemyStrategy;
import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.movementstrategy.StraightLineStrategy;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

public class LevelOne implements ILevelBuilder {
    @Override
    public IMapGenerator getMap() {
        // Implementation of level one map creation
        return new MapUne(SpriteStore.getInstance());
    }

    @Override
    public int getNumberOfEnnemy() {
        // Implementation of level one enemy creation
        return 1;
    }

    @Override
    public IEnemyStrategy getEnemyStrategy() {
        // Implementation of level one enemy strategy
        return new StraightLineStrategy();
    }

    @Override
    public ILevelBuilder getNextLevelBuilder() {
        // Implementation of level one builder
        return new LevelTwo();
    }
}
