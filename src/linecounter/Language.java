package linecounter;

import java.util.Arrays;

/**
 * Language represents a computer programming language, and all its file extensions and comment 
 * characters. It is stored in a {@code LanguagesList}.
 * @author Jonathan Thomas
 *
 */
public class Language {
    public String name;
    public String[] extensions;
    public String[] lineCommentChars;
    public String[][] blockCommentDelimiters;
    

    /**
     * 
     * @param name
     * @param index
     * @param extensions
     * @param lineCommentChars
     * @param blockCommentDelimiters
     */
    public Language(String name, 
    		String[] extensions, 
    		String[] lineCommentChars, 
    		String[][] blockCommentDelimiters
    		){
        this.name = name;
        this.extensions = extensions;
        this.lineCommentChars = lineCommentChars;
        this.blockCommentDelimiters = blockCommentDelimiters;
        //this.index = index;
    }
    
    public Language() {
    	this("Java", new String[]{"java"}, new String[]{"//"}, new String[][]{{"/*", "*/"}});
    }
    
    /**
     * Return an array containing all of both types of comment characters.
     * @return
     */
    public String[] allCommentChars() {
    	String[] list = new String[lineCommentChars.length+blockCommentDelimiters.length];
    	
    	for(int i = 0; i < lineCommentChars.length; i++) {
    		list[i] = lineCommentChars[i];
    	}
    	
    	for(int i = 0; i < blockCommentDelimiters.length; i++) {
    		list[i + lineCommentChars.length] = blockCommentDelimiters[i][0] + " ... " 
    				+ blockCommentDelimiters[i][1];
    	}

    	return list;
    }
    
    /**
     * @return A formatted String containing this language's relevant information.
     */
    public String toString() {
    	return name 
    			+ "\nAvailable exts: " + Arrays.toString(extensions) 
    			+ "\nComment char: " + Arrays.toString(lineCommentChars)
    			+ "\nBlock: " + Arrays.toString(blockCommentDelimiters);
    }
}
