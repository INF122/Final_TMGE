package com.example.final_tmge.src.Tetris;

import com.example.final_tmge.src.GamePiece;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

public class TetrisPiece extends GamePiece {
    Rectangle a;
    Rectangle b;
    Rectangle c;
    Rectangle d;
    Color color;

    HashMap<String, Color> piece;
    private String name;
    public int form = 1;


    public TetrisPiece(String id) {
        super(id);
    }

    public TetrisPiece(String id, Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        super(id);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public TetrisPiece(String id, Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name) {
        super(id);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.name = name;

        piece = new HashMap<>();
        piece.put("j", Color.BLUEVIOLET);
        piece.put("l", Color.DARKGRAY );
        piece.put("o", Color.DEEPPINK);
        piece.put("s", Color.DODGERBLUE);
        piece.put("t", Color.GREENYELLOW);
        piece.put("z", Color.LIGHTCYAN);
        piece.put("i", Color.NAVY);


        color = piece.get(name);
        this.a.setFill(color);
        this.b.setFill(color);
        this.c.setFill(color);
        this.d.setFill(color);
    }


    public String getName() {
        return name;
    }


    public void changeForm() {
        form = form%4 + 1;
    }

}
