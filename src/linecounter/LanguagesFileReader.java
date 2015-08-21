package linecounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
		
		System.out.println("Root: " + doc.getRootElement());
		
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

	}

}
