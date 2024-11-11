package fr.univartois.butinfo.r304.bomberman.model.movables;


import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.Player;
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
    /**Quand une bombe explose, une explosion apparait donc
     * 
     */
    protected Explosion explosion;

    public Bombe(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game,xPosition+game.getSpriteStore().getSpriteSize(),yPosition,sprite);
        strategie= new NormalExplosion();

    }

    public long getTimeDroppedBombe() {
        return timeDroppedBombe;
    }

    public void setTimeDroppedBombe(long timeDroppedBombe) {
        this.timeDroppedBombe = timeDroppedBombe;
    }

    /**enlève la bombe quand le temps impartie est atteint
     *
     * @param timeDelta Le temps écoulé depuis le dernier déplacement de cet objet (en
     *        millisecondes).
     *
     * @return
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
    }

    @Override
    public void explode() {
        // Déclenche immédiatement l'explosion de la bombe
        declencherExplosion();
        game.removeMovable(this);
    }

    @Override
    public void hitEnemy() {
    }




}