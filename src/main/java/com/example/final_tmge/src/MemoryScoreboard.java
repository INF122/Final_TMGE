package com.example.final_tmge.src;

public class MemoryScoreboard implements iScore {

    //MEMORY KEEPS TRACK OF MATCHES INSTEAD OF SCORES

    private int player1Matches = 0;
    private int player2Matches = 0;
    private int matchesLeft = 18;

    @Override
    public void updatePlayer1Score() {
        player1Matches++;
    }

    @Override
    public void updatePlayer2Score(){
        player2Matches++;
    }

    @Override
    public void resetScores() {
        player1Matches = 0;
        player2Matches = 0;
    }

    @Override
    public int checkWinCondition() {
        if (player1Matches > player2Matches){
            return 1; //PLAYER 1 WINS
        }
        else if (player1Matches < player2Matches){
            return 2; //PLAYER 2 WINS
        }
        return 0; //NOBODY WINS; TIE
    }

    @Override
    public int getPlayer1Score(){
        return player1Matches;
    }

    @Override
    public int getPlayer2Score(){
        return player2Matches;
    }

    public int getMatchesLeft(){
        return matchesLeft;
    }
    
    public void decrementMatchesLeft(){
        matchesLeft--;
    }
}
