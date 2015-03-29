package linecounter;

import java.io.File;
import javax.swing.JFileChooser;

/**
 * ProjectReader will process any given folder, and measure the total number of lines, files, folders, and comment lines
 * in all the contained source code.
 * @author Jonathan Thomas
 *
 */
public class ProjectReader {

	/*
	 * Store the overall number of lines, files, and folders for the whole project
	 */
	private long numFiles = 0;
	private long numFolders = 0;
	
	private long totalLines 	= 0;
	private long commentLines 	= 0;
	
	// Major settings; root folder, selected language, ignore comments y/n
	public File rootFolder = null;
	private int languageIndex;
	private boolean ignoreComments;
	
	/*
	 * Getters for the various line counts
	 */
	public long totalLines() {return totalLines;} 
	public long commentLines() {return commentLines;} 
	public long numFiles() {return numFiles;} 
	public long numFolders() {return numFolders;} 
	
	/**
	 * Run the file checker on the set root folder.
	 * @param ignoreComments
	 */
	public void run(boolean ignoreComments) {
		// Make sure everything goes back to 0!
		totalLines 		= 0;
		commentLines 	= 0;
		numFiles 		= 0;
		numFolders 		= 0;
		
		this.ignoreComments = ignoreComments;
		recursiveDirectoryCheck(rootFolder);
	} 
	
	/**
	 * Recursively loop through a directory and its subdirectories, and run a line counter on each file.
	 * @param dir
	 * @return
	 */
	private void recursiveDirectoryCheck(File dir) {
		// Create a an array of files with all the contents of this directory
		// including files and directories
		File[] files = dir.listFiles();
		
		// If no files are found in this directory, end the function
		if(files==null)
			return;
		
		// Loop through each file and directory in this folder
		for(File file: files) {			
			// If the current file is a directory, check it.
			if(file.isDirectory()) {
				recursiveDirectoryCheck(file);
				numFolders++;		// Increment the number of folders read
			} 
			// If the current file is a file, increment the number of lines by 1
			// and add the number of lines in that file to the total
			else if(isSourceCodeFile(file)) {
				// Increment the number of files read
				numFiles++;
								
				// Create a source file reader for the file, and add its line count to the total count
				SourceFileReader reader =  new SourceFileReader(file, languageIndex, ignoreComments);
				totalLines += reader.totalLines;
				commentLines += reader.commentLines;
			} 
		} 
	} 

	/**
	 * Determine if a given file is a programming source code file.
	 * @param file
	 * @return
	 */
	public boolean isSourceCodeFile(File file) {
		// Split the filename to get the extension
		String[] bits = file.getName().split("\\.");

		// If the file even has an extension, check to see if it's valid
		if(bits.length>1) {
			// Loop through the list of valid extensions given to us by the user, and see if the
			// extension of this file is on the list
			for(String ext: LanguagesList.availableExtensions[languageIndex]) {
				if(ext.equals(bits[bits.length-1]))
					return true;
			} 
		} 

		// If the file doesn't have an extension, we won't even bother checking it
		return false;
	} 
	
	/**
	 * Choose the root folder of a project using a JFileChooser dialog.
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
	
	/**
	 * Set the language index for this project.
	 * @param languageIndex
	 */
	public void setLanguage(int languageIndex) {
		this.languageIndex = languageIndex;
	}
	
	/**
	 * Create a new ProjectReader object with the given settings.
	 * @param settings
	 */
	public ProjectReader(Settings settings) {
		this.ignoreComments = settings.ignoreComments;
		this.languageIndex = settings.selectedLanguageIndex;
		this.rootFolder = settings.rootFolder;
	}
	
	public static void main(String[] args) {
		
	} 
} 
