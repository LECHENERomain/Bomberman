package fr.univartois.butinfo.r304.bomberman.model.bonusstrategy;

import fr.univartois.butinfo.r304.bomberman.model.Player;
import fr.univartois.butinfo.r304.bomberman.model.movables.IBonusStrategy;

public class AddHpStrategy implements IBonusStrategy {
    /**
     * Implémentation du bonus
     * @param player Le joueur ramassant le bonus
     */
    @Override
    public void applyBonus(Player player) {
        player.increaseLives(1);
    }
}
