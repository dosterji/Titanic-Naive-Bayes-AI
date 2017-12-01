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
    /**
     * main method.  MODEL MUST BE WRITTEN TO MODEL.TXT
     * @param args commandLine args
     *             1st: test_file
     *             2nd: output_file
     */
    public static void main(String[] args) throws IOException {
        NBPredict nbp = new NBPredict();
        nbp.predict(args[0], args[1]);
    }

    private double yes, no;
    private double[][] bClass, sex, age, cabin;   //The tables for each variable

    ///////////////
    //CONSTRUCTOR//
    ///////////////
    public NBPredict() throws IOException {
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

        bClass = new double[3][2];
        for(int i=0; i<3; i++) {
            bClass[i][0] = scan.nextDouble();
            bClass[i][1] = scan.nextDouble();
        }

        sex = new double[2][2];
        for(int i=0; i<2; i++) {
            sex[i][0] = scan.nextDouble();
            sex[i][1] = scan.nextDouble();
        }

        age = new double[10][2];
        for(int i=0; i<10; i++) {
            age[i][0] = scan.nextDouble();
            age[i][1] = scan.nextDouble();
        }

        cabin = new double[4][2];
        for(int i =0; i<4; i++) {
            cabin[i][0] = scan.nextDouble();
            cabin[i][1] = scan.nextDouble();
        }

        yes = scan.nextDouble();
        no = scan.nextDouble();

        toString(bClass);
        toString(sex);
        toString(age);
        toString(cabin);
    }

    /**
     * Does the actual prediction.
     * @param testFile The name of the file containing testing data
     * @param outputFile The name of the file this program should write to
     * @throws IOException Throw it away
     */
    public void predict(String testFile, String outputFile) throws IOException {
        File f = new File(testFile);
        Scanner scan = new Scanner(f);
        PrintWriter pw = new PrintWriter(outputFile);

        scan.nextLine();    //Ditch the first two lines
        scan.nextLine();

        while(scan.hasNext()) {
            int b, s, a, c;         //variables representing each value
            char ch;                //for reading the cabin
            String cab;             //same as above
            scan.nextInt();         //get rid of the -1

            System.out.println("WOO");
            b = scan.nextInt();
            s = scan.nextInt();
            a = (int) scan.nextDouble();
            scan.nextDouble();         //fuck the fare

            cab = scan.next();
            ch = cab.charAt(0);

            //find cabin
            switch(ch){
                case'A':
                    c=0;
                    break;
                case'B':
                    c=1;
                    break;
                case'C':
                    c=2;
                    break;
                case'D':
                    c=3;
                    break;
                default:
                    c=0;
                    break;
            }

            double y=0, n=0;

            System.out.println(a/10);
            y = bClass[b-1][0]*sex[s][0]*age[a/10][0]*cabin[c][0]*yes;
            n = bClass[b-1][1]*sex[s][1]*age[a/10][1]*cabin[c][1]*no;

            System.out.println(n);
            if(y>n)
                pw.append("1\n");
            else
                pw.append("0\n");
        }
        pw.close();
    }

    public void toString(double[][] array) {
        String s = "";
        for(int i=0; i<array.length; i++) {
            for(int j=0; j<array[0].length; j++) {
                s += String.format("%4.4f ", array[i][j]);
            }
            s += "\n";
        }
        System.out.println(s);
    }
}
