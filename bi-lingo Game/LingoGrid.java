
import java.util.*;
public class LingoGrid {
    private String[] foreignWords;
    private String[][] grid;
    Random rand;

    public LingoGrid(String[] fw) {
        foreignWords = fw;
        grid = new String[4][4];
        rand = new Random();
    }

    public String[][] getGrid() {
        return grid;
    }

    public void createGrid() {
        String word;
        ArrayList<String> usedWords = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                word = foreignWords[rand.nextInt(16)];
                while (usedWords.contains(word)) {
                    word = foreignWords[rand.nextInt(16)];
                }
                usedWords.add(word);
                grid[i][j] = word;
            }
        }
    }

    public int findMaxWordLen() {
        int maxWordLen = 0;
        for (int i = 0; i < 16; i++) {
            if (foreignWords[i].length() > maxWordLen) maxWordLen = foreignWords[i].length();
        }
        return maxWordLen;
    }

    public void printGrid() {
        int maxWordLen = findMaxWordLen();
        System.out.print("|");
        for (int i = 0; i < (maxWordLen+3)*4+1; i++) {
            System.out.print("-");
        }
        System.out.print("|\n");
        for (int i = 0; i < 4; i++) {
            
            System.out.print("| ");
            for (int j = 0; j < 4; j++) {
                System.out.print(grid[i][j] + "   ");
                for (int k = 0; k < maxWordLen-grid[i][j].length(); k++) {
                    System.out.print(" ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.print("|");
        for (int i = 0; i < (maxWordLen+3)*4+1; i++) {
            System.out.print("-");
        }
        System.out.print("|\n");
    }

    public void set(int row, int col, String newWord) {
        grid[row][col] = newWord;
    }

    public String get(int row, int col) {
        return grid[row][col];
    }

    public boolean fourInARow() {
        // check rows
        int count;
        for (int i = 0; i < 4; i++) {
            count = 0;
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == "X") {
                    count++;
                }
            }
            if (count == 4) return true;
        }

        // check columns
        for (int i = 0; i < 4; i++) {
            count = 0;
            for (int j = 0; j < 4; j++) {
                if (grid[j][i] == "X") {
                    count++;
                }
            }
            if (count == 4) return true;
        }

        // check diagonals
        count = 0;
        for (int i = 0; i < 4; i++) {
            if (grid[i][i] == "X") count++;
        }
        if (count == 4) return true;

        count = 0;
        for (int i = 0; i < 4; i++) {
            if (grid[i][3-i] == "X") count++;
        }
        if (count == 4) return true;

        return false;
    }
}
