package linecounter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class ProjectReader {

	@SuppressWarnings ("unused")
	private long numLines = 0;
	@SuppressWarnings("unused")
	private long numFiles = 0;
	@SuppressWarnings("unused")
	private long numFolders = 0;
	
	// Major settings; root folder, selected language, ignore comments y/n
	public File rootFolder = null;
	private int languageIndex;
	private boolean ignoreComments;
	
	/**
	 * Recursively loop through a directory and its subdirectories, and run a line counter on each file.
	 * @param dir
	 * @return
	 */
	private void recursiveDirectoryCheck(File dir){
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
				recursiveDirectoryCheck(file);
				numFolders++;		// Increment the number of folders read
			}
			// If the current file is a file, increment the number of lines by 1
			// and add the number of lines in that file to the total
			else if(isSourceCodeFile(file)){
				// Increment the number of files read
				numFiles++;
								
				// Create a source file reader for the file, and add its line count to the total count
				SourceFileReader reader =  new SourceFileReader(file, languageIndex);
				numLines += reader.numberOfLines(ignoreComments);
			}
		}
	}
	
	/**
	 * Count the number of lines in a file
	 * @param file
	 * @return
	 */
	public long linesInFile(File file){
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
			for(String ext: LanguagesList.availableExtensions[languageIndex]){
				if(ext.equals(bits[bits.length-1]))
					return true;
			}
		}

		// If the file doesn't have an extension, we won't even bother checking it
		return false;
	}
	
	/**
	 * Choose the root folder of a project
	 * @return A file on the system somewhere, to be treated as the root folder of a project.
	 * @throws NoFileChosenException If no file is chosen (i.e. the user hits the "cancel" button)
	 */
	public void chooseRootFolder() throws NoFileChosenException{
		// Create a new file chooser dialog
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		// If the user hits the cancel button, throw an exception 
		if(chooser.showOpenDialog(null)!=JFileChooser.APPROVE_OPTION)
			throw new NoFileChosenException();
		
		this.rootFolder = chooser.getSelectedFile();
	}
	
	public ProjectReader(File rootFolder, int languageIndex, boolean ignoreComments) throws NoFileChosenException{
		this.languageIndex = languageIndex;
		this.ignoreComments = ignoreComments;
		
		if(rootFolder==null){
			Scanner in = new Scanner("settings.dat");
			
			if(in.hasNext()){
				this.rootFolder = new File(in.nextLine());
			}else{
				in.close();
				throw new NoFileChosenException();
			}
			
			in.close();
		}
		
	}
	
	public ProjectReader(Settings settings){
		this.ignoreComments = settings.ignoreComments;
		this.languageIndex = settings.selectedLanguageIndex;
		this.rootFolder = settings.rootFolder;
	}
	
	public static void main(String[] args){
		
	}
}
