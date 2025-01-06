package fr.univartois.butinfo.r304.bomberman.model.bonusstrategy;

import fr.univartois.butinfo.r304.bomberman.model.Player;
import fr.univartois.butinfo.r304.bomberman.model.bombs.Bombe;
import fr.univartois.butinfo.r304.bomberman.model.bombs.IBombeStrategie;
import fr.univartois.butinfo.r304.bomberman.model.movables.IBonusStrategy;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

public class AddBombStrategy implements IBonusStrategy {

    /**
     * La stratégie de la bombe
     */
    IBombeStrategie strategie;

    /**
     * Le constructeur permettant d'affecter la stratégie à la bombe
     * @param strategie La stratégie choisie
     */
    public AddBombStrategy(IBombeStrategie strategie) {
        this.strategie = strategie;
    }

    /**
     * Implémentation du bonus
     * @param player Le joueur ramassant le bonus
     */
    @Override
    public void applyBonus(Player player) {
        player.addBomb(new Bombe(player.getGame(), 0, 0, SpriteStore.getInstance().getSprite("bomb"), strategie));
    }
}
