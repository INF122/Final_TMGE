package com.example.final_tmge;

import com.example.final_tmge.src.Tetris.Tetris;
import com.example.final_tmge.src.Tetris.TetrisBoard;
import com.example.final_tmge.src.Tetris.TetrisPiece;
import com.example.final_tmge.src.Tetris.TetrisPieceGenerator;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class TetrisGameController implements GameController{
    public Button start;
    public Button end;
    public static Tetris tetris = (Tetris) Application.GamePlay;
    public Pane gameMatrixleft;
    public Pane gameMatrixright;
    public AnchorPane holder;

    public Label leftPlayer;
    public Label rightPlayer;
    public Label finalMessage;

    public Timer leftTimer;
    public Timer rightTimer;

    // This is the Tetris piece fall down interval in milliseconds. The lower the value the higher the speed.
    private int fallDownInterval = 200;
    private TetrisPiece currentPiece1;
    private TetrisPiece currentPiece2;

    private TetrisPieceGenerator tileGenerator;

    public void initialize() throws IOException {
        start.setOnMouseClicked(event -> startNewGame());
        end.setOnMouseClicked(event -> closeGame());
        tileGenerator =  new TetrisPieceGenerator();
        tileGenerator.Initialize(tetris.board1.GetBlockSize(), tetris.getXBlocks());
    }

    public void startNewGame() {
        if (leftTimer != null) {
            leftTimer.cancel();
        }
        if (rightTimer != null) {
            rightTimer.cancel();
        }

        tetris.startNewGame(gameMatrixleft, gameMatrixright, leftPlayer, rightPlayer);
        startNewGameForPlayer1();
        startNewGameForPlayer2();
    }

    private void startNewGameForPlayer1() {
        updateCurrentPiecesForPlayer1().run();
        leftTimer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                runGame(tetris.board1, currentPiece1, updateCurrentPiecesForPlayer1());
            }
        };
        leftTimer.schedule(task, 0, fallDownInterval);
    }

    private void startNewGameForPlayer2() {
        updateCurrentPiecesForPlayer2().run();
        rightTimer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                runGame(tetris.board2, currentPiece2, updateCurrentPiecesForPlayer2());
            }
        };
        rightTimer.schedule(task, 0, fallDownInterval);
    }

    private void registerKeys() {
        holder.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case W:
                        tetris.board1.pieceReverse(currentPiece1);
                        break;
                    case S:
                        tetris.board1.down(currentPiece1);
                        break;
                    case A:
                        tetris.board1.left(currentPiece1);
                        break;
                    case D:
                        tetris.board1.right(currentPiece1);
                        break;
                    case I:
                        tetris.board2.pieceReverse(currentPiece2);
                        break;
                    case K:
                        tetris.board2.down(currentPiece2);
                        break;
                    case J:
                        tetris.board2.left(currentPiece2);
                        break;
                    case L:
                        tetris.board2.right(currentPiece2);
                        break;
                }
            }
        });
    }

    private Runnable updateCurrentPiecesForPlayer1() {
        return () -> {
            currentPiece1 = (TetrisPiece) tileGenerator.generateTile();
            gameMatrixleft.getChildren().addAll(currentPiece1.pieceFirst, currentPiece1.pieceSecond, currentPiece1.pieceThird, currentPiece1.pieceFourth);
            registerKeys();
        };
    }
    private Runnable updateCurrentPiecesForPlayer2() {
        return () -> {
            currentPiece2 = (TetrisPiece) tileGenerator.generateTile();
            gameMatrixright.getChildren().addAll(currentPiece2.pieceFirst, currentPiece2.pieceSecond, currentPiece2.pieceThird, currentPiece2.pieceFourth);
            registerKeys();
        };
    }

    private void runGame(TetrisBoard board, TetrisPiece piece, Runnable currentPieceUpdater) {
        Platform.runLater(new Runnable() {
            public void run() {
                boolean pieceMoved = board.down(piece);
                if (!pieceMoved) {
                    if (piece.isStuckAtTop()) {
                        board.GameOver();
                        leftTimer.cancel();
                        rightTimer.cancel();
                    } else {
                        currentPieceUpdater.run();
                    }
                }
            }
        });
    }

    public void closeGame(){
        //Close the game Window
        Stage stage = (Stage) start.getScene().getWindow();
        stage.close();
        Application application = new Application();
        application.restart();

        leftTimer.cancel();
        rightTimer.cancel();
    }
}

