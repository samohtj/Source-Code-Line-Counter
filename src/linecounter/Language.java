package linecounter;

public class Language {
    public String name;
    public String[] extensions;
    public String[] lineCommentChars;
    public String[] blockCommentDelimiters;
    public int index;

    public Language(String name, 
    		String[] extensions, 
    		String[] lineCommentChars, 
    		String[] blockCommentDelimiters, 
    		int index){
        this.name = name;
        this.extensions = extensions;
        this.lineCommentChars = lineCommentChars;
        this.blockCommentDelimiters = blockCommentDelimiters;
        this.index = index;
    }
    
    public Language() {
    	this("Java", new String[]{"java"}, new String[]{"//"}, new String[]{"/*", "*/"}, 3);    	
    }
    
    public String toString() {
    	return name + "\nAvailable exts: " + extensions[0] + "\nComment char: " + lineCommentChars[0];
    }
}
