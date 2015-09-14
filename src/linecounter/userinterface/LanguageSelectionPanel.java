package linecounter.userinterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JTable detailsTable;
	private JButton newLangButton;
	
	public LanguageSelectionPanel(Settings set) {
		
		this.settings = set;
		
		setLayout(new GridLayout(3, 1));
		
		languagesDropDown  = new JComboBox<Object>(settings.availableLangs.availableLangs());
        languagesDropDown.setSelectedIndex(settings.selectedLangIndex);
        
        detailsTable = new JTable();
        
        newLangButton = new JButton("Define New Language");
        
        languagesDropDown.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                // Update the settings
                settings.selectedLangIndex = ((JComboBox<?>) ev.getSource()).getSelectedIndex();
            }
        });
        
        add(languagesDropDown);
        add(detailsTable);
        add(newLangButton);
        setBorder(new TitledBorder("Language"));
	}

	@Override
	public void update(RunResult result) {
		
	}

}
