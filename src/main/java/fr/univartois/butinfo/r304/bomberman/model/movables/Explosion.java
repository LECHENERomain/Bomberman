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

    /**
     * Constructeur de l'explosion
     * @param game L'instance du jeu
     * @param xPosition La position x de l'explosion
     * @param yPosition La position y de l'explosion
     * @param sprite Le sprite de l'explosion
     * @param timeExplosionStart Le temps auquel l'explosion commence
     */
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
     * @return Un booléen
     */
    @Override
    public boolean move(long timeDelta) {
        long tempsEcoule = System.currentTimeMillis() - timeExplosionStart;  // Calculer le temps écoulé depuis le début de l'explosion
        if (tempsEcoule >= timeExplosion) {
            game.removeMovable(this);
        }

        return true;
    }

    /**
     * La méthode permettant à l'explosion de détruire un mur qu'elle touche
     */
    void destroyWall() {
        int column = xPosition.intValue();
        int row = yPosition.intValue();
        Cell cell = game.getCellAt(column, row);

        if(!cell.isEmpty()){
            cell.cellExplose(spriteStore,game, xPosition.get(), yPosition.get());
            timeExplosion = 0;
        }
    }

    @Override
    public void collidedWith(IMovable other) {
        other.explode();
    }

    @Override
    public void explode() {
        //Nothing happens
    }

    @Override
    public void hitEnemy() {
        //Nothing happens
    }

    @Override
    public void hitBonus(IMovable bonusBombe, IBonusStrategy strategie) {
        //Nothing happens
    }
}
