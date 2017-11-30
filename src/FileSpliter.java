import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is a class that will take an input file (the titanic data) and
 * a percentage of the data to train on.  It will then split in the input file's data
 * into three files: TitanicTrain.txt, TitanicTestNoLabel.txt, and TitanicTestLabel.txt.
 *
 * TitanicTrain.txt
 *      -The file that contains the amount of the data to be trained on.
 * TitanicTestNoLabel.txt
 *      -Contains the data to test the model on.  Will leave off whether or not a person lived or died
 * TitanicTestLabel.txt
 *      -Contains whether the person actually lived or died.  The answers, basically
 *
 * These files will then be used by the other parts of this project.
 */
public class FileSpliter {
    private int proportion;     //The portion of the data to be trained on (a integer between 1-100).
    private String fileName;    //Name of the input file.

    public FileSpliter(String fileName, int proportion) {
        if(proportion>100 || proportion<1)
            throw new IllegalArgumentException();
        this.proportion = proportion;
        this.fileName = fileName;
    }


    /**
     * Method the does the file splitting.
     * @throws IOException
     */
    public void split() throws IOException {
        File f = new File(fileName);
        Scanner scan = new Scanner(f);

        int n = 0;      //number of entries.
        scan.nextLine();
        scan.nextLine();

        //Loop for discovering how large the file is
        while(scan.hasNextLine()) {
            n++;
            scan.nextLine();
        }

        System.out.println(n);
        n = (int) (n * ((double)proportion/100));   //To find the total number of lines needed.
        System.out.println(n);

        //TODO: create a loop here that loops back through the input file and splits up the files.

        //TODO: Find a way to get people for each boarding class into the randomly sampled data.

    }
}
