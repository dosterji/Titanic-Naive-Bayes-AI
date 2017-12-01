package gui;

import javax.swing.*;
import java.awt.*;

/**
 * This class holds all of the other gui components
 */
public class TitanicWindow extends JFrame {

    private ImageIcon shipImage;
    private JButton startButton, predictButton;
    private JLabel titleLabel;
    private JTextField proportionField, inputField;
    private JTextArea resultsDisplay;

    public TitanicWindow() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Titanic");

        shipImage = new ImageIcon("titanic.jpg");

        Font f = new Font("Serif", Font.BOLD, 24);

        titleLabel = new JLabel("  WHO WILL SURVIVE?", shipImage, SwingConstants.CENTER);
        titleLabel.setFont(f);

        proportionField = new JTextField(20);
        startButton = new JButton("Train");

        inputField = new JTextField(20);

        predictButton = new JButton("PREDICT");


        JPanel panel = new JPanel();
        panel.add(titleLabel);
        panel.add(startButton);

        JPanel panelS = new JPanel();
        panelS.add(inputField);
        panelS.add(predictButton);

        this.add(panel);
        this.add(panelS, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TitanicWindow();
    }
}
