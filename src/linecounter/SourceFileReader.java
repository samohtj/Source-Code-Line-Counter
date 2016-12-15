/*
 * Copyright (C) 2016 Jonathan Thomas
 *
 * Source-Code-Line-Counter: An application for counting the number of lines in your source code.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package linecounter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

/**
 * SourceFileReader reads a source code file, and determines the number of lines it contains. This
 * class can filter out comments and whitespace if desired.
 * @author Jonathan Thomas
 *
 */
public class SourceFileReader {

    /**
     * Count the number of lines in a source file. If {@code shouldCountComments} is true, the method will
     * not add commented lines to the count. The resultant count will be placed into
     * @param filter If true, filter out comments and whitespace.
     * TODO Make ignore comments always true.
     */
    public static long numberOfLines(File file, Language language, boolean filter) {

    	long totalLines = 0;
    	
        try {
            if (!filter) {
            	// If we don't care about whitespace and comments, just read the number of newlines
                LineNumberReader reader = new LineNumberReader(new FileReader(file));
                reader.skip(Long.MAX_VALUE);
                totalLines = reader.getLineNumber();
                reader.close(); // Close the reader!
            } else {
                Scanner in = new Scanner(file);
                // Make this a single element array so we can cheat passing by reference
                boolean isInsideBlockComm[] = {false};
                while (in.hasNext()) {
                    String line = in.nextLine();
                    if (lineShouldBeCounted(line, isInsideBlockComm)) {
                        totalLines++;
                    }
                }

                in.close();        // Remember to close the scanner!
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return totalLines;
    }

    /**
     * Decide whether a line should be counted, based on our filter criterion. Those being:
     * <ul><li>Contains more than just whitespace</li>
     * <li>Doesn't start with the line comment characters</li>
     * <li>Isn't enclosed completely inside a block comment</li></ul>
     * @param line
     * @return
     */
    private static boolean lineShouldBeCounted(String line, boolean isInsideBlockComm[]) {
    	String lineChars = line.trim();
    	// TODO Read these in dynamically, support more than just one language.
    	String blockStart = "/*";
    	String blockEnd = "*/";
    	String lineStart = "//";
    	
    	// Don't count whitespace lines
    	if (lineChars.isEmpty())
    		return false;
    	
    	if (isInsideBlockComm[0]) {
    		if (lineChars.contains(blockEnd)) {
    			isInsideBlockComm[0] = false;
    			
    			// Return true if there are any more characters after the block comment end
    			return !lineChars.endsWith(blockEnd);
    		} else {
    			return false;
    		}
    		
    	} else {
    		// Don't count line comments
    		if (lineChars.startsWith(lineStart))
    			return false;
    		
    		if (lineChars.contains(blockStart)) {	
    			isInsideBlockComm[0] = true;
    			
    			// Return true if there were some characters before the block comment started
    			return !lineChars.startsWith(blockStart); 
    		} else {
    			return true;
    		}
    	}
    }
    
    /* Use this to test
    public static void main(String args[]) {
    	long lines = SourceFileReader.numberOfLines(new File("test.java"), new Language(), true);
    	System.out.println("The reader found " + lines + " lines of code.");
    }
    */
}
