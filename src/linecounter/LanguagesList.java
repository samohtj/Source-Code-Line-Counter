package linecounter;

import java.io.File;
import java.util.ArrayList;

/**
 * This class simply stores a cataogue of available languages, their file extensions, and their comment characters
 * @author Jonathan Thomas
 *
 */
public class LanguagesList{
	
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
    	return new ArrayList<Language>(list);
    }

    public String[] availableLangs() {
    	
    	String[] langs = new String[size()];
    	
    	for(int i = 0; i < size(); i++) {
    		langs[i] = list.get(i).name;
    	}
    	
    	return langs;
    }



	public int size() {
		return list.size();
	}
}
