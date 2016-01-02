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
	 * @param doc Document to parse.
	 * @return A full LanguagesList object.
	 */
	private LanguagesList parseXML(Document doc) {
		LanguagesList list = new LanguagesList();

		Element root = doc.getRootElement();

		for (Element lang: root.getChildren("language")) {
			String name = lang.getAttributeValue("name");

			// These variables require loops, so we'll set up the empty lists beforehand.
			ArrayList<String> extensions = new ArrayList<String>();
			ArrayList<String> lineCommentChars = new ArrayList<String>();
			ArrayList<String[]> blockCommentChars = new ArrayList<String[]>();

			// Loop through the "extension" elements contained by the "extensions" element
			for (Element ext: lang.getChild("extensions").getChildren("extension")) {
				extensions.add(ext.getAttributeValue("text"));
			}

			// Loop through all the possible "lineCommentChar" elements in the "language" element
			// Yes, there may be more than one (looking at you, PHP).
			for (Element ch: lang.getChildren("lineCommentChar")) {
				lineCommentChars.add(ch.getAttributeValue("text"));
			}

			// Loop through all the possible "blockCommentChars" elements in the "language" element
			// There should only be one, but you never know.
			for (Element ch: lang.getChildren("blockCommentChars")) {
				blockCommentChars.add(new String[]{ch.getAttributeValue("opening"),
						ch.getAttributeValue("closing")});
			}

			// Create a new Language object from the information we just gathered, and return it.
			Language language;
			language = new Language(name,
					extensions.toArray(new String[] {}),
					lineCommentChars.toArray(new String[] {}));

			list.add(language);
		}

		return list;
	}

	/**
	 * Parse an XML file, and generate a LanguagesList object from the data found inside.
	 * @param file File to write to.
	 * @return Populated language list.
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

	// Be very careful with this. Back up all your configuration files before running, or they
	// could be lost.
	/*
	public static void main(String[] args) {
		LanguagesFileReader reader = new LanguagesFileReader();
		LanguagesList list = reader.readList(new File("languages.xml"));
		list.allLangs();

	}
	*/
}
