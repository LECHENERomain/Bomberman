package fr.univartois.butinfo.r304.bomberman.model.bonusstrategy;

import fr.univartois.butinfo.r304.bomberman.model.InvisibleState;
import fr.univartois.butinfo.r304.bomberman.model.Player;
import fr.univartois.butinfo.r304.bomberman.model.movables.IBonusStrategy;

public class InvincibilityStrategy implements IBonusStrategy {

    /**
     * Implémentation du bonus
     * @param player Le joueur ramassant le bonus
     */
    @Override
    public void applyBonus(Player player) {
        // Transition to invincible state after being hit
        player.setState(new InvisibleState(player));
    }
}
