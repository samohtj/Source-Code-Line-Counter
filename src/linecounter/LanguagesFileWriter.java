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

public class LanguagesFileWriter {
	
	private void writeXML() {
		
		Document doc = new Document();
		
		// The root element is the base element for the whole XML file, the one that encloses everything
		Element root = new Element("languages");
		doc.setRootElement(root);
		
		Element languageElement = new Element("language");
		Element nameElement = new Element("name");
		
		nameElement.addContent(new Text("Java"));
		languageElement.addContent(nameElement);
		
		root.addContent(languageElement);
		
		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		
		try {
			out.output(doc, new FileOutputStream(new File("languages.xml")));
			System.out.println("done");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		LanguagesFileWriter writer = new LanguagesFileWriter();
		writer.writeXML();

	}

}
