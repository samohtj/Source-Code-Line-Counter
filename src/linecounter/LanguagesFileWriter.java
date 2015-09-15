package linecounter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * LanguagesFileWriter is used to convert a LanguagesList object to XML format, and then write that
 * data to an XML file. It makes use of the JDOM library. Check them out
 * <a href="http://jdom.org">here.</a>
 * @author Jonathan
 *
 */
public class LanguagesFileWriter {
	
	/**
	 * This method will compose an XML document from a given LanguagesList object. It will format
	 * the document according to this template:
	 * <pre>
	 * {@code
	 * <languages>
	 *     <language name="Java" index=3>
	 *         <extensions>
	 *             <extension text="java"/>
	 *         </extensions>
	 *         <lineCommentChar text="//"/
	 *         <blockCommentChars opening="/*" closing="* /"/>
	 *     </language>
	 * </languages>
	 * }
	 * </pre>
	 * @param list 
	 * @return A fully formatted Document object
	 */
	private Document composeXML(LanguagesList list) {
		Document doc = new Document();
		
		// The root element is the base element for the whole XML file, the one that encloses everything
		Element root = new Element("languages");
		doc.setRootElement(root);
		
		for(Language language: list.allLangs()) {
			
			// Create a new language element, and add the name and index
			Element languageElement = new Element("language");
			languageElement.setAttribute("name", language.name);
			
			// Create an extensions element to hold all the available file extensions, and then
			// create extension elements for each available file extension in the language.
			Element extensionsElement = new Element("extensions");
			for(String ext: language.extensions) {
				Element extensionElement = new Element("extension");
				extensionElement.setAttribute("text", ext);
				extensionsElement.addContent(extensionElement);
			}
			languageElement.addContent(extensionsElement);
			
			// Create a lineCommentChars element for each available line comment character string.
			for(String text: language.lineCommentChars) {
				Element lineComElement = new Element("lineCommentChar");
				lineComElement.setAttribute("text", text);
				languageElement.addContent(lineComElement);
			}
			
			// Create a blockCommentChars element to store opening and closing block comment characters.
			for(String[] text: language.blockCommentDelimiters) {
				Element blockComElement = new Element("blockCommentChars");
				blockComElement.setAttribute("opening", text[0]);
				blockComElement.setAttribute("closing", text[1]);
				languageElement.addContent(blockComElement);	
			}
			
			// Add the newly populated language element to the root element.
			root.addContent(languageElement);
		}
		
		return doc;
	}
	
	/**
	 * Convert a given LanguagesList object to XML format, and write it to an XML file.
	 * @param list
	 * @param file
	 */
	public void writeList(LanguagesList list, File file) {
		
		Document doc = composeXML(list);
		 
		try {
			XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
			out.output(doc, new FileOutputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		LanguagesFileWriter writer = new LanguagesFileWriter();
		writer.writeList(new LanguagesList(), new File("languages.xml"));

	}

}
