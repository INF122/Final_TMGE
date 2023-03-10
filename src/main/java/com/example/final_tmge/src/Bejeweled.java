package com.example.final_tmge.src;

public class Bejeweled extends TMGE{

    // Gem pieces class inherited from GamePiece, might add more functionality as needed.
    public class Gem extends GamePiece{

        String imagePath;

        Gem(String name, String path){
            super(name);
            this.imagePath = path;
        }

        @Override
        public String pngPath(){
            return imagePath;
        }

    }

    /**
        gem pieces
     */



    BejeweledBoard board = new BejeweledBoard(10, 10);
    public Bejeweled(String name, Player p1, Player p2) {
        super(name, p1, p2);
    }

}