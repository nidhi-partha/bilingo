import java.io.IOException;
import java.util.*;


public class FinalProject {
    public static boolean checkAns(int row, int col, String lingoWord, String[][] foreignToEnglish, LingoGrid grid, LingoWord words) {
        row--;
        col--;
        String foreign = "";
        for (int i = 0; i < 16; i++) {
            if (foreignToEnglish[i][1] == lingoWord) {
                foreign = foreignToEnglish[i][0];
                break;
            }
        }
        if (grid.get(row, col) == foreign) {
            grid.set(row, col, "X");
            words.addCorrect(lingoWord);
            return true;
        }
        return false;
    }

    public static void playGame() throws IOException{
        Scanner in = new Scanner(System.in);

        System.out.println("\nLanguage Bingo!\n");
        System.out.print("What's your name? ");
        String name = in.nextLine();
        
        //Creating all necessary objects
        LingoPlayer player = new LingoPlayer(name);

        String playAgain = "y";
        while (playAgain.equals("y")) {
            playRound(player);
            System.out.print("Do you want to play again? (y/n) ");
            playAgain = in.next().toLowerCase();
            // validate
            while (!playAgain.equals("y") && !playAgain.equals("n")) {
                System.out.println("Please enter a valid response. (y/n) ");
                playAgain = in.next().toLowerCase();
            }
        }
        // in.close();
    }

    public static void playRound(LingoPlayer player) throws IOException {
        String[] foreignWords = new String[16];
        ArrayList<String> englishWords = new ArrayList<String>();
        String[][] foreignToEnglish = new String[16][2];
       
        // Intro 
        Scanner in = new Scanner(System.in);
        
        LingoWord words = new LingoWord();

        //Prompts player to pick between 3 modes
        System.out.print("Hi, "+ player.getName() +"! Do you want to play easy, medium, or hard? ");
        String level = in.nextLine().toLowerCase();

        //Verifies input
        while (!level.equals("hard") && !level.equals("easy") && !level.equals("medium")) {
            System.out.print("Please enter easy, medium, or hard: ");
            level = in.nextLine().toLowerCase();
        }

       //Asks user if they want to use automatically generated words
        System.out.print("Would you like to play custom or randomly generated (c/r): ");
        String cust_or_rand = in.nextLine().toLowerCase();

        while (!cust_or_rand.equals("c") && !cust_or_rand.equals("r")) {
            System.out.print("Enter a valid input (c/r): ");
            cust_or_rand = in.nextLine().toLowerCase();
        }

        if (cust_or_rand.equals("r")){
            System.out.println("Select your language option");
            System.out.println("Enter the number of the language you would like to use ");
            System.out.println("1 Spanish");
            System.out.println("2 Portuguese");
            System.out.println("3 French");
            System.out.println("4 Italian");
            System.out.println("5 Czech");

            System.out.print("Please enter the number: ");
            String num_lang = in.nextLine();

            while (!num_lang.equals("1") && !num_lang.equals("2") && !num_lang.equals("3") && !num_lang.equals("4") && !num_lang.equals("5")) {
                System.out.print("Please enter a valid number: ");
                num_lang = in.nextLine();
            }
            
            if(num_lang.equals("1"))
            {
                words.generateArrays("spanish.txt");
            }

            else if(num_lang.equals("2"))
            {
                words.generateArrays("portugese.txt");
            }

            
            else if(num_lang.equals("3"))
            {
                words.generateArrays("french.txt");
            }

            
            else if(num_lang.equals("4"))
            {
                words.generateArrays("italian.txt");
            }

            
            else if(num_lang.equals("5"))
            {
                words.generateArrays("czech.txt");
            }

            foreignWords = words.getForeignWords();
            englishWords = words.getEnglishWords();

            for (int i = 0; i < 16; i++) {
                foreignToEnglish[i][0] = foreignWords[i];
                foreignToEnglish[i][1] = englishWords.get(i);
            }
        }
            
        else {
            //asks player to enter 16 words, first in english and then in the language of their choice
            System.out.println("Let's get started!");
            System.out.println("Please input your 16 words and meanings below in the following format (english - language):");
            for (int i = 0; i < 16; i++) {
                // add validation that formatted correctly
                String foreign = in.next();
                in.next();
                String english = in.next();
                foreignWords[i] = foreign;
                englishWords.add(english);
                foreignToEnglish[i][0] = foreign;
                foreignToEnglish[i][1] = english;
            }
            words.generateCustomEnglishArray(englishWords);
        }

        // start game
        int time;
        if (level.equals("hard")) time = 2;
        else if (level.equals("medium")) time = 4;
        else time = 10;

        System.out.println("Your time starts now! You have "+time+" minutes.");
        
        LingoGrid grid = new LingoGrid(foreignWords);
        grid.createGrid();

        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        int wrong = 0; 
        int count = 0;
        boolean won = false;
        while (elapsedTime < time*60*1000) {
            grid.printGrid();
            String lingoWord = words.generateRandomWord();
            System.out.println("Your word is " + lingoWord);
            System.out.print("Please enter the row and column (row col): ");
            int row = 0;
            int col = 0;
            boolean right = false;
            while (!right) {
                try {
                    row = in.nextInt();
                    col = in.nextInt();
                    right = true;
                } catch (Exception e) {
                    System.out.print("please enter a valid row and col: ");
                    in.nextLine();
                }
            }
            
            while (!(row > 0 && row < 5) || !(col > 0 && col < 5)) {
                System.out.print("Enter a valid row and column (1-4): ");
                row = in.nextInt();
                col = in.nextInt();
            }
            Boolean correct = checkAns(row, col, lingoWord, foreignToEnglish, grid, words); //finish checkAns
            if (correct) System.out.println("That is correct!");
            else {
                System.out.println("That is incorrect! Your accuracy has gone down.");
                wrong++;
            }
            count++;  
            elapsedTime = (new Date()).getTime() - startTime;
            won = grid.fourInARow();
            if (won) {
                grid.printGrid();
                System.out.println("\n-------\nYou got four in a row!\n-------\n");
                break;
            }
        }
        // in.close();
        if (!won) {
            System.out.println();
            System.out.println("---------");
            System.out.println("You have run out of time!");
            System.out.println("---------");
            System.out.println();
        }

        player.updateStats(won, wrong, count);
        player.displayStats();

    }

