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
    public int index;
    public String[] extensions;
    public String[] lineCommentChars;
    public String[] blockCommentDelimiters;
    

    /**
     * 
     * @param name
     * @param index
     * @param extensions
     * @param lineCommentChars
     * @param blockCommentDelimiters
     */
    public Language(String name, 
    		int index,
    		String[] extensions, 
    		String[] lineCommentChars, 
    		String[] blockCommentDelimiters
    		){
        this.name = name;
        this.extensions = extensions;
        this.lineCommentChars = lineCommentChars;
        this.blockCommentDelimiters = blockCommentDelimiters;
        this.index = index;
    }
    
    public Language() {
    	this("Java", 3, new String[]{"java"}, new String[]{"//"}, new String[]{"/*", "*/"});    	
    }
    
    /**
     * @return A formatted String containing this language's relevant information.
     */
    public String toString() {
    	return name 
    			+ "\nAvailable exts: " + Arrays.toString(extensions) 
    			+ "\nComment char: " + Arrays.toString(lineCommentChars)
    			+ "\nBlock: " + blockCommentDelimiters[0] + " " + blockCommentDelimiters[1];
    }
}
