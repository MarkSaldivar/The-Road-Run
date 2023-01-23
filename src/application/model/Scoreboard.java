package application.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * The Scoreboard class is responsible for the scoreboard object and it's functions
 * 
 * @author Tyler Unruh (hsg211)
 * @author John Moran (qvv333)
 * @author Mark Saldivar (bpr926)
 *
 */
public class Scoreboard {


	private static String username;
	private static String fileName;
	private static int score;
	
	
	/**
	 * This is a constructor method that initializes the Scoreboard object
	 * 
	 * @author Tyler Unruh (hsg211)
	 * 
	 * @param fileName
	 * @param username
	 * @param score
	 */
	public Scoreboard(String fileName, String username, int score) {
		Scoreboard.fileName = fileName;
		Scoreboard.username = username;
		Scoreboard.score = score;
	}

	/**
	 * the loadQuote() method will load a random motivational quote into the scoreboard
	 * 
	 * @author Tyler Unruh (hsg211)
	 * 
	 * @param fileName
	 * @param randomNumber
	 * @return A randomly selected string from a file
	 */
	public static String loadQuote(String fileName, int randomNumber) {

		BufferedReader reader = null;
		String line = " ";
		ArrayList<String> tokens = new ArrayList<>();

		try {
			reader = new BufferedReader(new FileReader(fileName));
			while ((line = reader.readLine()) != null) {
				tokens.addAll(Arrays.asList(line.split(",")));
				
                
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return tokens.get(randomNumber);

	}

	/**
	 * the getRandNumber() method will get a random number based on the number of lines in an inspirational quote file
	 * 
	 * @author Tyler Unruh (hsg211)
	 * 
	 * @return an int
	 */
	public static int getRandNumber() {
		Scanner scan = null;
		int ret = 0;
		int count = 0;
		Random rn = new Random();
		
		try {
			scan = new Scanner(new File("./data/Scoreboard/keepGoing.txt"));
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				if(line != null) {
					++count;
				}
			}
			ret = rn.nextInt((count));
			if(ret == 0) {
				ret = 1;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * The getScores() method will load the scores form a csv file and parse the data to be used in the Scoreboard Controller 
	 * 
	 *  @author Tyler Unruh (hsg211)
	 *  @author John Moran (qvv333)
	 *  @author Mark Saldivar (bpr926)
	 * 
	 * @param fileName
	 * @return an arraylist of strings that represents the scoreboard data
	 */
	public static ArrayList<String> getScores(String fileName) {
		Scanner scan = null;
		ArrayList<String> scores = new ArrayList<String>();
		try {
			scan = new Scanner(new File(fileName) );
			

			
			while( scan.hasNextLine() ) {
				String line = scan.nextLine();
				String[] tokens = line.split( "," );
				if( line!=null ) {
					if(scores.size() > 0) {
						int counter = scores.size();
						while(Integer.parseInt(tokens[1]) > Integer.parseInt(scores.get(counter - 1).split(": ")[1])) {
							//System.out.println(tokens[1]);
							if(counter != 0) {
								counter--;
							} else  {
								break;
							}
							if(counter == 0) 
								break;
							
						}
						scores.add(counter, tokens[0] + ": " + tokens[1]);
					} else {
						scores.add(tokens[0] + ": " + tokens[1]);
					}
					
					//scores.add(tokens[0] + ": " + tokens[1]);
				} 

			
				
			}
			
		}catch( IOException e ) {
			//e.printStackTrace();
		}
		
		if( scan!=null )
			scan.close();
		return scores;
	}
	
	/*
	 * the loadNewScore() method takes in data from the EndScreen controller and writes it to the scoreboard database
	 * 
	 *  @author Tyler Unruh (hsg211)
	 *  @author John Moran (qvv333)
	 *  @author Mark Saldivar (bpr926)
	 * 
	 */
	public static void loadNewScore(String fileName, String userName, int score) throws IOException {
		
		FileWriter fw = new FileWriter(fileName, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(userName + "," + score);
		bw.newLine();
		bw.close();
				
	}	

	
	
	
	
	/**
	 * @return the username
	 */
	public static String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public static void setUsername(String username) {
		Scoreboard.username = username;
	}

	/**
	 * @return the fileName
	 */
	public static String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public static void setFileName(String fileName) {
		Scoreboard.fileName = fileName;
	}

	/**
	 * @return the score
	 */
	public static int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public static void setScore(int score) {
		Scoreboard.score = score;
	}
	
	
	
	/*
	public static void loadData( String fileName, String input ) {
		Scanner scan = null;
		try {
			scan = new Scanner(new File(fileName) );
			

				
			if(scan.hasNextLine() == false) {
				FileWriter fw = new FileWriter(fileName, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(input + ",0");
				bw.close();
				username = input;
			}
			while( scan.hasNextLine() ) {
				String line = scan.nextLine();
				String[] tokens = line.split( "," );
				if( line!=null ) {
					if(tokens[0].equals(input)) {
						username = input;
						break;
					}
				} 

					

				
				if(scan.hasNextLine() == false) {
					FileWriter fw = new FileWriter(fileName, true);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(input + ",0");
					bw.close();
					username = input;
				}
				
			}
			
			Scoreboard.newScore("data/data.txt", score);
			
		}catch( IOException e ) {
			//e.printStackTrace();
		}
		
		if( scan!=null )
			scan.close();
	}
	*/
	
	/*
	public static void newScore(String fileName, int score) {
		
		
		
		try {
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			StringBuffer inputBuffer = new StringBuffer();
			String line;
			
			while((line = file.readLine()) != null) {
				inputBuffer.append(line);
				inputBuffer.append('\n');
			}
			
			file.close();
			String inputString = inputBuffer.toString();
			String[] lines = inputString.split("\n");
			String newStr = "";
			for (String text: lines) {
				if(text.split(",")[0].equals(username)) {
					String[] tokens = text.split(",");
					if(username.equals(tokens[0])) {
						if(score > Integer.parseInt(tokens[1])) {
							text = username + "," + score;
						}
					}
					
				
			
				}
				newStr += text + "\n";
				
			}
			FileOutputStream fileOut = new FileOutputStream(fileName);
			fileOut.write(newStr.getBytes());
			fileOut.close();
			
			
			
		}catch( IOException e ) {
			//System.out.println(e);
			e.printStackTrace();
		}
	}
	*/
	
	
	/*
	public String getUsername() {
		return username;
	}


	public static void setUsername(String username) {
		Scoreboard.username = username;
	}


	public static int getScore() {
		return score;
	}


	public static void setScore(int score) {
		Scoreboard.score = score;
	}
	*/
	
	
	

}


