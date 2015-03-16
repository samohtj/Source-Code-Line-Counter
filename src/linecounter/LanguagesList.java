package linecounter;

/**
 * This class simply stores a cataogue of available languages, their file extensions, and their comment characters
 * @author Jonathan Thomas
 *
 */
public class LanguagesList {
	
	public static final int C 			= 0;
	public static final int C_PLUS_PLUS 	= 1;
	public static final int C_SHARP 		= 2;
	public static final int JAVA 			= 3;
	public static final int PHP			= 4;
	public static final int PYTHON		= 5;
	public static final int ROBOT_C		= 6;
	
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
	/* C */		{"c", "h"},
	/* C++ */	{"cc", "cpp", "cxx", "C", "c++", "h", "hh", "hpp", "hxx", "h++"},
	/* C# */	{"cs"},
	/* Java */	{"java"},
	/* PHP */	{"php", "phtml", "php4", "php3", "php5", "phps"},
	/* Python */{"py"},
	/* RobotC */{"c", "h"}
			
	};
	
	public static String[][] commentCharacters = {
	/* C */		{"//", "/*", "*/"},
	/* C++ */	{"//", "/*", "*/"},
	/* C# */	{"//", "/*", "*/"},
	/* Java */	{"//", "/*", "*/"},
	/* PHP */	{"//", "/*", "*/", "#"},	// Rare secondary line comment
	/* Python */{"#", "\"\"\"", "\"\"\""},
	/* RobotC */{"//", "/*", "*/"}
			
	};
}
