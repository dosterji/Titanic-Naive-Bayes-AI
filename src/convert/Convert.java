package convert;
//converting the Titanic data from excel to CSV format. 
import java.util.Scanner; 
import java.io.*;

public class Convert{
	public static void main(String[]args){
		//load file
		try{
			File f1 = new File("dataToFix2.txt"); 
			Scanner scan = new Scanner(f1); 
			
			 
			PrintWriter writer = new PrintWriter("titanicData2.csv"); 
			PrintWriter real = new PrintWriter("TestDataNoLabelTITANIC.txt"); 
			
			//Headers for each column
			writer.print("survived class sex age fare cabin\n"); 
			String lineswallow = scan.nextLine(); 
			
			while(scan.hasNext()){
			int pclass = scan.nextInt();
			int survive = scan.nextInt(); 
			int sex; 
			if(scan.next().equals("female")){
			 sex=1; }
			else{  sex = 0; } //male
				//System.out.println("Hallelujah!"); 
			
			String age = scan.next(); //here the ages are messed up. Round babies?
			System.out.println("In here"); 
			double fare = scan.nextDouble(); 
			System.out.println("Here too"); 
			String cabin = scan.next(); 
			
			//write data to our csv file 
			writer.print(survive+","+pclass+","+sex+","+age+","+fare+","+cabin+"\n");
			System.out.println("Yay!"); 
			real.print(survive+"\n"); 
			
			}
			writer.close(); 
			real.close(); 
		}catch(FileNotFoundException e){
			System.out.println("Error: dataToFix not found"); 
		}
		
}}