package linecounter.userinterface;

import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import linecounter.Language;
import linecounter.Settings;

/**
 * A small dialog box that will get all the information needed to add a new language.
 * @author Jonathan Thomas
 *
 */
// Suppress an "unused" warning on the Arrays import, in case we ever need the debug output again.
@SuppressWarnings("unused")
public class NewLangDialog {

	/**
	 * Show a dialog to get information on a new language.
	 * @param settings Settings to use.
	 */
	public static void showDialog(Settings settings) {
		JTextField nameField = new JTextField(10);
		JTextField extensionsField = new JTextField(10);
		JTextField lineComField = new JTextField(10);
		JTextField blockComField = new JTextField(10);

		JPanel panel = new JPanel(new GridLayout(4, 2));
		panel.add(new JLabel("Name:"));
		panel.add(nameField);
		panel.add(new JLabel("Extensions:"));
		panel.add(extensionsField);
		panel.add(new JLabel("Line comment characters:"));
		panel.add(lineComField);
		panel.add(new JLabel("Block comment characters:"));
		panel.add(blockComField);

		int result = JOptionPane.showConfirmDialog(null, panel,
				"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {

			// Create String objects that can be manipulated, and remove all whitespace characters
			String nameText = nameField.getText().replaceAll("\\s+","");
			String extsText = extensionsField.getText().replaceAll("\\s+","");
			String lncmText = lineComField.getText().replaceAll("\\s+","");
			String blcmText = blockComField.getText();

			String[] extensions = extsText.split(",");
			String[] lineComChars = lncmText.split(",");
			String[][] blockComChars;
			
			if(!blockComField.getText().equals("")) {
				blockComChars = new String[][]{blcmText.split(" ")};
				settings.languages.addLanguage(
						new Language(nameText, extensions, lineComChars, blockComChars));
			} else {
				settings.languages.addLanguage(
						new Language(nameText, extensions, lineComChars));
			}

			/*
			System.out.println("Name: " + nameText);
			System.out.println("Extensions: " + Arrays.toString(extensions));
			System.out.println("Line comment characters: " + Arrays.toString(lineComChars));
			System.out.println("Block comment characters: " + Arrays.toString(blockComChars));
			*/

			// TODO Data validation
			
		}
	}
}
