package fr.univartois.butinfo.r304.bomberman.model.movables;


import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.view.ISpriteStore;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

public class Explosion extends AbstractMovable {
    /**Début de l'explosion
     *
     */
    protected long timeExplosionStart=System.currentTimeMillis();
    /**Temps de l'explosion en milliseconde
     *
     */
    protected long timeExplosion=3000;
    ISpriteStore spriteStore;


    public Explosion(BombermanGame game, double xPosition, double yPosition, Sprite sprite, long timeExplosionStart) {
        super(game, xPosition, yPosition, sprite);
        this.timeExplosionStart = timeExplosionStart;
        spriteStore = game.getSpriteStore();
        destroyWall();
    }

    /**enlève l'explosion quand le temps impartie est atteint
     *
     * @param timeDelta Le temps écoulé depuis le dernier déplacement de cet objet (en
     *        millisecondes).
     *
     * @return
     */
    public boolean move(long timeDelta) {
        long tempsEcoule = System.currentTimeMillis() - timeExplosionStart;  // Calculer le temps écoulé depuis le début de l'explosion
        if (tempsEcoule >= timeExplosion) {
            game.removeMovable(this);
        }

        return true;
    }

    void destroyWall() {
        int column = xPosition.intValue();
        int row = yPosition.intValue();
        Cell cell = game.getCellAt(column, row);

        if(!cell.isEmpty()){
            cell.cellExplose(spriteStore);
            timeExplosion = 0;
        }

    }



    @Override
    public void collidedWith(IMovable other) {
        other.explode();
    }

    @Override
    public void explode() {
    }

    @Override
    public void hitEnemy() {
    }
}
