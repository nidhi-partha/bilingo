import java.util.*;

public class LingoPlayer {
    private String name; //Player name
    private int gamesPlayed; //total games played
    private int gamesWon; //Amount of games player has one
    private double accuracy;  //Percent ones
    private int numCorrect;
    private int totalCorrect;
    //private Integer maxCorrectGuess;  //Maximum of correct guesses that updates each round
    //private ArrayList<Integer> correctGuessList; //ArrayList of correct guesses (only after winning round)
    
    public LingoPlayer(String n)  //Constructor
    {
        name = n;
        gamesPlayed = 0;
        gamesWon = 0;
        accuracy = 0.0;
        totalCorrect=0;
        numCorrect=0;
        //maxCorrectGuess = 0;
        //correctGuessList = new ArrayList<Integer>(400);
        
    }

    public String getName() {
        return name;
    }

    public void updateStats(boolean won, int numIncorrect, int guesses)  //Updates stats
    {
        numCorrect = guesses-numIncorrect;
        totalCorrect+=numCorrect;
        gamesPlayed++; //Total games played that is accumulated through playing the game mulitple times
        if (won==true) //If won ...got a bingo and did not go over time limit
        { 
            gamesWon++;
        }
        /*    
        if (numCorrect>maxCorrectGuess){ //To get max (number of correctguesses per game) for the display stats number of tries chart
            maxCorrectGuess=numCorrect;}

        for (int x=0; x<maxCorrectGuess; x++){ //Array list of numbers of correct guesses to win the game
            correctGuessList.add(0);}
        correctGuessList.set(numCorrect-1, (correctGuessList.get(numCorrect-1))+1); //Updates correct guesses by adding one to number of guesses
        */
        accuracy=(numCorrect*1.0/guesses)*100; //Percentage of correct/total        
    }

    public void displayStats() 
    {
        
        System.out.println(name+", you had an accuracy of "+String.format("%,.2f", accuracy)+"% this round.");
        System.out.println("You have won "+gamesWon+"/"+gamesPlayed+" rounds so far and your win rate of this game is "+String.format("%,.2f", (gamesWon*1.0/gamesPlayed)*100)+"%");
        
        if (numCorrect!=1)
        {
            System.out.println("You have "+numCorrect+" correct guesses this round and "+totalCorrect+" correct guesses in total.");
        }
        else if (numCorrect==1)
        {
            System.out.println("You had "+numCorrect+" correct guess this round and "+totalCorrect+" correct guesses in total.");
        }
        
    }

    
    //Test code
    public static void main(String[] args)
    {   
        LingoPlayer player = new LingoPlayer("Bob");
        player.updateStats(false,3,4); 
        player.displayStats();
        System.out.println();
        player.updateStats(false,10,12);
        player.displayStats();
        System.out.println();
        player.updateStats(true, 5,8); //numCorrect=3
        player.displayStats();
        System.out.println();
        player.updateStats(true,0,6); //numCorrect=6
        player.displayStats();
        System.out.println();
        player.updateStats(false,10,10);
        player.displayStats();
        System.out.println();
        player.updateStats(true,1,7); //numCorrect=6
        player.displayStats();
        System.out.println();
        player.updateStats(true, 5,8); //numCorrect=3
        player.displayStats();
        System.out.println();
        player.updateStats(true, 4,11); //numCorrect=7
        player.displayStats();
    }
}