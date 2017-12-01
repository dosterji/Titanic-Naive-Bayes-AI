import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This program does the actual prediction using the Naive Bayes model
 *
 * @author dosterji
 */
public class NBPredict {
    private int num, yes, no;
    private double[][] bClass, sex, age, fare, cabin;   //The tables for each variable

    ///////////////
    //CONSTRUCTOR//
    ///////////////
    public NBPredict() throws IOException {
        //TODO: This method will instantiate the tables for each variable
        tableInit("model.txt");
    }

    //////////////////////
    //FUNCTIONAL METHODS//
    //////////////////////
    /**
     * Method for initializing the data inside each table;
     * @param modelFile The file containing the model
     * @throws IOException Throw it away
     */
    public void tableInit(String modelFile) throws IOException {
        File f = new File(modelFile);
        Scanner scan = new Scanner(f);

        num = scan.nextInt();
        yes = scan.nextInt();
        no = scan.nextInt();

        //TODO: initialize each table with data from the model
    }

    /**
     * Does the actual prediction.
     * @param testFile The name of the file containing testing data
     * @param outputFile The name of the file this program should write to
     * @throws IOException Throw it away
     */
    //TODO: This method or another one should figure out how much of the test file to use (percentage)
    public void predict(String testFile, String outputFile) throws IOException {
        File f = new File(testFile);
        Scanner scan = new Scanner(f);
        PrintWriter pw = new PrintWriter(outputFile);

        while(scan.hasNext()) {
            //TODO: do the writing to the file.  Will write either a 1 or 0 for each line of the test file.
        }

        pw.close();
    }
}
