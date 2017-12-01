import java.util.Scanner; 
import java.io.*;
//evaluate the performance of our Titanic survival prediction program
public class TitanicEvaluation{
	public static void main(String[]args){
		//load files
		try{
			File f1 = new File("DTOut.txt"); //put file to read here
			Scanner scan = new Scanner(f1); 
			
			File f2 = new File ("TestDataNoLabelTITANIC.txt"); 
			Scanner scanner = new Scanner(f2); 
			
			int rightCounter = 0; 
			int total = 0; 
			int truerightcounter = 0; 
			int truenegativecounter = 0;
			int falsepositive = 0; 
			int falsenegative = 0; 
			
			while(scan.hasNext() && scanner.hasNext()){
				int mine = scan.nextInt();
				int theirs = scanner.nextInt(); 
				if(mine == theirs){
					//if the prediction is the same as the testdatalabel
					rightCounter++; 
					//if true right
					if(mine == 1){
					truerightcounter++; }
					else{ truenegativecounter++; }
				}
				if(mine ==1 && theirs==0){
					falsepositive++; 
				}
				else if(mine ==0 && theirs==1){
					falsenegative++; 
				}
				total++; 
			}
			//calculate accuracy percentage, etc and print
			double accuracy = (double)rightCounter / (double)total; 
			System.out.println("The accuracy is " + accuracy*((double)100) + " percent!"); 
			
			double recall = (double)truerightcounter / ((double)truerightcounter + (double)falsenegative);
			double precision = (double)truerightcounter / ((double)truerightcounter+(double)falsepositive); 
			double specificity= (double) truenegativecounter / ((double)truenegativecounter + (double)falsepositive); 
			double fscore = 2.0 * ((precision*recall)/(precision+recall));
			
			PrintWriter writer = new PrintWriter("TitanicEvals.txt"); 
			writer.print(truerightcounter + "\t" + falsenegative + "\n" + falsepositive + "\t" + truenegativecounter); 
			writer.print("\n"); 
			writer.print("Recall: " + recall + "\tPrecision: " + precision + "\tSpecificity: " + specificity + "\tFscore: " + fscore); 
			
			System.out.println("Evaluation Complete."); 
			
			}catch(FileNotFoundException ex){ 
				System.out.println("unable to open file");
			}	
			
}}