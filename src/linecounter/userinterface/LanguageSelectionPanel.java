package linecounter.userinterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import linecounter.RunResult;
import linecounter.Settings;

/**
 * A JLabel object that displays the language selection controls.
 * @author Jonathan Thomas
 *
 */
//Suppress serial warnings. We aren't ever going to serialize this object.
@SuppressWarnings("serial")
public class LanguageSelectionPanel extends SourceCounterPanel {

	private JComboBox<?> languagesDropDown;
	private JTable detailsTable = new JTable();
	private JScrollPane detailsPane = new JScrollPane(detailsTable);
	private JButton newLangButton;

	/**
	 * Construct a new panel for language selection.
	 *
	 * @param set Settings to use.
	 */
	public LanguageSelectionPanel(Settings set) {

		this.settings = set;

		languagesDropDown  = new JComboBox<Object>(settings.availableLangs.availableLangs());
        languagesDropDown.setSelectedIndex(settings.selectedLangIndex);

        newLangButton = new JButton("Define New Language");

		// User should not be able to edit this table.
        detailsTable.setCellSelectionEnabled(false);

		// TODO Make table update.
        updateTable();

        languagesDropDown.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent ev) {
                // Update the settings
                settings.selectedLangIndex = ((JComboBox<?>) ev.getSource()).getSelectedIndex();
                updateTable();
            }
        });

        newLangButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewLangDialog.showDialog(settings);
				languagesDropDown = new JComboBox<Object>(settings.availableLangs.availableLangs());
			}

        });

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.add(languagesDropDown);
        topPanel.add(newLangButton);

        setLayout(new GridLayout(2, 1));
        add(topPanel);
        add(detailsPane);
        setBorder(new TitledBorder("Language"));
	}

	/**
	 * Update the table with the details of the selected language.
	 *
	 */
	private void updateTable() {
		String[] columnNames = {"Extensions", "Comment Characters"};

		// Decide how many rows the table should have. The maximum number of rows will equal the
		// length of either the extensions list or comment character list.
		int extensionCount = settings.selectedLang().extensions.length;
		int commentCharCount = settings.selectedLang().allCommentChars().length;
		Object[][] data = new Object[(extensionCount>commentCharCount?
				extensionCount : commentCharCount)][2];

		for (int i = 0; i < data.length; i++) {
			if (i < extensionCount) {
				data[i][0] = settings.selectedLang().extensions[i];
			} else {
				data[i][0] = "";
			}

			if (i < commentCharCount) {
				data[i][1] = settings.selectedLang().allCommentChars()[i];
			} else {
				data[i][1] = "";
			}
		}

		DefaultTableModel model = (DefaultTableModel) detailsTable.getModel();
		model.setDataVector(data, columnNames);
	}

	@Override
	public void update(RunResult result) {

	}
}
