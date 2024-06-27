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

public class Snake{
    int head;
    int tail;

    Snake(int head, int tail){
        this.head = head;
        this.tail = tail;
    }

    void setTail(int tail){
        this.tail = tail;
    }
    void setHead(int head){
        this.head = head;
    }
    int getTail(){
        return this.tail;
    }
    int getHead(){
        return this.head;
    }
}