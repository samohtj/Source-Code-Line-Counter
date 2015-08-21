package linecounter;

import java.io.File;
import javax.swing.JFileChooser;

/**
 * ProjectReader will take a given folder, search through it, and use a SourceFileReader to process
 * each file found in the project.
 * @author Jonathan Thomas
 *
 */
public class ProjectReader {

    /*
     * Store the overall number of lines, files, and folders for the whole project
     */
    private long numFiles   = 0;
    private long numFolders = 0;

    private long totalLines     = 0;
    private long commentLines   = 0;

    public File rootFolder = null;
    private Language language;
    private boolean ignoreComments;
    
    public Settings settings;

    /*
     * Getters for the various line counts
     */
    public long totalLines() {return totalLines;}
    public long commentLines() {return commentLines;}
    public long numFiles() {return numFiles;}
    public long numFolders() {return numFolders;}

    /**
     * Run the program on the set root folder.
     * @param ignoreComments
     */
    public void run() {
        // Reset counters before running the program!
        totalLines      = 0;
        commentLines    = 0;
        numFiles        = 0;
        numFolders      = 0;

        recursiveDirectoryCheck(settings.rootFolder);
    }

    /**
     * Recursively loop through a directory and its subdirectories, and run a line counter on each file.
     * @param dir The directory to run on.
     */
    private void recursiveDirectoryCheck(File dir) {
        // Create a an array of files with all the contents of this directory
        File[] files = dir.listFiles();

        // If no files are found in this directory, end the function
        if(files==null)
            return;

        for(File file: files) {
            // If the current file is a directory, perform this same process again
            if(file.isDirectory()) {
                recursiveDirectoryCheck(file);
                numFolders++;
            }
            // Ignore files that do not contain source code
            else if(isSourceCodeFile(file)) {
                numFiles++;

                // Create a source file reader for the file, and add its line count to the total count
                SourceFileReader reader =  new SourceFileReader(file, language, ignoreComments);
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
            for(String ext: settings.selectedLang().extensions) {
                if(ext.equals(bits[bits.length-1]))
                    return true;
            }
        }
        
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
        settings.selectLanguage(languageIndex);
        this.language = settings.selectedLang();
    }
    
    /**
     * Set whether to ignore comments or not.
     * @param ignoreComments 
     */
    public void setIgnoreComments(boolean ignoreComments) {
    	this.ignoreComments = ignoreComments;
    }

    /**
     * Create a new ProjectReader object with the given settings.
     * @param settings
     */
    public ProjectReader(Settings settings) {
    	this.settings = settings;
        this.ignoreComments = settings.ignoreComments;
        this.language = settings.selectedLang();
    }
}
