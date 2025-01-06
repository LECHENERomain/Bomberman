package fr.univartois.butinfo.r304.bomberman.model;

public interface IPlayerState {

    /**
     * Méthode gérant le contact entre un ennemi et le joueur
     * @param player Le joueur
     */
    void hit(Player player);
}
