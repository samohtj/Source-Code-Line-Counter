package linecounter.userinterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

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
	private JScrollPane detailsPane;
	private JButton newLangButton;
	
	public LanguageSelectionPanel(Settings set) {
		
		this.settings = set;
		
		setLayout(new GridLayout(3, 1));
		
		languagesDropDown  = new JComboBox<Object>(settings.availableLangs.availableLangs());
        languagesDropDown.setSelectedIndex(settings.selectedLangIndex);
        
        detailsPane = populateTable();
        
        newLangButton = new JButton("Define New Language");
        
        languagesDropDown.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                // Update the settings
                settings.selectedLangIndex = ((JComboBox<?>) ev.getSource()).getSelectedIndex();
            }
        });
        
        add(languagesDropDown);
        add(detailsPane);
        add(newLangButton);
        setBorder(new TitledBorder("Language"));
	}
	
	private JScrollPane populateTable() {
		String[] columnNames = {"Extensions", "Comment Characters"};
		
		// Decide how many rows the table should have. The comment character column will never have
		// more than 3 items (conceivably), so if there are 3 or fewer
		int extensionCount = settings.selectedLang().extensions.length;
		int commentCharCount = (int) settings.selectedLang().lineCommentChars.length + 
				(settings.selectedLang().blockCommentDelimiters.length / 2);
		Object[][] data = new Object[(extensionCount>commentCharCount? extensionCount : commentCharCount)][2];
		
		for(int i = 0; i < data.length; i++) {
			if(i<settings.selectedLang().extensions.length) {
				data[i][0] = settings.selectedLang().extensions[i].toString();
			} else {
				data[i][0] = "";
			}
			
			switch(i) {
			case 0:
				if(i<settings.selectedLang().lineCommentChars.length) {
					data[i][1] = settings.selectedLang().lineCommentChars[i];
				}
				break;
			case 1:
				if(settings.selectedLang().blockCommentDelimiters.length > 0) {
					data[i][1] = settings.selectedLang().blockCommentDelimiters[0] + " ... "
							+ settings.selectedLang().blockCommentDelimiters[1];
				}
				break;
			case 2:
				if(i<settings.selectedLang().lineCommentChars.length) {
					data[i][1] = settings.selectedLang().lineCommentChars[i];
				}
				break;
			default:
				data[i][1] = "";
			}
			
			
		}
		return new JScrollPane(new JTable(data, columnNames));
	}

	@Override
	public void update(RunResult result) {
		
	}

}
