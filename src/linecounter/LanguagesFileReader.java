package linecounter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * LanguagesFileReader will take a given XML file, parse its data, and generate a LanguagesList
 * object from the file's contents. It makes use of the JDOM library. Check them out 
 * <a href="http://jdom.org">here.</a>
 * @author Jonathan Thomas
 *
 */
public class LanguagesFileReader {
	
	/**
	 * Parse an XML document, and generate a LanguagesList from the data found inside.
	 * <br>The template that this method reads can be found in {@code LangaugesFileWriter}.
	 * @param doc
	 * @return A full LanguagesList object.
	 */
	private LanguagesList parseXML(Document doc) {
		LanguagesList list = new LanguagesList();
		
		Element root = doc.getRootElement();
		
		for(Element lang: root.getChildren("language")) {
			String name = lang.getAttributeValue("name");
			int index = Integer.parseInt(lang.getAttributeValue("index"));
			
			// These variables require loops, so we'll set up the empty lists beforehand.
			ArrayList<String> extensions = new ArrayList<String>();
			ArrayList<String> lineCommentChars = new ArrayList<String>();
			ArrayList<String> blockCommentChars = new ArrayList<String>();
			
			// Loop through the "extension" elements contained by the "extensions" element
			for(Element ext: lang.getChild("extensions").getChildren("extension")) {
				extensions.add(ext.getAttributeValue("text"));
			}
			
			// Loop through all the possible "lineCommentChar" elements in the "language" element
			// Yes, there may be more than one (looking at you, PHP).
			for(Element ch: lang.getChildren("lineCommentChar")) {
				lineCommentChars.add(ch.getAttributeValue("text"));
			}
			
			// Loop through all the possible "blockCommentChars" elements in the "language" element
			// There should only be one, but you never know.
			for(Element ch: lang.getChildren("blockCommentChars")) {
				blockCommentChars.add(ch.getAttributeValue("opening"));
				blockCommentChars.add(ch.getAttributeValue("closing"));
			}
			
			// Create a new Language object from the information we just gathered, and return it.
			Language language = new Language(name, 
					index, 
					extensions.toArray(new String[] {}), 
					lineCommentChars.toArray(new String[] {}), 
					blockCommentChars.toArray(new String[] {}));
			
			list.add(language);
		}
		
		return list;
	}
	
	/**
	 * Parse an XML file, and generate a LanguagesList object from the data found inside.
	 * @param file
	 * @return
	 */
	public LanguagesList readList(File file) {
		Document doc = new Document();
		
		SAXBuilder builder = new SAXBuilder();
		
		try {
			doc = builder.build(file);
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		
		return parseXML(doc);
	}

	public static void main(String[] args) {
		LanguagesFileReader reader = new LanguagesFileReader();
		LanguagesList list = reader.readList(new File("languages.xml"));
		list.allLangs();

	}

}
