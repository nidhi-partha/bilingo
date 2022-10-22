import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class GUIFlashcards {
    JFrame frame;
    GUIFlashcards()
    {
        frame = new JFrame("Create Flashcards");

        JLabel starter = new JLabel("Type up your flashcards.");
        starter.setFont(new Font("Calibri", Font.BOLD, 20));
        starter.setBounds(530, 20, 300, 50); // x, y, width, height

        // english text
        JLabel englishWord;
        JLabel[] englishWordsTexts = new JLabel[16];

        // english input
        JTextField englishInput;
        JTextField[] englishInputs = new JTextField[16];

        // foreign text
        JLabel foreignWord;
        JLabel[] foreignWordsTexts = new JLabel[16];

        // foreign input
        JTextField foreignInput;
        JTextField[] foreignInputs = new JTextField[16];
        int add = 0;
        int move = 0;
        for (int i = 0; i < 16; i++) {
            if (i%6==0 && i != 0) {
                add = 0;
                move += 400;
            }
            englishWord = new JLabel((i+1) + ". English Word:");
            englishWord.setBounds(100+move, 65+add*90, 150, 50);
            englishWordsTexts[i] = englishWord;

            englishInput = new JTextField();
            englishInput.setBounds(100+move, 105+add*90, 150, 50);
            englishInput.setMargin(new Insets(40, 40, 40, 40));
            englishInputs[i] = englishInput;

            foreignWord = new JLabel((i+1) + ". Foreign Word:");
            foreignWord.setBounds(275+move, 65+add*90, 150, 50);
            foreignWordsTexts[i] = foreignWord;

            foreignInput = new JTextField();
            foreignInput.setBounds(275+move, 105+add*90, 150, 50);
            foreignInput.setMargin(new Insets(40, 40, 40, 40));
            foreignInputs[i] = foreignInput;
            add++;
        }

        String[] foreignWords = new String[16];
        ArrayList<String> englishWords = new ArrayList<String>();
        JButton done = new JButton("Done");
        done.setBounds(1000, 465, 150, 50);
        done.setBackground(new Color(28, 214, 81));
        done.setOpaque(true);
        JLabel[] texts = new JLabel[1];
        texts[0] = new JLabel("");
        done.addActionListener(new ActionListener() {
            String english;
            String foreign;
            public void actionPerformed(ActionEvent e) {
                boolean works = true;
                for (int i = 0; i < 16; i++) {
                    english = englishInputs[i].getText();
                    foreign = foreignInputs[i].getText();
                    if (english.equals("") || foreign.equals("")) {
                        // texts[0] = new JLabel("Complete all flashcards");
                        // texts[0].setBounds(1000, 500, 150, 50);
                        System.out.println("Complete validation");// COMPLETE VALIDATION
                        // frame.add(texts[0]);
                        works = false;
                        break;
                    }
                    else {
                        englishWords.add(english);
                        foreignWords[i] = foreign;
                    }
                }
                if (works) {
                    frame.dispose();
                    try {
                        new GUIGame(foreignWords, englishWords);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(starter);
        for (int i = 0; i < 16; i++) {
            frame.add(englishWordsTexts[i]);
            frame.add(englishInputs[i]);
            frame.add(foreignWordsTexts[i]);
            frame.add(foreignInputs[i]);
        }
        frame.add(done);

        frame.setSize(1325, 700);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new GUIFlashcards();
    }
}
