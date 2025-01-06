/**
 * Ce logiciel est distribué à des fins éducatives.
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 * (c) 2022-2024 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.bomberman.model.map;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe {@link GameMap} représente la carte du jeu du Bomberman, sur laquelle les
 * personnages se déplacent et peuvent poser des bombes.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class GameMap {

    /**
     * Le nombre de lignes de cellules dans cette carte.
     */
    private final int height;

    /**
     * Le nombre de colonnes de cellules dans cette carte.
     */
    private final int width;

    /**
     * Les cellules qui constituent cette carte.
     */
    private final Cell[][] cells;

    /**
     * Construit une nouvelle instance de GameMap.
     *
     * @param width Le nombre de lignes de cellules dans la map.
     * @param height Le nombre de colonnes de cellules dans la map.
     */
    public GameMap(int height, int width) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[height][width];
        init();
    }

    /**
     * Crée les cellules qui constituent cette carte.
     */
    private void init() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Donne le nombre de lignes de cellules dans cette carte.
     *
     * @return Le nombre de lignes de cellules dans cette carte.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Donne le nombre de colonnes de cellules dans cette carte.
     *
     * @return Le nombre de colonnes de cellules dans cette carte.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Donne la cellule à la position donnée.
     *
     * @param row La ligne de la cellule.
     * @param column La colonne de la cellule.
     *
     * @return La cellule à la position donnée.
     *
     * @throws IllegalArgumentException Si la position n'est pas sur la carte.
     */
    public Cell getAt(int row, int column) {
        if ((row < 0) || (height <= row) || (column < 0) || (width <= column)) {
            throw new IllegalArgumentException("Incorrect cell location!");
        }
        return cells[row][column];
    }

    /**
     * Modifie la cellule à la position donnée.
     *
     * @param row La ligne de la cellule.
     * @param column La colonne de la cellule.
     * @param cell La cellule à mettre à la position donnée.
     *
     * @throws IllegalArgumentException Si la position n'est pas sur la carte.
     */
    public void setAt(int row, int column, Cell cell) {
        if ((row < 0) || (height <= row) || (column < 0) || (width <= column)) {
            throw new IllegalArgumentException("Incorrect cell location!");
        }
        cells[row][column].replaceBy(cell);
    }

    /**
     * Donne la liste des cellules qui sont vides sur cette carte.
     *
     * @return La liste des cellules vides.
     *
     * @see Cell#isEmpty()
     */
    public List<Cell> getEmptyCells() {
        List<Cell> emptyTiles = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cells[i][j].isEmpty()) {
                    emptyTiles.add(cells[i][j]);
                }
            }
        }

        return emptyTiles;
    }

}
