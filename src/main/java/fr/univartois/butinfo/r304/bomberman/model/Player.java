package fr.univartois.butinfo.r304.bomberman.model;

import fr.univartois.butinfo.r304.bomberman.model.bombs.Bombe;
import fr.univartois.butinfo.r304.bomberman.model.movables.IBonusStrategy;
import fr.univartois.butinfo.r304.bomberman.model.movables.*;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public class Player extends AbstractMovable {

    /**
     * Points de vie du joueur
     */
    private final IntegerProperty lives;

    /**
     * Score du joueur (nombre d'ennemis tués)
     */
    private final IntegerProperty score;

    /**
     * Nombre de vies du joueur
     */
    static final int MAXLIVES = 3;

    /**
     * Inventaire du joueur (liste de bombes)
     */
    private final ObservableList<Bombe> bombes;

    /**
     * Ajout des points de vie grâce au décorateur
     */
    private final MovableDecorator healthDecorator;

    /**
     * Etat du joueur (invincible ou non)
     */
    private IPlayerState state;

    /**
     * Contructeur du personnage joueur
     * @param game L'instance du jeu
     * @param xPosition La position x du spawn du joueur
     * @param yPosition La position y du spawn du joueur
     * @param sprite Le sprite du joueur
     */
    protected Player(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
        lives = new SimpleIntegerProperty(MAXLIVES);
        score = new SimpleIntegerProperty(0);
        bombes = FXCollections.observableArrayList();
        state = new NormalState();
        healthDecorator = new HealthDecorator(this,MAXLIVES);
    }

    /**
     * Accesseur des bombes du joueur
     * @return La liste des bombes
     */
    public ObservableList<Bombe> getBombes() {
        return bombes;
    }

    /**
     * Méthode permettant d'ajouter une bombe à l'inventaire du joueur
     * @param bombe La bombe à ajouter
     */
    public void addBomb(Bombe bombe) {
        bombes.add(bombe);
    }

    /**
     * We love JavaFX
     * @return Les vies du joueur
     */
    public IntegerProperty livesProperty() {
        return lives;
    }

    /**
     * We love JavaFX
     * @return Le score du joueur (le nombre d'ennemis tués)
     */
    public IntegerProperty scoreProperty() {
        return score;
    }

    /**
     * Méthode permettant de faire perdre une vie au joueur
     */
    public void decreaseLives() {
        if (healthDecorator.getHealthPoints() == 1) {
            game.playerIsDead();
        }
        healthDecorator.takeDamage(1);
        lives.set(healthDecorator.getHealthPoints());
    }

    /**
     * Méthode permettant de faire gagner un nombre de vies au joueur
     * @param lives Les vies gagnées
     */
    public void increaseLives(int lives) {
        healthDecorator.heal(lives);
        this.lives.set(healthDecorator.getHealthPoints());
    }

    /**
     * Méthode permettant de faire augmenter le score du joueur
     */
    public void increaseScore() {
        score.set(score.get() + 1);
    }

    public void hit() {
        state.hit(this);
    }

    /*
     * Set the current state of the player
     */
    public void setState(IPlayerState state) {
        this.state = state;
    }

    @Override
    public void collidedWith(IMovable other) {
        //Nothing happens
    }

    @Override
    public void explode() {
        hit();
    }

    @Override
    public void hitEnemy() {
        hit();
    }

   @Override
    public void hitBonus(IMovable bonus, IBonusStrategy strategie ){
       strategie.applyBonus(this);
       bonus.consume();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Player player)) // That's okay here it's an equals pls dont kill me
            return false;
        if (!super.equals(o))
            return false;
        return Objects.equals(lives, player.lives)
               && Objects.equals(score, player.score)
               && Objects.equals(bombes, player.bombes)
               && Objects.equals(healthDecorator, player.healthDecorator)
               && Objects.equals(state, player.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), lives, score, bombes,
                healthDecorator,
                state);
    }

}

