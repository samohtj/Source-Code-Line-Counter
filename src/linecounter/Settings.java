package linecounter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.Serializable;

public class Settings implements Serializable{

    private static final long serialVersionUID = -6952939008177887335L;

    private static final String SETTINGS_FILE_NAME 	= "settings.dat";
    private static final String LANGUAGES_FILE_NAME = "languages.xml";
    
    private transient LanguagesList availableLangs = new LanguagesList();
    
    // Make sure that these all have default values, or you might have trouble later.
    public boolean ignoreComments = true;
    public int selectedLangIndex = 0;
    public File rootFolder = null;

    /**
     * Create a new Settings object with the default settings.
     */
    public Settings(){
        this(true, 0, new File(""));
    }

    /**
     * Create a new Settings object with the given settings.
     * @param ignoreComments
     * @param selectedLanguageIndex
     * @param rootFolder
     */
    public Settings(boolean ignoreComments, int selectedLanguageIndex, File rootFolder){
        this.ignoreComments = ignoreComments;
        this.selectedLangIndex = selectedLanguageIndex;
        this.rootFolder = rootFolder;
    }
    
    /**
     * Set the selected language.
     * @param index
     */
    public void selectLanguage(int index) {
    	if(index > availableLangs.size() - 1)
    		selectedLangIndex = availableLangs.size() - 1;
    	else if(index < 0)
    		selectedLangIndex = 0;
    	else
    		selectedLangIndex = index;
    }
    
    /**
     * Return the currently selected language. If there are no languages available, generate a new one.
     * @return The currently selected language.
     */
	public Language selectedLang() {
		if(availableLangs.size() > 0)
			return availableLangs.get(selectedLangIndex);
		else
			return new Language();
	}


    /**
     * Save a Settings object to a file called settings.dat
     * @param settings
     */
    public static void save(Settings settings){
        // Try to serialize the object
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SETTINGS_FILE_NAME));
            out.writeObject(settings);
            out.close();
            
            // Write all the available languages to an XML file
            LanguagesFileWriter writer = new LanguagesFileWriter();
            writer.writeList(settings.availableLangs, new File(LANGUAGES_FILE_NAME));
            
        }catch(FileNotFoundException ex){
            System.out.println("Could not find file "+SETTINGS_FILE_NAME);
        }catch(IOException ex){
            System.out.println("There was a problem writing to the file.");
            ex.printStackTrace();
        }
    }

    /**
     * Load a settings object from a file called settings.dat, and return it.
     * @return
     */
    public static Settings load(){
        Settings settings = null;

        // Try to load in the Settings object
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(SETTINGS_FILE_NAME));
            settings = (Settings) in.readObject();
            in.close();
            
            // Read the available languages from an XML file
            LanguagesFileReader reader = new LanguagesFileReader();
            settings.availableLangs = reader.readList(new File(LANGUAGES_FILE_NAME));
            
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find file "+SETTINGS_FILE_NAME);
        }catch(IOException ex){
            System.out.println("There was a problem reading from the file.");
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            System.out.println("Could not locate appropriate class.");
        }finally{
            // Make sure that if we couldn't load the object, that it doesn't remain null
            if(settings==null)
                settings = new Settings();
        }

        return settings;
    }
	
    /**
     * Generate a string from this object, for debugging purposes.
     */
	public String toString() {
		return "SETTINGS\n--------"
				+ "\nIgnore comments: " + ignoreComments
				+ "\nSelected language: " + selectedLangIndex
				+ "\nRoot folder: " + rootFolder.toString();
	}
}
