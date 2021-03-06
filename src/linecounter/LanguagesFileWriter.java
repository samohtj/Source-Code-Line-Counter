/*
 * Copyright (C) 2016 Jonathan Thomas
 *
 * Source-Code-Line-Counter: An application for counting the number of lines in your source code.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

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
 * @author Jonathan Thomas
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
	 *     </language>
	 * </languages>
	 * }
	 * </pre>
	 * @param list List to serialize.
	 * @return A fully formatted Document object
	 */
	private Document composeXML(LanguagesList list) {
		Document doc = new Document();

		// The root element is the base element for the whole XML file, the one that encloses everything
		Element root = new Element("languages");
		doc.setRootElement(root);

		for (Language language: list.allLangs()) {

			// Create a new language element, and add the name and index
			Element languageElement = new Element("language");
			languageElement.setAttribute("name", language.name);

			// Create an extensions element to hold all the available file extensions, and then
			// create extension elements for each available file extension in the language.
			Element extensionsElement = new Element("extensions");
			for (String ext: language.extensions) {
				Element extensionElement = new Element("extension");
				extensionElement.setAttribute("text", ext);
				extensionsElement.addContent(extensionElement);
			}
			languageElement.addContent(extensionsElement);

			// Create a lineCommentChars element for each available line comment character string.
			for (String text: language.lineCommentChars) {
				Element lineComElement = new Element("lineCommentChar");
				lineComElement.setAttribute("text", text);
				languageElement.addContent(lineComElement);
			}

			// Add the newly populated language element to the root element.
			root.addContent(languageElement);
		}

		return doc;
	}

	/**
	 * Convert a given LanguagesList object to XML format, and write it to an XML file.
	 * @param list List to serialize.
	 * @param file File to write to.
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

	// Be very carfeul with this. Back up all your configuration files, or they could be lost.
	/*
	public static void main(String[] args) {
		LanguagesFileWriter writer = new LanguagesFileWriter();
		writer.writeList(new LanguagesList(), new File("languages.xml"));

	}
	*/
}
