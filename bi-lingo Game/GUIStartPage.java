import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIStartPage
{
    JFrame frame;
 
    GUIStartPage()
    {
        // creating instance of JFrame
        frame = new JFrame("Lingo");

        //welcome text
        JLabel welcome = new JLabel("Welcome to Lingo!");
        welcome.setFont(new Font("Calibri", Font.BOLD, 20));
        welcome.setBounds(200, 20, 200, 100); // x, y, width, height


        JButton flashcards = new JButton("Create Flashcards");
        flashcards.setBounds(220, 100, 150, 50);
        flashcards.setBackground(new Color(28, 214, 81));
        flashcards.setOpaque(true);
        flashcards.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               frame.dispose();
               new GUIFlashcards();
            }
         });

        JButton createCustomSet = new JButton("Use Random Set");
        createCustomSet.setBounds(220, 160, 150, 50);
        createCustomSet.setBackground(new Color(28, 214, 81));
        createCustomSet.setOpaque(true);
        createCustomSet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               frame.dispose();
               new GUILanguagePrompt();
            }
         });

        // flashcards.setBorderPainted(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(welcome);
        frame.add(flashcards);
        frame.add(createCustomSet);
        frame.setSize(600, 300);
        frame.setLayout(null);
        frame.setVisible(true);
    }
     
    public static void main(String[] args)
    {
        new GUIStartPage();
    }
}