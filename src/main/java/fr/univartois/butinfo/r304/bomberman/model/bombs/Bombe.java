package fr.univartois.butinfo.r304.bomberman.model.bombs;


import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.movables.IBonusStrategy;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.Explosion;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

public class Bombe extends AbstractMovable {
    protected IBombeStrategie strategie;


    /**
     * Temps quand la bombe a été déposée
     */
    protected long timeDroppedBombe;
    /**Délai avant que la bombe explose en millisecondes
     *
     */
    protected long timeBombeDelay = 5000;
    /**Quand une bombe explose, une explosion apparait donc.
     * 
     */
    protected Explosion explosion;

    /**
     * Constructeur de la bombe
     * @param game L'instance du jeu
     * @param xPosition La position x de la bombe
     * @param yPosition La position y de la bombe
     * @param sprite Le sprite de la bombe
     * @param strategie La stratégie de la bombe
     */
    public Bombe(BombermanGame game, double xPosition, double yPosition, Sprite sprite,IBombeStrategie strategie) {
        super(game,xPosition+game.getSpriteStore().getSpriteSize(),yPosition,sprite);
        this.strategie= strategie;

    }

    /**
     * Mutateur de l'attribut correspondant au temps où la bombe est posée
     * @param timeDroppedBombe Le temps en question
     */
    public void setTimeDroppedBombe(long timeDroppedBombe) {
        this.timeDroppedBombe = timeDroppedBombe;
    }

    /** Enlève la bombe quand le temps imparti est atteint
     *
     * @param timeDelta Le temps écoulé depuis le dernier déplacement de cet objet (en
     *        millisecondes).
     *
     * @return Un booléen
     */

    @Override
    public boolean move(long timeDelta) {
        long tempsEcoule = System.currentTimeMillis() - timeDroppedBombe;  // Calculer le temps écoulé depuis que la bombe a été drop
        if (tempsEcoule >= timeBombeDelay) {
            game.removeMovable(this);
            declencherExplosion();
        }
        return true;
    }

    /**
     * Déclenche l'explosion de la bombe et crée les explosions autour.
     */
    private void declencherExplosion() {
        strategie.explode( game,  xPosition.get(),  yPosition.get());
    }


    public void collidedWith(IMovable other) {
        // Nothing happens
    }

    @Override
    public void explode() {
        // Makes the bomb explode immediately
        declencherExplosion();
        game.removeMovable(this);
    }

    @Override
    public void hitEnemy() {
        // Nothing happens
    }

    @Override
    public void hitBonus(IMovable bonusBombe, IBonusStrategy strategie ) {
        // Nothing happens
    }
}
