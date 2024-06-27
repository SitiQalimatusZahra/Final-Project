/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Final Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : Q
 * Group    : 09
 * Members  :
 * 1. 5026231063 - Aulia Salma Anjani
 * 2. 50262310.. - Siti Qalimatus Zahra
 * 3. Student ID - Full Name
 * ------------------------------------------------------
 */

import java.util.ArrayList;

public class Player{
    //states
    private String name;
    private int position;

    //constructor method
    public Player (String name){
        this.name=name;
    }
    //setter methods

    public void setName (String name){
        this.name=name;
    }

    public void setPosition(int position){
        this.position = position;
    }

    //getter methods
    public String getName() {
        return this.name;
    }


    public int getPosition() {
        return this.position;
    }

    //another method
    public int rollDice()
    {
        return (int)((Math.random()*6)+1);
    }

    public void moveAround(int x, int boardSize)
    {
        if (this.position + x > boardSize){
            this.position = (boardSize - this.position) + (boardSize - x);
        }
        else {
            this.position = this.position + x;

        }
    }

    //for player who get Golden Ladder
    public boolean useGoldenLadder(ArrayList<Ladder> ladders) {
        for (Ladder l : ladders) {
            if (l.getFromPosition() > this.position) {
                this.position = l.getToPosition();
                return true;
            }
        }
        return false;
    }
}