
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIWon
{
    JFrame frame;
 
    GUIWon()
    {
        // creating instance of JFrame
        frame = new JFrame("Lingo");

        //welcome text
        JLabel welcome = new JLabel("You Won! Do you want to play again?");
        welcome.setFont(new Font("Calibri", Font.BOLD, 20));
        welcome.setBounds(100, 20, 500, 100); // x, y, width, height


        JButton flashcards = new JButton("Yes");
        flashcards.setBounds(220, 100, 150, 50);
        flashcards.setBackground(new Color(28, 214, 81));
        flashcards.setOpaque(true);
        flashcards.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               frame.dispose();
               new GUIStartPage();
            }
         });

        // flashcards.setBorderPainted(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(welcome);
        frame.add(flashcards);
        // frame.add(button);
        frame.setSize(600, 250);
        frame.setLayout(null);
        frame.setVisible(true);
    }
     
    public static void main(String[] args)
    {
        new GUIWon();
    }
}