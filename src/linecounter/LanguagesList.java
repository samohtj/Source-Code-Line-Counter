package linecounter;

import java.util.ArrayList;

/**
 * This class simply stores a catalog of available languages in the form of an ArrayList.
 * @author Jonathan Thomas
 *
 */
public class LanguagesList extends ArrayList<Language>{
	private static final long serialVersionUID = 8102198895664199057L;

	public Language get(int index) {
    	// If the index asked for is out of bounds, default to Java
    	if(index > size() - 1)
    		return new Language();
    	
        return get(index);
    }
    
    /**
     * Return a new ArrayList containing all the Language objects stored in this list.
     * @return
     */
    public ArrayList<Language> allLangs() {
    	return new ArrayList<Language>(this);
    }

    public String[] availableLangs() {
    	
    	String[] langs = new String[size()];
    	
    	for(int i = 0; i < size(); i++) {
    		langs[i] = get(i).name;
    	}
    	
    	return langs;
    }
}
