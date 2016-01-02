package linecounter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

/**
 * SourceFileReader reads a source code file, and determines the number of lines it contains. This
 * class can sort out comment lines, and return both the number of comment lines and lines with
 * actual code.
 * @author Jonathan Thomas
 *
 */
public class SourceFileReader {

    private File theFile;           // The file to check
    private String[] commentChars;  // Characters that start a comment in this language.

    private long totalLines = 0;    // Total number of lines of any kind in the file

    /**
     * Construct a new SourceFileReader from the given parameters.
     * @param File The file to read.
     * @param Language The language the file is written in.
     * @param ignoreComments Whether to ignore lines that are commented.
     */
    public SourceFileReader(File file, Language language, boolean ignoreComments) {
        this.theFile = file;
        this.commentChars = language.lineCommentChars;

        numberOfLines(ignoreComments);
    }

    /**
     * Total lines in the project.
     * @return The total number of lines in this source file.
     */
    public long totalLines() {
    	return totalLines;
    }

    /**
     * Count the number of lines in a source file. If {@code ignoreComments} is true, the method will
     * not add commented lines to the count. The resultant count will be placed into
     * @param ignoreComments Whether to ignore commented lines.
     * TODO Make ignore comments always true.
     */
    private void numberOfLines(boolean ignoreComments) {

        // Try to read the total number of lines in the file
        try {
            // Create a LineNumberReader object and give it the file
            if (ignoreComments) {
                LineNumberReader reader = new LineNumberReader(new FileReader(theFile));
                reader.skip(Long.MAX_VALUE);
                totalLines = reader.getLineNumber();
                reader.close(); // Close the reader!
            } else {
                Scanner in = new Scanner(theFile);
                while (in.hasNext()) {
                    String line = in.nextLine();
                    if (!detectCommentLine(line)) {
                        totalLines++;
                    }
                }

                in.close();        // Remember to close the scanner!
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Detect whether a line is commented out.
     * @param  line The line to check.
     * @return      Whether the line is a comment or not.
     */
    public boolean detectCommentLine(String line) {

        char[] commentCharList = commentChars[0].toCharArray();
        char[] lineChars = line.trim().toCharArray();

        // If the line is empty, return false. Also decrement the total line count, negating the
        // value that will be added should this function return false
        if (lineChars.length==0) {
            totalLines--;
            return false;
        }

        // Loop through the characters of the comment list. If the comment characters don't match
        // the line characters, return false. This line is not a comment
        for (int i = 0; i<commentCharList.length; i++) {
            if (lineChars[i] != commentCharList[i]) {
                return false;
            }
        }


        // TODO Add support for languages with more than one line comment character
        /*
        if(commentChars.length > 3)
            detectCommentLine(line);
        */
        // If we haven't discovered that a line isn't a comment, then it must be a comment
        return true;
    }
}
