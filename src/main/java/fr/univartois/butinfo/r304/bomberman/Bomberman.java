package fr.univartois.butinfo.r304.bomberman;

import java.io.IOException;

import fr.univartois.butinfo.r304.bomberman.controller.BombermanController;
import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.map.ILevelBuilder;
import fr.univartois.butinfo.r304.bomberman.model.map.levelfactories.LevelOne;
import fr.univartois.butinfo.r304.bomberman.model.map.levelfactories.LevelTwo;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * La classe Bomberman permet de lancer l'implantation en JavaFX du jeu Bomberman.
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class Bomberman extends Application {

    /**
     * La largeur (en pixels) de la fenêtre affichant le jeu.
     */
    private static final int GAME_WIDTH = 1080;

    /**
     * La hauteur (en pixels) de la fenêtre affichant le jeu.
     */
    private static final int GAME_HEIGHT = 720;



    @Override
    public void start(Stage stage) {
        // Création du menu principal
        createMainMenu(stage);
    }

    /**
     * Crée et affiche le menu principal du jeu.
     *
     * @param stage La fenêtre principale de l'application.
     */
    private void createMainMenu(Stage stage) {
        // Image du titre
        ImageView titleImage = new ImageView(new Image(String.valueOf(getClass().getResource("view/sprites/logo.png"))));
        titleImage.setFitWidth(300);
        titleImage.setPreserveRatio(true);

        final Font font = new Font("OCR A Extended", 16);

        // Bouton pour démarrer le jeu
        Button startButton = new Button("Start Game");
        startButton.setFont(font);
        startButton.setMinWidth(200);
        startButton.setMinHeight(50);
        startButton.setOnAction(event -> launchGame(stage));

        // Boutons pour les niveaux
        Button level1Button = new Button("Niveau 1");
        level1Button.setFont(font);
        level1Button.setMinWidth(200);
        level1Button.setMinHeight(40);
        level1Button.setOnAction(event -> launchLevel(stage, 1));

        Button level2Button = new Button("Niveau 2");
        level2Button.setFont(font);
        level2Button.setMinWidth(200);
        level2Button.setMinHeight(40);
        level2Button.setOnAction(event -> launchLevel(stage, 2));

        Button level3Button = new Button("Niveau 3");
        level3Button.setFont(new Font("OCR A Extended", 14));
        level3Button.setMinWidth(200);
        level3Button.setMinHeight(40);
        level3Button.setOnAction(event -> launchLevel(stage, 3));

        // Organisation des éléments dans un VBox
        VBox root = new VBox(20, titleImage, startButton, level1Button, level2Button, level3Button);
        root.setStyle("-fx-alignment: center; -fx-padding: 20; -fx-spacing: 10;");

        // Création de la scène pour le menu
        Scene menuScene = new Scene(root, 400, 400);

        stage.setTitle("Bomberman Menu");
        stage.setScene(menuScene);
        stage.show();
    }

    /**
     * Lance le jeu Bomberman.
     *
     * @param stage La fenêtre principale de l'application.
     */
    private void launchGame(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/bomberman.fxml"));
            Parent viewContent = fxmlLoader.load();
            BombermanController controller = fxmlLoader.getController();
            controller.setStage(stage);

            BombermanGame game = new BombermanGame(
                    GAME_WIDTH, GAME_HEIGHT, SpriteStore.getInstance(), new LevelOne());
            controller.setGame(game);
            game.setController(controller);
            game.prepare();

            Scene gameScene = new Scene(viewContent, GAME_WIDTH, GAME_HEIGHT);
            stage.setScene(gameScene);
            stage.setTitle("BombermanFX");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lance un niveau spécifique de Bomberman.
     *
     * @param stage La fenêtre principale de l'application.
     * @param level Le numéro du niveau à lancer.
     */
    private void launchLevel(Stage stage, int level) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/bomberman.fxml"));
            Parent viewContent = fxmlLoader.load();
            BombermanController controller = fxmlLoader.getController();
            controller.setStage(stage);
            ILevelBuilder[] levelBuilderList = {new LevelOne(), new LevelTwo()};
            BombermanGame game = new BombermanGame(
                    GAME_WIDTH, GAME_HEIGHT, SpriteStore.getInstance(),
                    levelBuilderList[level - 1]);
            controller.setGame(game);
            game.setController(controller);
            game.prepare();


            System.out.println("Lancement du niveau " + level);


            Scene gameScene = new Scene(viewContent, GAME_WIDTH, GAME_HEIGHT);
            stage.setScene(gameScene);
            stage.setTitle("BombermanFX - Niveau " + level);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exécute l'application JavaFX du jeu Bomberman.
     *
     * @param args Les arguments de la ligne de commande (dont on ne tient pas compte).
     *
     * @see #launch(String...)
     */
    public static void main(String[] args) {
        launch();
    }
}
