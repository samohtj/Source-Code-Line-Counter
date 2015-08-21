package linecounter;

import java.io.File;
import java.util.ArrayList;

/**
 * This class simply stores a cataogue of available languages, their file extensions, and their comment characters
 * @author Jonathan Thomas
 *
 */
public class LanguagesList {

    private ArrayList<Language> list = new ArrayList<Language>();
    
    public Language get(int index) {
    	// If the index asked for is out of bounds, default to Java
    	if(index > list.size() - 1)
    		return new Language();
    	
        return list.get(index);
    }
    
    public void load(File file) {
    	
    }
    
    public LanguagesList(File file) {
    	load(file);
    }
    
    public void add(Language lang) {
    	list.add(lang);
    }

    public LanguagesList() {
    	//list.add(new Language("Java", new String[]{"java"}, new String[]{"//"}, new String[]{"/*", "*/"}, 3));
    }
    
    /**
     * Return a new ArrayList containing all the Language objects stored in this list.
     * @return
     */
    public ArrayList<Language> allLangs() {
    	for(Language lang: list)
    		System.out.println(lang.index+ ". " + lang.toString());
    	return new ArrayList<Language>(list);
    }










    public static final int C           = 0;
    public static final int C_PLUS_PLUS	= 1;
    public static final int C_SHARP     = 2;
    public static final int JAVA        = 3;
    public static final int PHP         = 4;
    public static final int PYTHON      = 5;
    public static final int ROBOT_C     = 6;

    public static String[] availableLangs = {
            "C", 
            "C++",
            "C#",
            "Java",
            "PHP",
            "Python",
            "RobotC"
    };

    public static String[][] availableExtensions = {
    /* C */     {"c", "h"},
    /* C++ */   {"cc", "cpp", "cxx", "C", "c++", "h", "hh", "hpp", "hxx", "h++"},
    /* C# */    {"cs"},
    /* Java */  {"java"},
    /* PHP */   {"php", "phtml", "php4", "php3", "php5", "phps"},
    /* Python */{"py"},
    /* RobotC */{"c", "h"}

    };

    public static String[][] commentCharacters = {
    /* C */     {"//", "/*", "*/"},
    /* C++ */   {"//", "/*", "*/"},
    /* C# */    {"//", "/*", "*/"},
    /* Java */  {"//", "/*", "*/"},
    /* PHP */   {"//", "/*", "*/", "#"},	// Rare secondary line comment
    /* Python */{"#", "\"\"\"", "\"\"\""},
    /* RobotC */{"//", "/*", "*/"}

    };
}
