import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class GUIGame {
    // create necessary variables that must be used in all methods
    private String[] foreignWords;
    private ArrayList<String> englishWords;
    private String[][] foreignGrid;
    private String[][] foreignToEnglish;
    private JFrame frame;
    private JButton[] buttons;
    private String lingoWord;
    private LingoWord words;

    // returns if the answer was correct and the correct foreignWord translation in case they got it wrong
    public String[] correct(String englishWord, String foreignWord, String[][] foreignToEnglish) {
        String[] ans = new String[2];
        for (int i = 0; i < 16; i++) {
            if (foreignToEnglish[i][1] == englishWord) {
                ans[1] = foreignToEnglish[i][0];
                if (foreignToEnglish[i][0] == foreignWord) {
                    ans[0] = "correct";
                    return ans;
                }
            }
        }
        ans[0] = "incorrect";
        return ans;
    }

    // checks if player won four in a row
    public boolean fourInARow() {
        // check rows
        int count;
        for (int i = 0; i < 4; i++) {
            count = 0;
            for (int j = 0; j < 4; j++) {
                if (foreignGrid[i][j] == "X") {
                    count++;
                }
            }
            if (count == 4) return true;
        }

        // check columns
        for (int i = 0; i < 4; i++) {
            count = 0;
            for (int j = 0; j < 4; j++) {
                if (foreignGrid[j][i] == "X") {
                    count++;
                }
            }
            if (count == 4) return true;
        }

        // check diagonals
        count = 0;
        for (int i = 0; i < 4; i++) {
            if (foreignGrid[i][i] == "X") count++;
        }
        if (count == 4) return true;

        count = 0;
        for (int i = 0; i < 4; i++) {
            if (foreignGrid[i][3-i] == "X") count++;
        }
        if (count == 4) return true;

        return false;
    }

    // constructor
    public GUIGame(String[] fw, ArrayList<String> ew) throws InterruptedException {
        //instance variable
        frame = new JFrame("Lingo");
        foreignWords = fw;
        englishWords = ew;


        //welcome text
        JLabel welcome = new JLabel("Lets Play Lingo!");
        welcome.setFont(new Font("Calibri", Font.BOLD, 20));
        welcome.setBounds(350, 20, 200, 100); // x, y, width, height

        // create foreignToEnglish Dictionary
       foreignToEnglish = new String[16][2];
        for (int i = 0; i < 16; i++) {
            foreignToEnglish[i][0] = foreignWords[i];
            foreignToEnglish[i][1] = englishWords.get(i);
        }

        // create the grid
        JButton button;
        buttons = new JButton[16];
        int xpos = 220;
        LingoGrid grid = new LingoGrid(foreignWords);
        grid.createGrid();
        foreignGrid = grid.getGrid();
        int x = 0;

        ClickListener click = new ClickListener();
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                button = new JButton(foreignGrid[i][j]);
                button.setBounds(xpos, 100+105*j, 100, 100);
                button.addActionListener(click);
                buttons[x] = button;
                x++;
            }
            xpos += 105;
        }

        // add necessary parts to frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(welcome);
        for (int i = 0; i < 16; i++) {
            frame.add(buttons[i]);
        }

        // start game
        words = new LingoWord();
        words.generateCustomEnglishArray(englishWords);
        JLabel word = new JLabel();
        lingoWord = words.generateRandomWord();
        word.setText("Your word is " + lingoWord + ".");
        word.setFont(new Font("Calibri", Font.BOLD, 15));
        word.setBounds(340, 490, 200, 100);

        JButton next = new JButton("Next");
        next.setBounds(350, 565, 150, 50);
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 16; i++) {
                    buttons[i].setEnabled(true);
                }
                lingoWord = words.generateRandomWord();
                word.setText("Your word is " + lingoWord + ".");
                if (fourInARow()) {
                    frame.dispose();
                    new GUIWon();
                }
            }
         });

         // continue adding necessary parts to frame
        frame.add(word);
        frame.add(next);
        frame.setSize(850, 700);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private class ClickListener implements ActionListener
    {
        // figure out which button was pressed and disable correct button
        public void actionPerformed(ActionEvent e)
        {
            String[] ans;
            for (int i = 0; i < 16; i++) {
                if (e.getSource() == buttons[i]) {
                    ans = correct(lingoWord, buttons[i].getText(), foreignToEnglish);
                    if (ans[0] == "correct") {
                        words.addCorrect(lingoWord);
                        for (int j = 0; j < 4; j++) {
                            for (int x = 0; x < 4; x++) {
                                if (foreignGrid[j][x] == buttons[i].getText()) {
                                    foreignGrid[j][x] = "X";
                                    break;
                                }
                            }
                        }
                        buttons[i].setText("X");
                    }
                    else {
                        for (int j = 0; j < 16; j++) {
                            if (buttons[j].getText() == ans[1]) {
                                buttons[j].setEnabled(false);
                            }
                        }
                        
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String[] foreignWords = {"uno", "dos", "tres" , "cuatro", "cinco", "seis", "siete", "ocho", "nueve", "diez", "once", "doce", "trece", "catorce", "quince", "dieciseis"};
        String[] ew = {"one", "two","three", " four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen"};
        ArrayList<String> englishWords = new ArrayList<String>();
        for (int i = 0; i < 16; i++) {
            englishWords.add(ew[i]);
        }        
        new GUIGame(foreignWords, englishWords);
    }
    
}
