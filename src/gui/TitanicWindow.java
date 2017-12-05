package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class holds all of the other gui components
 */
public class TitanicWindow extends JFrame {

    private ImageIcon shipImage;        //Image of a boat
    private JButton predictButton;      //The button that does the prediction
    private JLabel titleLabel;          //The title label
    private JLabel messageLabel;        //The label that tells the user what's going on
    private JLabel textFieldLabel;      //The label for the text field
    private JTextField inputField;      //The textField

    public TitanicWindow() {
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Titanic");

        shipImage = new ImageIcon("titanic.jpg");

        Font f = new Font("Serif", Font.BOLD, 24);

        titleLabel = new JLabel("  WHO WILL SURVIVE?", shipImage, SwingConstants.CENTER);
        titleLabel.setFont(f);
        messageLabel = new JLabel("Enter your data to see survival prediction");
        textFieldLabel = new JLabel("Your Data: ");


        inputField = new JTextField(20);

        predictButton = new JButton("PREDICT");


        JPanel panel = new JPanel();
        panel.add(titleLabel);
        panel.add(textFieldLabel);
        panel.add(inputField);
        panel.add(predictButton);

        JPanel panelS = new JPanel();
        panelS.add(messageLabel);
        panelS.setBackground(Color.YELLOW);

        this.add(panel);
        this.add(panelS, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public static void main(String[] args) {
        new TitanicWindow();
    }
}
