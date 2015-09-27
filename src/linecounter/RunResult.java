package linecounter;

/**
 * Result of a run of the program.
 * @author Jonathan Thomas
 *
 */
public class RunResult {
    private long numFiles   = 0;
    private long numFolders = 0;
    private long totalLines = 0;

    /**
     * Construct a new result.
     * @param   numFiles Number of files detected.
     * @param   numFolders Number of folders detected.
     * @param   totalLines Number of lines detected.
     * @return  New RunResult object.
     */
    public RunResult(long numFiles, long numFolders, long totalLines) {
    	this.numFiles = numFiles;
    	this.numFolders = numFolders;
    	this.totalLines = totalLines;
    }

    public long numFiles() { return numFiles; }
    public long numFolders() { return numFolders; }
    public long totalLines() { return totalLines; }
}
