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
    
    /**
     * Construct a new Language with <i>no</i> block comments.
     * @param   name Name of the language.
     * @param   extensions File extensions.
     * @param   lineCommentChars Line comment characters.
     * @return  New Language object.
     */
    public Language(String name,
    		String[] extensions,
    		String[] lineCommentChars) {
    	this.name = name;
    	this.extensions = extensions;
    	this.lineCommentChars = lineCommentChars;
    }

    /**
     * Create a Langauge object with the defualt values. The default is Java.
     * @return Default Language.
     */
    public Language() {
    	this("Java", new String[]{"java"}, new String[]{"//"});
    }

    /**
     * Return an array containing all of both types of comment characters.
     * @return
     */
    public String[] allCommentChars() {
    	String[] list = new String[lineCommentChars.length];
    	
    	if (list.length == 0) {
    		return list;
    	}

    	for (int i = 0; i < lineCommentChars.length; i++) {
    		list[i] = lineCommentChars[i];
    	}
    	return list;
    }

    /**
     * @return A formatted String containing this language's relevant information.
     */
    public String toString() {
    	return name
    			+ "\nAvailable exts: " + Arrays.toString(extensions)
    			+ "\nComment char: " + Arrays.toString(lineCommentChars);
    }
}
