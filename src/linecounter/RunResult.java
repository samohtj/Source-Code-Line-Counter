package linecounter;

public class RunResult {
    private long numFiles   = 0;
    private long numFolders = 0;
    private long totalLines = 0;
    
    public RunResult(long numFiles, long numFolders, long totalLines) {
    	this.numFiles = numFiles;
    	this.numFolders = numFolders;
    	this.totalLines = totalLines;
    }
    
    public long numFiles() { return numFiles; }
    public long numFolders() { return numFolders; }
    public long totalLines() { return totalLines; }
}
