package linecounter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

/**
 * SourceFileReader
 * A class that reads a source code file, and determines the number of lines it contains. This class can
 * sort out comment lines, and return both the number of comment lines and lines with actual code.
 * @author Jonathan Thomas
 *
 */
public class SourceFileReader {
	
	private File theFile;				// The file to check
	private String[] commentChars;		// Characters that start a comment in this language.
										// The first character in the list is always a line comment. Any subsequent
										// characters will be block comment initiators and terminators.
	
	public long totalLines 	= 0;	// Total number of lines of any kind in the file
	public long commentLines 	= 0;	// Total number of comment lines in the file
	
	public SourceFileReader(File file, int languageIndex, boolean ignoreComments){
		this.theFile = file;
		this.commentChars = LanguagesList.commentCharacters[languageIndex];
		
		numberOfLines(ignoreComments);
	}
	
	public void numberOfLines(boolean ignoreComments)
	{
		
		// Try to read the total number of lines in the file
		try {
			// Create a LineNumberReader object and give it the file
			if(ignoreComments){
				LineNumberReader reader =				// Create a LineNumberReader object and give it the file 
						new LineNumberReader(new FileReader(theFile));
				reader.skip(Long.MAX_VALUE);			// Skip to the end of the file
				totalLines = reader.getLineNumber();	// Read the line number at our position
				reader.close();							// Close the reader!
			}
			else
			{
				// TODO Block comment detector
				Scanner in = new Scanner(theFile);
				while(in.hasNext()){					// While lines exist in the file
					String line = in.nextLine();		// Scan in the next line
					if(detectCommentLine(line)){		// If a comment is detected, increment the comment count
						commentLines++;
					}else{								// If no comment is detected, increment the source count
						totalLines++;
					}
				}
				
				in.close();		// Remember to close the scanner!
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean detectCommentLine(String line){
		// Split the first comment character into an array of chars
		char[] commentCharList = commentChars[0].toCharArray();
		
		// Split the line character into an array of chars, and trim off whitespace
		char[] lineChars = line.trim().toCharArray();
		
		// If the line is empty, return false. Also decrement the total line count, negating the value
		// that will be added should this function return false
		if(lineChars.length==0){
			totalLines--;
			return false;
		}
		
		// Loop through the characters of the comment list. If the comment characters don't match the line characters,
		// return false. This line is not a comment
		for(int i = 0; i<commentCharList.length; i++){
			if(lineChars[i] != commentCharList[i])
				return false;
		}
		
		
		// TODO Add support for languages with more than one line comment character
		/*
		if(commentChars.length > 3)
			detectCommentLine(line);
		*/
		// If we haven't discovered that a line isn't a comment, then it must be a comment
		return true;
	}

	public static void main(String[] args) {		
//		JFileChooser chooser = new JFileChooser();
//		int val = chooser.showOpenDialog(null);
//		
//		if(val==JFileChooser.APPROVE_OPTION){
//			File file =  chooser.getSelectedFile();
//			String[] comm = {"//"};
//			SourceFileReader reader = new SourceFileReader(file, comm);
//			reader.numberOfLines(false);
//			System.out.println("Total lines: "+reader.totalLines+"\nComment lines: "+reader.commentLines);
//		}

	}

}
