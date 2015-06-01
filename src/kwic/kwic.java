package kwic;
import java.util.List;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** The program searches through the input document given via command line,
 * by default "abc.txt". It goes through each line, circularly shifts the 
 * words, creates a map referencing each word to each line number. It then 
 * arranges all the words alphabetically and deletes the stop words from this list.
 * The program then loops till all the indexed words and their line numbers (page numbers)
 * are displayed alphabetically. The output is displayed on the console.
 * 
 * It can read a maximum of 10000000 lines from a file, but stores a maximum of 10 instances
 * of the same index word in the map. Since it stores a maximum of 10 instances, it displayes the
 * last 10 stores instances of that index word (in case more than 10 existed).
 * 
 * Known issues: (Solved)
 * 1) The program does not distinguish between special characters combined with words.
 * Fore example, it cannot tell if index is the same as "index,". The later will throw an error. 
 * 2) The program does not ignore special characters on their own. They are treated as individual words
 * and hence will throw a "not found" error.
 * @ Author: Avichal Chum
 */


public class kwic {

	// File name of the file to be indexed
	private String mFileName;
	private File mFileObj;
	private String mFileFullPath;
	// Flag to show input argument was given
	private boolean mGivenInput = false;
	
	// Create command line input scanner
	Scanner mCmndIn = new Scanner(System.in);
	
	/*
	 *  Define main search object holder
	 */
	List<KwicSearch> mKwickers = new ArrayList<KwicSearch>();

	// Default Constructor : Empty
	public kwic(){

	}
	
	// Constructor with input file name
	public kwic(String filename){
		this.mFileName = filename;
		this.mGivenInput = true;
	}

//File selection
	private int selectFile(int stateFlag) throws IOException {
		// Define holder for user inputs
		String inputStr;
		// Define holder for file name
		String fileName;
		
		// Try catch for Scanner actions (mCmndIn)
		try {
			
			// If file name was not set as argument, prompt for file name
			if (mGivenInput){
				inputStr = mFileName;
				
				mGivenInput = false;
			}
			else {
				
				System.out.format("Enter file name and press enter%n");
				inputStr = mCmndIn.nextLine();
				
				
				
			}
			
			
		
			if (inputStr.equalsIgnoreCase("exit")){
				// Double Check Before Exiting
				System.out.format("%nYou entered: %s%n", inputStr);
				inputStr="y";
				if (inputStr.equalsIgnoreCase("y")){
					System.out.format("%nProgram Exiting%n");
					// Set return code to 0, which should cause the program to exit
					stateFlag = 0;
				} else {
					
				}
			} else { // Error check the specified file name
				
				// TODO: Research potential TOCTTOU security flaw
				System.out.format("%nYou entered: %s%n", inputStr);
				// Save off file name so we can reuse inputStr 
				fileName = inputStr;
				
				try {
					// Create File object based on fileName
					mFileObj = new File(fileName);
					// Check for read permissions
					if(mFileObj.canRead()){
						// Get full path to file
						//mFileFullPath = mFileObj.getAbsolutePath(); - FinBugs error: URF_Unread_Field
						inputStr="y";
						if (inputStr.equalsIgnoreCase("y")){
							System.out.format("%n Starting indexing of file%n");
							mFileName = fileName;
							// Change UI state flag
							stateFlag = 2;						
						} else {
							//Keep in case any other options may need to be handeled.
						}
					} else {
						System.out.format("%n\"%s\" could not be found.%nCheck that file name and file permissions%n",fileName);
						System.out.println("If you want to exit, type 'exit'");
					}

				} catch (NullPointerException e){
					e.printStackTrace();
				} 			
			}
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} finally {

		} 
		
		return stateFlag;
	}
	
