package linecounter;

import java.util.Arrays;

public class Language {
    public String name;
    public int index;
    public String[] extensions;
    public String[] lineCommentChars;
    public String[] blockCommentDelimiters;
    

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
    
    public String toString() {
    	return name 
    			+ "\nAvailable exts: " + Arrays.toString(extensions) 
    			+ "\nComment char: " + Arrays.toString(lineCommentChars)
    			+ "\nBlock: " + blockCommentDelimiters[0] + " " + blockCommentDelimiters[1];
    }
}
