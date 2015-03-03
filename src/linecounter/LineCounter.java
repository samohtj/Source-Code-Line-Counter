package linecounter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LineCounter {

	private long numLines = 0;
	private long numFiles = 0;
	private long numFolders = 0;
	
	private String[] validExts;
	
	/**
	 * Recursively loop through a directory and its subdirectories.
	 * @param dir
	 * @return
	 */
	private void loopDir(File dir){
		// Create a an array of files with all the contents of this directory
		// including files and directories
		File[] files = dir.listFiles();
		
		// If no files are found in this directory, end the function
		if(files==null)
			return;
		
		// Loop through each file and directory in this folder
		for(File file: files){
			// If the current file is a directory, check it.
			if(file.isDirectory()){
				loopDir(file);
				numFolders++;
			}
			// If the current file is a file, increment the number of lines by 1
			// and add the number of lines in that file to the total
			else if(isSourceCodeFile(file)){
				numFiles++;
				numLines += lineCount(file);
			}
		}
	}
	
	/**
	 * Count the number of lines in a file
	 * @param file
	 * @return
	 */
	public long lineCount(File file){
		long totalLines = 0;
		
		// Try to read the total number of lines in the file
		try {
			// Create a LineNumberReader object and give it the file
			LineNumberReader reader = new LineNumberReader(new FileReader(file));
			// Skip to the end of the file
			reader.skip(Long.MAX_VALUE);
			// Read the line number at our position
			totalLines = reader.getLineNumber();
			// Close the reader!
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return totalLines;
	}
	
	/**
	 * Determine if a given file is a programming source code file.
	 * @param file
	 * @return
	 */
	public boolean isSourceCodeFile(File file){
		// Split the filename to get the extension
		String[] bits = file.getName().split("\\.");

		// If the file even has an extension, check to see if it's valid
		if(bits.length>1){
			// Loop through the list of valid extensions given to us by the user, and see if the
			// extension of this file is on the list
			for(String ext: validExts){
				if(ext.equals(bits[bits.length-1]))
					return true;
			}
		}

		// If the file doesn't have an extension, we won't even bother checking it
		return false;
	}
	
	public LineCounter(){
		JFrame frame = new JFrame();
		
		// Show a message saying to choose a root directory
		JOptionPane.showMessageDialog(frame, "Choose a root directory.");
		
		// Create a file chooser to get the selected folder to run on
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int val = chooser.showOpenDialog(null);
		
		// Get the file extensions that we want to check for
		String exts = JOptionPane
				.showInputDialog(frame, "Enter the extensions you would like to check for, separated by commas:")
				.replaceAll("\\s", "");
		validExts = exts.split(",");
		
		// If the chooser returned a file, run the program on it
		if(val==JFileChooser.APPROVE_OPTION){
			// Start by checking the directory given
			loopDir(chooser.getSelectedFile());
		}
		
		// Print out the results
		JOptionPane.showMessageDialog(frame, "Folder contained "+numFiles+" files, "+numFolders+" directories\n"
				+numLines+" total lines of code.");
	}
	
	public static void main(String[] args){
		@SuppressWarnings("unused")
		LineCounter counter = new LineCounter();
	}
}
