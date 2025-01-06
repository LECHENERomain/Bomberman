package fr.univartois.butinfo.r304.bomberman.model;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class InvisibleState implements IPlayerState {
    //Typo lors de la création de la classe, ce n'est pas Invisible, mais InviNCIble, désolé :(

    /**
     * La durée d'invincibilité du joueur
     */
    private static final int INVISIBILITY_DURATION = 3; // Temps d'invinsibilité en secondes

    /**
     * Constructeur de l'état invincible
     * @param player Le joueur devenant invincible
     */
    public InvisibleState(Player player) {
        startInvisibilityTimer(player);
        player.setSprite(player.getGame().getSpriteStore().getSprite("guy_invicible"));
    }

    @Override
    public void hit(Player player) {
        // Do nothing, the player is invisible and cannot be hit
    }

    /**
     * Méthode gérant le temps d'invincibilité du joueur
     * @param player Le joueur invincible
     */
    private void startInvisibilityTimer(Player player) {
        PauseTransition transition = new PauseTransition(Duration.seconds(INVISIBILITY_DURATION));
        transition.setOnFinished(event -> {
            player.setState(new NormalState());
            player.setSprite(player.getGame().getSpriteStore().getSprite("guy"));
        }); // Return to normal after the delay
        transition.play();
    }

}
