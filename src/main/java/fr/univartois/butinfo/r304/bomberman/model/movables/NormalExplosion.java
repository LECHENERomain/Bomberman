package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;

public class NormalExplosion implements IBombeStrategie {


    @Override
    public void explode(BombermanGame game, double xPosition, double yPosition) {
        game.addMovable(new Explosion(game, xPosition, yPosition, game.getSpriteStore().getSprite("explosion"), System.currentTimeMillis()));
        game.addMovable(new Explosion(game, xPosition, yPosition + game.getSpriteStore().getSpriteSize(), game.getSpriteStore().getSprite("explosion"), System.currentTimeMillis())); // en haut
        game.addMovable(new Explosion(game, xPosition, yPosition - game.getSpriteStore().getSpriteSize(), game.getSpriteStore().getSprite("explosion"),System.currentTimeMillis())); // en bas
        game.addMovable(new Explosion(game, xPosition + game.getSpriteStore().getSpriteSize(), yPosition, game.getSpriteStore().getSprite("explosion"), System.currentTimeMillis())); // à droite
        game.addMovable(new Explosion(game, xPosition - game.getSpriteStore().getSpriteSize(), yPosition, game.getSpriteStore().getSprite("explosion"), System.currentTimeMillis())); // à gauche
    }
}
