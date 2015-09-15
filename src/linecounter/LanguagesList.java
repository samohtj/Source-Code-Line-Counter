package linecounter;

import java.util.ArrayList;

/**
 * This class simply stores a catalog of available languages in the form of an ArrayList.
 * @author Jonathan Thomas
 *
 */
public class LanguagesList extends ArrayList<Language>{
	private static final long serialVersionUID = 8102198895664199057L;

	/**
	 * Get the language at the specified position in the list.
	 * @param index
	 */
	public Language get(int index) {
    	// If the index asked for is out of bounds, default to Java
    	if(index > size() - 1)
    		return new Language();
    	
        return super.get(index);
    }
    
    /**
     * Return a new ArrayList containing all the Language objects stored in this list.
     * @return
     */
    public ArrayList<Language> allLangs() {
    	return new ArrayList<Language>(this);
    }
    
    /**
     * Add a new language to the list. If a language with the same name already exists, do not add it.
     * @param lang
     */
    public void addLanguage(Language lang) {
    	for(Language existingLang: this) {
    		if(existingLang.name.equals(lang.name))
    			return;
    	}
    	
    	add(lang);
    }

    /**
     * Create an array holding the names of all the languages in the list.
     * @return String array with names of languages.
     */
    public String[] availableLangs() {
    	
    	String[] langs = new String[size()];
    	
    	for(int i = 0; i < size(); i++) {
    		langs[i] = get(i).name;
    	}
    	
    	return langs;
    }
}
