package linecounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class LanguagesFileReader {
	
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
