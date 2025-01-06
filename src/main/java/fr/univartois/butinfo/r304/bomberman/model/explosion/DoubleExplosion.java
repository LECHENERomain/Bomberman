package fr.univartois.butinfo.r304.bomberman.model.explosion;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.bombs.IBombeStrategie;
import fr.univartois.butinfo.r304.bomberman.model.movables.Explosion;

public class DoubleExplosion implements IBombeStrategie {
    @Override
    public void explode(BombermanGame game, double xPosition, double yPosition) {
        final String explosionString = "explosion";
        game.addMovable(new Explosion(game, xPosition, yPosition, game.getSpriteStore().getSprite(explosionString), System.currentTimeMillis()));
        game.addMovable(new Explosion(game, xPosition, yPosition + game.getSpriteStore().getSpriteSize(), game.getSpriteStore().getSprite(explosionString), System.currentTimeMillis())); // en haut
        game.addMovable(new Explosion(game, xPosition, yPosition + (2*game.getSpriteStore().getSpriteSize()), game.getSpriteStore().getSprite(explosionString), System.currentTimeMillis())); // en haut 2eme case
        game.addMovable(new Explosion(game, xPosition, yPosition - game.getSpriteStore().getSpriteSize(), game.getSpriteStore().getSprite(explosionString),System.currentTimeMillis())); // en bas
        game.addMovable(new Explosion(game, xPosition, yPosition - (2*game.getSpriteStore().getSpriteSize()), game.getSpriteStore().getSprite(explosionString),System.currentTimeMillis())); // en bas 2eme case
        game.addMovable(new Explosion(game, xPosition + game.getSpriteStore().getSpriteSize(), yPosition, game.getSpriteStore().getSprite(explosionString), System.currentTimeMillis())); // à droite
        game.addMovable(new Explosion(game, xPosition + (2*game.getSpriteStore().getSpriteSize()), yPosition, game.getSpriteStore().getSprite(explosionString), System.currentTimeMillis())); // à droite 2eme case
        game.addMovable(new Explosion(game, xPosition - game.getSpriteStore().getSpriteSize(), yPosition, game.getSpriteStore().getSprite(explosionString), System.currentTimeMillis())); // à gauche
        game.addMovable(new Explosion(game, xPosition - (2*game.getSpriteStore().getSpriteSize()), yPosition, game.getSpriteStore().getSprite(explosionString), System.currentTimeMillis())); // à gauche 2eme case
    }
}
