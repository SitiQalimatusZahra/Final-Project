import java.util.ArrayList;
import java.util.Scanner;

public class SnL {

    //states, variable, or properties
    private int boardSize;
    private ArrayList<Player> players;
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private int gameStatus;
    private int currentTurn;
    private boolean vsComputer; //for 1 player only

    //constructor
    public SnL (int size){
        this.boardSize = size;
        this.snakes = new ArrayList<Snake>();
        this.ladders = new ArrayList<Ladder>();
        this.players= new ArrayList<Player>();
        this.gameStatus = 0;
        this.vsComputer = false; //for 1 player only
    }

    public void initiateGame(){
        int [][] ladders =
                {    {2, 23},
                        {8, 34},
                        {20, 77},
                        {32,68},
                        {41, 79},
                        {74, 88},
                        {82, 100},
                        {85, 95}
                };
        setLadders(ladders);
        int [][] snakes =
                {    {47, 5},
                        {29, 9},
                        {38, 15},
                        {97,25},
                        {53, 33},
                        {92, 70},
                        {86, 54},
                        {97, 25}
                };
        setSnakes(snakes);

    }

    public Player getTurn() {
        if (this.gameStatus == 0) {
            double r = Math.random();
            if (r < 0.5){
                this.currentTurn = 0;
                return this.players.get(0);
            }
            else {
                this.currentTurn = 1;
                return this.players.get(1);
            }


        }
        else {
            if (currentTurn == 0) {
                this.currentTurn = 1;
                return this.players.get(1);
            }

            else {
                this.currentTurn = 0;
                return this.players.get(0); }
        }
    }

    //setter methods
    public void setSizeBoard(int size){
        this.boardSize = size;
    }
    public void addPlayer(Player p){
        this.players.add(p);
    }
    public void setLadders(int[][] ladders){
        int s = ladders.length;
        for(int i = 0; i < s; i++){
            this.ladders.add(new Ladder(ladders[i][0],ladders[i][1]));
        }
    }

    public void setSnakes(int[][] snakes)
    {
        int s = snakes.length;
        for(int i = 0; i < s; i++){
            this.snakes.add(new Snake(snakes[i][0],snakes[i][1]));
        }
    }

    public int getBoardSize(){
        return this.boardSize;
    }
    public ArrayList<Player> getPlayers(){
        return this.players;
    }
    public ArrayList<Snake> getSnakes(){
        return this.snakes;
    }
    public ArrayList<Ladder> getLadders(){
        return this.ladders;
    }

    public int getGameStatus(){
        return this.gameStatus;
    }
    public void play(){
        initiateGame();

        Scanner sc= new Scanner(System.in);
        System.out.println("enter first player name:");
        String firstPlayer= sc.nextLine();

        //Choice for 1 player only
        System.out.println("Do you want to play versus the computer? (yes/no):");
        String versusComputer = sc.nextLine().toLowerCase();

        addPlayer(new Player(firstPlayer));
        if (versusComputer.equals("yes")) {
            this.vsComputer = true;
            addPlayer(new Player("Computer"));
        } else {
            System.out.println("enter second player name:");
            String secondPlayer= sc.nextLine();
            addPlayer(new Player(secondPlayer));
        }

        Player nowPlaying;
        do{
            System.out.println("----------------------------------------------");
            nowPlaying = getTurn();
            System.out.println("Now Playing: "+ nowPlaying.getName()+" the current position is "+nowPlaying.getPosition());

            //
            if (nowPlaying.getName().equals("Computer")) {
                System.out.println("Computer is rolling the dice...");
                int x = nowPlaying.rollDice();
                System.out.println("Computer rolled a " + x);
                movePlayer(nowPlaying, x);
            } else {
                System.out.println(nowPlaying.getName()+" it's your turn, please press enter to roll dice");
                String input= sc.nextLine();
                int x = 0;
                if (input.isEmpty()){
                    x = nowPlaying.rollDice();
                }

                System.out.println(nowPlaying.getName()+ " is rolling dice and get number: "+x);
                movePlayer(nowPlaying, x);
                System.out.println(nowPlaying.getName()+ " new position is "+ nowPlaying.getPosition());
            }
        } while(getGameStatus()!=2);

        System.out.println("The Game is Over, the winner is: "+nowPlaying.getName());
    }

    public void movePlayer(Player p, int x){
        this.gameStatus=1;
        p.moveAround(x, this.boardSize);

        //Check if the player rolled a 3 and chooses to use Golden Ladder
        if (x == 3) {
            System.out.println(p.getName() + " you rolled a 3! Do you want to use Golden Ladder? (yes/no)");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine().toLowerCase();
            if (choice.equals("yes\no") || choice.equals("yes")) {
                if (p.useGoldenLadder(this.ladders)) {
                    System.out.println(p.getName() + " used Golden Ladder and jumps to " + p.getPosition());
                } else {
                    System.out.println("No ladder ahead to use.");
                }
            }
        }


        for(Ladder l: this.ladders){
            if(l.getFromPosition() == p.getPosition()){
                p.setPosition(l.getToPosition());
                System.out.println(p.getName()+" got ladder so jumps to "+p.getPosition());
            }
        }

        for(Snake s: this.snakes){
            if(s.getHead() == p.getPosition()){
                p.setPosition(s.getTail());
                System.out.println(p.getName()+" got snake so slide down to "+p.getPosition());
            }
        }

        if (p.getPosition()==this.boardSize){
            this.gameStatus=2;
        }
    }
}
