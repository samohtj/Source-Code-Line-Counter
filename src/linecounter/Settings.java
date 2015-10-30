package linecounter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.Serializable;

import javax.swing.JFileChooser;

/**
 * Stores the settings the user has given.
 * @author Jonathan Thomas
 *
 */
public class Settings implements Serializable {

    private static final long serialVersionUID = -6952939008177887335L;

    private static final String SETTINGS_FILE_NAME 	= "settings.dat";
    private static final String LANGUAGES_FILE_NAME = "languages.xml";

    public transient LanguagesList languages = new LanguagesList();

    // Make sure that these all have default values, or you might have trouble later.
    // (By trouble I mean NullPointerExceptions)
    public boolean ignoreComments = true;
    public int selectedLangIndex = 0;
    public File rootFolder = null;

    /**
     * Create a new Settings object with the default settings.
     * @return New Settings object.
     */
    public Settings() {
        this(true, 0, null);
    }

    /**
     * Create a new Settings object with the given settings.
     * @param ignoreComments Whether to ignore comments.
     * @param selectedLanguageIndex The index of the selected language.
     * @param rootFolder Root foler of the selected project.
     * @return New Settings object.
     */
    public Settings(boolean ignoreComments, int selectedLanguageIndex, File rootFolder) {
        this.ignoreComments = ignoreComments;
        this.selectedLangIndex = selectedLanguageIndex;
        this.rootFolder = rootFolder;
    }

    /**
     * Set the selected language.
     * @param index
     */
    public void selectLanguage(int index) {
    	if (index > languages.size() - 1) {
    		selectedLangIndex = languages.size() - 1;
        } else if (index < 0) {
    		selectedLangIndex = 0;
    	} else {
    		selectedLangIndex = index;
        }
    }

    /**
     * Return the currently selected language. If there are no languages available, generate a new one.
     * @return The currently selected language.
     */
	public Language selectedLang() {
		if (languages.size() > 0) {
			return languages.get(selectedLangIndex);
		} else {
			return new Language();
        }
	}

	/**
     * Choose the root folder of a project using a JFileChooser dialog.
     * @return A file on the system somewhere, to be treated as the root folder of a project.
     * @throws NoFileChosenException If no file is chosen (i.e. the user hits the "cancel" button)
     */
    public void chooseRootFolder() throws NoFileChosenException {
        // Create a new file chooser dialog
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // If the user hits the cancel button, throw an exception
        if (chooser.showOpenDialog(null)!=JFileChooser.APPROVE_OPTION) {
            throw new NoFileChosenException();
        }

        rootFolder = chooser.getSelectedFile();
    }

    /**
     * Save a Settings object to a file called settings.dat
     * @param settings Settings to use.
     */
    public static void save(Settings settings){
        // Try to serialize the object
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SETTINGS_FILE_NAME));
            out.writeObject(settings);
            out.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find file "+SETTINGS_FILE_NAME);
        } catch (IOException ex) {
            System.out.println("There was a problem writing to the file.");
            ex.printStackTrace();
        } finally {
        	// Write all the available languages to an XML file
            LanguagesFileWriter writer = new LanguagesFileWriter();
            writer.writeList(settings.languages, new File(LANGUAGES_FILE_NAME));
        }
    }

    /**
     * Load a settings object from a file called settings.dat, and return it.
     * @return Generated settings object.
     */
    public static Settings load() {
        Settings settings = null;

        // Try to load in the Settings object
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(SETTINGS_FILE_NAME));
            settings = (Settings) in.readObject();
            in.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find file "+SETTINGS_FILE_NAME);
        } catch (IOException ex) {
            System.out.println("There was a problem reading from the file.");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Could not locate appropriate class.");
        } finally {
            // Make sure that if we couldn't load the object, that it doesn't remain null
            if (settings==null) {
                settings = new Settings();
            }

            // Read the available languages from an XML file
            LanguagesFileReader reader = new LanguagesFileReader();
            settings.languages = reader.readList(new File(LANGUAGES_FILE_NAME));
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
