import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class GUILanguagePrompt
{
    JFrame frame;
    JButton[] buttons;
 
    GUILanguagePrompt()
    {
        // creating instance of JFrame
        frame = new JFrame("Lingo");

        //welcome text
        JLabel welcome = new JLabel("What Language Would you Like to Use? ");
        welcome.setFont(new Font("Calibri", Font.BOLD, 20));
        welcome.setBounds(100, 20, 500, 100); // x, y, width, height

        String[] languages = {"spanish", "portugese", "czech", "italian", "french"};

        JButton button;
        ClickListener click = new ClickListener();
        buttons = new JButton[5];
        for (int i = 0; i < 5; i++) {
            button = new JButton(languages[i]);
            button.setBounds(220, 100+60*i, 150, 50);
            // button.setBackground(new Color(28, 214, 81));
            button.setOpaque(true);
            button.addActionListener(click);
            buttons[i] = button;
        }


        // flashcards.setBorderPainted(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(welcome);
        for (int i = 0; i< 5; i++) {
            frame.add(buttons[i]);
        }
        frame.setSize(600, 500);
        frame.setLayout(null);
        frame.setVisible(true);

        
    }

    private class ClickListener implements ActionListener
    {
        // figure out which button was pressed and disable correct button
        public void actionPerformed(ActionEvent e)
        {
            for (int i = 0; i < 5; i++) {
                if (e.getSource() == buttons[i]) {
                    LingoWord words = new LingoWord();
                    try {
                        words.generateArrays(buttons[i].getText() + ".txt");
                        frame.dispose();
                        try {
                            new GUIGame(words.getForeignWords(), words.getEnglishWords());
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
     
    public static void main(String[] args)
    {
        new GUILanguagePrompt();
    }
}