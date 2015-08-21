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
	
	private Document composeXML(LanguagesList list) {
		Document doc = new Document();
		
		/*
		 * 	<languages>
		 * 		<language name="Java">
		 * 			<extensions>
		 * 				<extension text="java"/>
		 * 			</extensions>
		 * 			<lineCommentChar text="//"/
		 * 			<blockCommentChars opening="/*" closing="* /"/>
		 * 		</language>
		 * 	</languages>
		 */
		
		// The root element is the base element for the whole XML file, the one that encloses everything
		Element root = new Element("languages");
		doc.setRootElement(root);
		
		Element languageElement = new Element("language");
		Element extensionsElement = new Element("extensions");
		Element extensionElement = new Element("extension");
		Element lineComElement = new Element("lineCommentChar");
		Element blockComElement = new Element("blockCommentChars");
		
		languageElement.setAttribute("name", "Java");
		extensionElement.setAttribute("text", "java");
		lineComElement.setAttribute("text", "//");
		blockComElement.setAttribute("opening", "/*");
		blockComElement.setAttribute("closing", "*/");
		
		extensionsElement.addContent(extensionElement);
		languageElement.addContent(extensionsElement);
		languageElement.addContent(lineComElement);
		languageElement.addContent(blockComElement);
		
		root.addContent(languageElement);
		
		return doc;
	}
	
	public void writeList(LanguagesList list, File file) {
		
		Document doc = composeXML(list);
		 
		try {
			XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
			out.output(doc, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		LanguagesFileWriter writer = new LanguagesFileWriter();
		writer.writeList(new LanguagesList(), new File("languages.xml"));

	}

}
