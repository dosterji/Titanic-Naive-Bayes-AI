
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NBTrain {
	//array for each variable
	private static double[][] boardingClass = new double[2][3];
	private static double[][] gender = new double[2][2];
	private static double[][] age = new double[2][10];
	private static double[][] fare = new double[2][21];
	private static double[][] cabin = new double[2][4];
	
	private static double prob_survive = 0;
	
	/**
	 * first argument is the file to be read "titanicData.csv"
	 * second is name of file to output to
	 */
	public static void main(String[] args) throws IOException {

		String fName = args[0];
		
		read(fName);
		
		write(args[1]);
		
	}

	private static void read(String fName) throws FileNotFoundException  {
		File file = new File(fName);
		Scanner fScan = new Scanner(file);
		
		//skip the text at the top
		System.out.println(fScan.nextLine());
		System.out.println(fScan.nextLine());
		
		
				
		
		//add up the successes for each value.
		double total_count = 0, yes_count = 0;
		
		int surv_input = 0, board_input = 0, gend_input = 0, age_input = 0, fare_input = 0;
		char cabin_input;
		
		String input_line = "";
		ArrayList<String>attribute_list;
		while( fScan.hasNext() ){
			input_line = fScan.nextLine();
			
			//Separate the attributes
			attribute_list = new ArrayList<String>(Arrays.asList(input_line.split(",")));
		
			surv_input= Integer.parseInt(attribute_list.get(0));
			board_input= Integer.parseInt(attribute_list.get(1));
			gend_input= Integer.parseInt(attribute_list.get(2));
			try{
				age_input = (int)Double.parseDouble(attribute_list.get(3));
			}catch(Exception e) { // if a is n, they were a baby
				age_input = 0;
			}
			fare_input = (int)Double.parseDouble(attribute_list.get(4));
			cabin_input =attribute_list.get(5).charAt(0);

			//put into the attribute arrayLists
			if(surv_input== 1){	//survived, 
				
				//find boarding class
				boardingClass[1][board_input- 1]++;
				
				//find gender (1 = female)
				gender[1][gend_input]++;
				
				//find age (separated by decades)
				age_input = age_input / 10;
				
				age[1][age_input]++;
								
				//find fare
				fare_input = fare_input / 25;
				
				fare[1][fare_input]++;
				
				//find cabin
				switch(cabin_input){
					case'A':
						cabin[1][0]++;
						break;
					case'B':
						cabin[1][1]++;
						break;
					case'C':
						cabin[1][2]++;
						break;
					case'D':
						cabin[1][3]++;
						break;
					default:
						//don't add to anything
						break;
				}
				
				
				yes_count++;
			}else{						//didn't survive
				
				
				//find boarding class
				boardingClass[0][board_input- 1]++;
				
				//find gender (1 = female)
				gender[0][gend_input]++;
				
				//find age (separated by decades)
				age_input = age_input / 10;
				age[0][age_input]++;
								
				//find fare
				fare_input = fare_input / 25;
				
				fare[0][fare_input]++;
				
				//find cabin
				switch(cabin_input){
					case'A':
						cabin[0][0]++;
						break;
					case'B':
						cabin[0][1]++;
						break;
					case'C':
						cabin[0][2]++;
						break;
					case'D':
						cabin[0][3]++;
						break;
					default:
						//don't add to anything
						break;
				}
			}
			total_count++;
		}
		
		//find base probabilities 
		prob_survive = yes_count / total_count;
		
		double attr_total = 0;
		
		//find ratios for each number
		for(int i = 0; i < 2; i++){ 	
			attr_total = 0;
			//DONT ADD YES AND NO, ADD UP ATTRIBUTES BY YES'S AND NO'S
			
			//get totals for no then yes
			for(int j = 0; j < boardingClass[0].length; j++){
				attr_total += boardingClass[i][j];
			}
			//System.out.println(attr_total);
			for(int j = 0; j < boardingClass[0].length; j++){
				boardingClass[i][j] = (boardingClass[i][j] / attr_total);
			}
			
			attr_total = 0;
			for(int j = 0; j < gender[0].length; j++){
				attr_total += gender[i][j];
			}
			//System.out.println(attr_total);
			for(int j = 0; j < gender[0].length; j++){
				gender[i][j] = (gender[i][j] / attr_total);
			}
			
			attr_total = 0;
			for(int j = 0; j < age[0].length; j++){
				attr_total += age[i][j];
			}
			//System.out.println(attr_total);
			for(int j = 0; j < age[0].length; j++){
				age[i][j] = (age[i][j] / attr_total);
			}
			
			attr_total = 0;
			for(int j = 0; j < fare[0].length; j++){
				attr_total += fare[i][j];
			}
			//System.out.println(attr_total);
			for(int j = 0; j < fare[0].length; j++){
				fare[i][j] = (fare[i][j] / attr_total);
			}
			
			attr_total = 0;
			for(int j = 0; j < cabin[0].length; j++){
				attr_total += cabin[i][j];
			}
			//System.out.println(attr_total);
			for(int j = 0; j < cabin[0].length; j++){
				cabin[i][j] = (cabin[i][j] / attr_total);
			}
		}
	}
	
	
	
	/**
	 * 
	 * -----FORMATTING FOR THE DATA-----
	 * 
	 * P(0|A(n))     " " P(1|A(n))
	 * P(0|A(n + 1)) " " P(1|A(n + 1))
	 * and so on for each attribute 
	 * 
	 * these are how many value are in each attribute array
	 * (remember there is a survive and didn't survive probability for each of these values)
	 * 
	 * boarding class:	3  (taken from the value in the data)
	 * gender: 			2  (1 = female, 0 = male)
	 * age:				10 (divided into groups of 10 years)
	 * fare:				21 (divided into groups of 25$)
	 * cabin:			4  (taken from letter of cabin A-D)
	 * 
	 * last is the base probabilities P(0) and P(1)
	 * 
	 */
	/**
	 * @param fName
	 * @throws IOException
	 */
	private static void write(String fName) throws IOException{
		PrintWriter mFile = new PrintWriter(fName);
		
		for(int i = 0; i < boardingClass[0].length; i++){
			mFile.println(boardingClass[0][i] + " " + boardingClass[1][i]);
		}
		
		for(int i = 0; i < gender[0].length; i++){
			mFile.println(gender[0][i] + " " + gender[1][i]);
		}
		
		for(int i = 0; i < age[0].length; i++){
			mFile.println(age[0][i] + " " + age[1][i]);
		}
		/*for(int i = 0; i < fare[0].length; i++){
			mFile.println(fare[0][i] + " " + fare[1][i]);
		}*/
		for(int i = 0; i < cabin[0].length; i++){
			mFile.println(cabin[0][i] + " " + cabin[1][i]);
		}
		
		mFile.println(prob_survive + " " + (1 - prob_survive));
		mFile.close();
		
	}
}