	// Implement the bells and whistles for UI in State 2: Keyword search
	private int displayIndex(int stateFlag){
		/*
		 * Define local KWIC search object
		 * For now we will always assume we want the first
		 * kwicker, even if several are available 
		 */
		KwicSearch localKwicker;
		
		// Define holder for user inputs
		String inputStr;
			
		// Try catch for Scanner actions (mCmndIn)
		try {
			
			System.out.println("Indexing complete. Press enter to display generated KWIC index.");
			
			inputStr = mCmndIn.nextLine();
			
			// Check for exit
			if (inputStr.equalsIgnoreCase("exit")){
				// Double Check Before Exiting
				System.out.format("%nYou entered: %s%n", inputStr);
					inputStr = "y";
				if (inputStr.equalsIgnoreCase("y")){
					System.out.format("%nProgram exiting%n");
					// Set return code to 0, which should cause the program to exit
					stateFlag = 0;
				} else {
					System.out.format("%nGreat!  Glad you are sticking around%n");
				}
			} else {
				/*
				 * Grab the desired KWIC search object
				 * For now we will always assume we want the first
				 * kwicker, even if several are available 
				 */
				localKwicker = mKwickers.get(0);
				
				if (localKwicker != null){
					// Check if index is complete yet
					if (!localKwicker.isIndexDone()){ // Index not yet complete
						System.out.format("%nOh, the file is still being indexed.  Please try again in a bit%n");
					} else { // Index is complete, check for results
						// TODO: Consider doing cleanup on input string
						String wordlist[]=processSearch(mFileName);
						for(int i=0;i<wordlist.length-1;i++){
							if(wordlist[i].equals(wordlist[i+1])) //array out of bounds potential
								{
								
								continue;
								}
							
							inputStr=wordlist[i];
							localKwicker.processQueryKeyword(inputStr);
						}
						
					}
				} else {
					System.out.format("%nHmm, that's weird. Couldn't find that search object%n");
				}
				
			}
			stateFlag = 0;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} finally {
			
		}
		// End Try catch for Scanner actions (mCmndIn)
		
		// Send back the state code
		return stateFlag;
	}
	
	
	// Method to Execute Main User Interface State Machine
	private void launchMainControl(){
		// Define state flag (Default to file input state)
		int stateFlag = 1;
				
		// Implement UI State for Selecting the File to Index
		do {
			
			try {
				// Execute main UI code for File selection state
				stateFlag = selectFile(stateFlag);
				
				// Check for immediate exit
				if (stateFlag == 0){
					return;
				}
			} catch (IOException e){
				e.printStackTrace();
			}
			
		} while(stateFlag == 1);
		
		
		// ----- Create new KWIC search object and start indexing a file ----
		mKwickers.add(new KwicSearch(mFileName));
		// Start Indexing process on newest search object
		mKwickers.get(mKwickers.size()-1).startIndexWorker();
		
		
	
		do {
			// Execute main UI code displaying the KWIC index to the console
			
			
			stateFlag = displayIndex(stateFlag);
			
			// Check for immediate exit
			if (stateFlag == 0){
				return;
			}
			
		} while (stateFlag == 2);		
			// Close Scanner
		mCmndIn.close();
	}
	
	
	//Makes a list of all the index words, deletes the stop words and 
	//arranges the list alphabetically.
	public String[] processSearch(String filename){
		String[] wordlist = readText(filename).split(" ");
		
		for(int i=0;i<wordlist.length;i++) // Loop iteratively to remove any special characters form word list
		 wordlist[i] = wordlist[i].replaceAll("[^a-zA-Z]+",""); // Solves known issue 1 and 2

		 String[] ignorelist={"of","The","the","for","and","a","As","A"}; // Add more stop words here
		
		 int wordsRemoved=0;

		 for(int i=0;i<wordlist.length;i++){
			 for(int j=0;j<ignorelist.length;j++){
					 if(wordlist[i].equals(ignorelist[j])){
					 wordsRemoved++;
					 for(int k=i;k<wordlist.length-1;k++){
						 wordlist[k]=wordlist[k+1];				 // Shift array and over-write stop words
					 }
					 
				 }
			 }
		 }
		 for(int i=0;i<wordsRemoved;i++)
		 wordlist[wordlist.length-1-i]=null; //Set trailing elements of array to null
		 
		 List<String> list = new ArrayList<String>(); //Convert array to list

		 for(String s : wordlist) { 
		    if(s != null && s.length() > 0) {  //Delete null elements from list
		       list.add(s);
		    }
		 }

		 wordlist = list.toArray(new String[list.size()]); //Convert list back to array with reduced size
		 String wordlistnew[]=wordlist;
		 for(int i=0;i<wordlist.length;i++)
			 wordlistnew[i]=wordlist[i].toLowerCase(); 
		 //Convert everything to lower case to avoid processing same words twice
		 Arrays.sort(wordlistnew);
//Sort array alphabetically and return to retrieve instances and indexes. 
		 		 
		 return wordlistnew;
	}
	
	 private static String readText(String InputFile) {
		 String text = "";
		 try{
		         FileInputStream fstream = new FileInputStream(InputFile);
		         DataInputStream in = new DataInputStream(fstream);
		         BufferedReader br = new BufferedReader(new InputStreamReader(in));
		         String strLine;
		         while ((strLine = br.readLine()) != null)
		          text+=" "+strLine;
		         in.close();
		         br.close();
		     } catch (FileNotFoundException e) {
		 e.printStackTrace();
		 } catch (IOException e) {
		 e.printStackTrace();
		 } 
		 return text;
		 
		        }
		
	
	public static void main(String[] args) {
		kwic kwicker;
		

		if (args.length > 0){
			kwicker = new kwic(args[0]);
		} else {
			kwicker = new kwic();
		}
					
		// Launch User Interface
		kwicker.launchMainControl();
	}

}
