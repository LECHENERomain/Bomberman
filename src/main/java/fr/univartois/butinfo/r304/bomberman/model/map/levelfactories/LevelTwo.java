package fr.univartois.butinfo.r304.bomberman.model.map.levelfactories;

import fr.univartois.butinfo.r304.bomberman.model.map.ILevelBuilder;
import fr.univartois.butinfo.r304.bomberman.model.map.IMapGenerator;
import fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.MapDeux;
import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.IEnemyStrategy;
import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.movementstrategy.RandomMovesStrategy;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

public class LevelTwo implements ILevelBuilder {
    @Override
    public IMapGenerator getMap() {
        // Implementation of level one map creation
        return new MapDeux(SpriteStore.getInstance());
    }

    @Override
    public int getNumberOfEnnemy() {
        // Implementation of level one enemy creation
        return 5;
    }

    @Override
    public IEnemyStrategy getEnemyStrategy() {
        // Implementation of level one enemy strategy
        return new RandomMovesStrategy();
    }

    @Override
    public ILevelBuilder getNextLevelBuilder() {
        // Implementation of level one level builder
        return this;
    }
}
