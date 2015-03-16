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
	
	public boolean ignoreComments;			// Whether or not to ignore comments when searching files
	public int selectedLanguageIndex;		// The index of the currently selected language
	public File rootFolder;					// The root folder currently selected
	
	private static final String SETTINGS_FILE_NAME = "settings.dat";
	
	/**
	 * Save a Settings object to a file called settings.dat
	 * @param settings
	 */
	public static void save(Settings settings){
		// Try to serialize the object
		try{
			// Create a new ObjectOutputStream to put the object into the file
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SETTINGS_FILE_NAME));

			// Write the object to the file
			out.writeObject(settings);

			// Remember to close the stream!
			out.close();
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
		
		// Try to load in the Facebook object
		try {
			// Create a new ObjectInputStream and FileInputStream to read from the file
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(SETTINGS_FILE_NAME));

			// Try to load in the object
			settings = (Settings) in.readObject();

			// Remember to close the stream!
			in.close();
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
		this.ignoreComments = true;
		this.selectedLanguageIndex = 0;
		this.rootFolder = new File("");
	}
	
	public Settings(boolean ignoreComments, int selectedLanguageIndex, File rootFolder){
		this.ignoreComments = ignoreComments;
		this.selectedLanguageIndex = selectedLanguageIndex;
		this.rootFolder = rootFolder;
	}
	
	public static void main(String[] args){
		Settings newSettings = Settings.load();
		
		System.out.println("Ignore comments: "+newSettings.ignoreComments+"\nLang index: "+newSettings.selectedLanguageIndex+
				"\nFile name: "+newSettings.rootFolder.getName());
	}
}