    public static void main(String[] args) throws IOException{
        //Intro
        System.out.println("Welcome to...\n");
        System.out.println("|||||           |||||||||||||||    ||||||||||     |||||    ||||||||||||||||||    |||||||||||||||");
        System.out.println("|||||           |||||||||||||||    ||||||||||     |||||    ||||||||||||||||||    |||||||||||||||");
        System.out.println("|||||                |||||         ||||| |||||    |||||    |||||                 |||||     |||||");
        System.out.println("|||||                |||||         |||||  |||||   |||||    |||||   ||||||||||    |||||     |||||");
        System.out.println("|||||                |||||         |||||   |||||  |||||    |||||   ||||||||||    |||||     |||||");
        System.out.println("|||||                |||||         |||||    ||||| |||||    |||||        |||||    |||||     |||||");
        System.out.println("||||||||||||    |||||||||||||||    |||||     ||||||||||    ||||||||||||||||||    |||||||||||||||");
        System.out.println("||||||||||||    |||||||||||||||    |||||     ||||||||||    ||||||||||||||||||    |||||||||||||||");
        
        //Asks if user wants console or gui version
        Scanner scanning_thing = new Scanner(System.in);
        System.out.print("First, would you like to play GUI or console (g or c): ");
        String gui_or_console = scanning_thing.nextLine();

        if (gui_or_console.equals("g")) new GUIStartPage();
        else playGame();

    }
}
