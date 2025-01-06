package fr.univartois.butinfo.r304.bomberman.model.explosion;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.bombs.IBombeStrategie;
import fr.univartois.butinfo.r304.bomberman.model.movables.Explosion;

public class NukeExplosion implements IBombeStrategie {
    private static final String BOOM = "explosion";

    @Override
    public void explode(BombermanGame game, double xPosition, double yPosition) {
        int spriteSize = game.getSpriteStore().getSpriteSize();
        long currentTime = System.currentTimeMillis();


        game.addMovable(new Explosion(game, xPosition, yPosition, game.getSpriteStore().getSprite(BOOM), currentTime));


        int width = game.getGameMap().getWidth();
        int height = game.getGameMap().getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double explosionX = i * (double)spriteSize;
                double explosionY = j * (double)spriteSize;
                game.addMovable(new Explosion(game, explosionX, explosionY, game.getSpriteStore().getSprite(BOOM), currentTime));
            }
        }
    }
}
