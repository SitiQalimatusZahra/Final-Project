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
 * 2. 5026231057 - Siti Qalimatus Zahra
 * 3. Student ID - Full Name
 * ------------------------------------------------------
 */

public class Ladder{
    int fromPosition;
    int toPosition;

    Ladder(int from, int to){
        this.fromPosition = from;
        this.toPosition = to;
    }

    void setFromPosition(int from){
        this.fromPosition =  from;

    }

    void setToPosition(int to){
        this.toPosition = to;

    }

    int getFromPosition(){
        return fromPosition;

    }
    int getToPosition(){
        return toPosition;

    }
}