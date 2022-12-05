package com.example.snakeladder;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SnakeLadder extends Application {
    public final int tileSize =40;
    int height = 10;
    int width = 10;

    int diceValue;

    int yLine =430;

    Group tileGroup = new Group();

    Player playerOne, playerTwo;

    Label randResult;

    boolean gameStart = true, turnOnePlayer= true,  turnTwoPlayer = false;
    public Pane createContent() {
        Pane root = new Pane();
        root.setPrefSize(width*tileSize, height*tileSize+80);
        root.getChildren().addAll(tileGroup);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                com.example.realsnakeladder.Tile tile =new com.example.realsnakeladder.Tile(tileSize,tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                tileGroup.getChildren().addAll(tile);

            }

        }
        // add labels
        randResult = new Label("Start the Game");
        randResult.setTranslateX(160);
        randResult.setTranslateY(yLine-20);

        // add three buttons

        Button playerOneButton = new Button("Player One");
        playerOneButton.setTranslateX(20);
        playerOneButton.setTranslateY(yLine);
        playerOneButton.setOnAction(actionEvent -> {
            if (gameStart) {
                if (turnOnePlayer) {
                    getDiceValue();
                    playerOne.movePlayer(diceValue);
                    playerOne.playerAtSnakeOrLaddr();
                    turnOnePlayer = false;
                    turnTwoPlayer = true;
                }
            }

        });

        Button gameButton = new Button("Start Game");
        gameButton.setTranslateX(160);
        gameButton.setTranslateY(yLine);

        Button playerTwoButton = new Button("Player Two");
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setTranslateY(yLine);
        playerTwoButton.setOnAction(actionEvent -> {
            if (gameStart){
                if (turnTwoPlayer){
                    getDiceValue();
                    playerTwo.movePlayer(diceValue);
                    playerTwo.playerAtSnakeOrLaddr();
                    turnOnePlayer= true;
                    turnTwoPlayer= false;
                }
            }
        });

        playerOne = new Player(tileSize, Color.BLACK);
        playerTwo = new Player(tileSize-10, Color.WHITE);



        Image img = new Image("C:\\Users\\USER\\IdeaProjects\\SnakeLadder\\src\\snake ladder image.jpg");
        ImageView boardImage = new ImageView();
        boardImage.setImage(img);
        boardImage.setFitWidth(tileSize*width);
        boardImage.setFitHeight(tileSize*height);

        tileGroup.getChildren().addAll(boardImage,
                playerOneButton, gameButton,
                playerTwoButton, randResult,
                playerOne.getGamePiece(), playerTwo.getGamePiece()
        );
        return root;
    }

    private void getDiceValue(){
        diceValue =(int)(Math.random()*6+1);
        randResult.setText(Integer.toString(diceValue));
    }

    @Override
    public void start(Stage stage) {
        // FXMLLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake and Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}