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
    
    public boolean ignoreComments;      // Whether or not to ignore comments when searching files
    public Language selectedLanguage;   // The index of the currently selected language
    public File rootFolder;             // The root folder currently selected
    public String languagesFileName;	// File storing available programming languages
    
    private LanguagesList availableLangs;
    
    public void selectLanguage(int index) {
    	selectedLanguage = availableLangs.get(index);
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
            
            // TODO Add language saving capabilities
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
            
            // TODO Add language loading capabilities
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

    public Settings(){
        this(true, new Language(), new File(""));
    }

    public Settings(boolean ignoreComments, Language selectedLanguage, File rootFolder){
        this.ignoreComments = ignoreComments;
        this.selectedLanguage = selectedLanguage;
        this.rootFolder = rootFolder;
    }

	public static Language selectedLang() {
		// TODO Auto-generated method stub
		return null;
	}
}
