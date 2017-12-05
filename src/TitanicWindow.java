import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

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
    private String test_no_label_file_name =    "TestNoLabel.txt";
    private String predict_output_file_name =   "output.txt";
    private String model_file_name =            "model.txt";
    private String training_data_file_name =    "titanicData.csv";
    private JPanel panel;
    private JPanel panelS;

    public TitanicWindow() throws IOException {
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Titanic");

        generateModel();

        shipImage = new ImageIcon("titanic.jpg");

        Font f = new Font("Serif", Font.BOLD, 24);

        titleLabel = new JLabel("  WHO WILL SURVIVE?", shipImage, SwingConstants.CENTER);
        titleLabel.setFont(f);
        messageLabel = new JLabel("Enter your data to see survival prediction");
        textFieldLabel = new JLabel("Your Data: ");


        inputField = new JTextField(20);
        inputField.addActionListener(new InputListener());

        predictButton = new JButton("PREDICT");

        predictButton.addActionListener(new InputListener());

        panel = new JPanel();
        panel.add(titleLabel);
        panel.add(textFieldLabel);
        panel.add(inputField);
        panel.add(predictButton);

        panelS = new JPanel();
        panelS.add(messageLabel);
        panelS.setBackground(Color.YELLOW);

        this.add(panel);
        this.add(panelS, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void generateModel() throws IOException {
        NBTrain train = new NBTrain();
        String[] args = new String[2];
        args[0] = training_data_file_name;
        args[1] = model_file_name;
        train.main(args);
    }

    private class InputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = inputField.getText();
            try {
                scanAndReadFile(text);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        /**
         * This method exists because I didn't want to encase all of the above method in
         * a try-catch statement.
         *
         * @param text The text from the inpuField
         * @throws IOException For file shit
         */
        public void scanAndReadFile(String text) throws IOException {
            PrintWriter pw = new PrintWriter(test_no_label_file_name);
            pw.write("-1 ");
            pw.write(text);
            pw.close();

            String[] args = new String[2];
            args[0] = test_no_label_file_name;
            args[1] = predict_output_file_name;


            NBPredict p = new NBPredict();
            p.main(args);

            determineResult();
        }

        public void determineResult() throws IOException {
            File f = new File(predict_output_file_name);
            Scanner scan = new Scanner(f);
            int result = scan.nextInt();

            if(result==1) {
                panelS.setBackground(Color.GREEN);
                messageLabel.setText("YOU SURVIVED!");
            }
            if(result==0) {
                panelS.setBackground(Color.RED);
                messageLabel.setText("You Died XD");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new TitanicWindow();    }
}
