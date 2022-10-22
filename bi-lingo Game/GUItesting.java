import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUItesting
{
    JFrame frame;
 
    GUItesting()
    {
        // creating instance of JFrame
        frame = new JFrame("Lingo");

        //welcome text
        JLabel welcome = new JLabel("What language would you like to use? ");
        welcome.setFont(new Font("Calibri", Font.BOLD, 20));
        welcome.setBounds(100, 20, 500, 100); // x, y, width, height

        String[] languages = {"spanish", "portugese", "czech", "italian", "french"};

        JButton button;
        JButton[] buttons = new JButton[5];
        for (int i = 0; i < 5; i++) {
            button = new JButton(languages[i]);
            button.setBounds(220, 100+60*i, 150, 50);
            //button.setBackground(new Color(28, 214, 81));
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                frame.dispose();
                //new GUIGame(englishWords, foreignWords);
                }
            });
            buttons[i] = button;
        }


        // flashcards.setBorderPainted(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(welcome);
        for (int i = 0; i< 5; i++) {
            frame.add(buttons[i]);
        }
        frame.setSize(600, 300);
        frame.setLayout(null);
        frame.setVisible(true);
    }
     
    public static void main(String[] args)
    {
        new GUILanguagePrompt();
    }
}