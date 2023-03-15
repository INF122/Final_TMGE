package com.example.final_tmge;

import com.example.final_tmge.src.Memory;
import com.example.final_tmge.src.TMGE;
import com.example.final_tmge.src.Tetris.Tetris;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class TetrisGameController {
    public Button start;
    private Tetris tetris = (Tetris)Application.GamePlay;
    public GridPane gameMatrix;

    public void initialize() throws IOException {
        startNewGame();
        start.setOnMouseClicked(event -> startNewGame());
    }

    public void startNewGame(){
        Text scoretext = new Text("Score: ");
        gameMatrix.getChildren().addAll(scoretext);

        gameMatrix.getChildren().clear();

        }
    }

