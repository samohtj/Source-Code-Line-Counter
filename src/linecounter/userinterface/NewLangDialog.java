package linecounter.userinterface;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import linecounter.Settings;

public class NewLangDialog {

	public static void showDialog(Settings settings) {
		JTextField nameField = new JTextField(10);
		JTextField extensionsField = new JTextField(10);
		JTextField lineComField = new JTextField(10);
		JTextField blockComField = new JTextField(10);

		JPanel myPanel = new JPanel(new GridLayout(4, 2));
		myPanel.add(new JLabel("Name:"));
		myPanel.add(nameField);
		myPanel.add(new JLabel("Extensions:"));
		myPanel.add(extensionsField);
		myPanel.add(new JLabel("Line comment characters:"));
		myPanel.add(lineComField);
		myPanel.add(new JLabel("Block comment characters:"));
		myPanel.add(blockComField);

		int result = JOptionPane.showConfirmDialog(null, myPanel,
				"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			System.out.println("x value: " + nameField.getText());
			System.out.println("y value: " + extensionsField.getText());
		}
	}

	public NewLangDialog() {
		// TODO Create language adding dialog.
	}
}
