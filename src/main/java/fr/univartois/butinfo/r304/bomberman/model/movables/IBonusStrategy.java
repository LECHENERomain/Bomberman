package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.Player;

public interface IBonusStrategy {
    /**
     * Méthode abstraite permettant implémenter la récupération d'un bonus par le joueur
     * @param player Le joueur ramassant le bonus
     */
    void applyBonus(Player player);
}
