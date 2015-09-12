package linecounter.userinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

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
	
	public LanguageSelectionPanel(Settings set) {
		languagesDropDown  = new JComboBox<Object>(settings.availableLangs.availableLangs());
        languagesDropDown.setSelectedIndex(settings.selectedLangIndex);
        
     // LANGUAGES LIST DROPDOWN
        languagesDropDown.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                // Update the settings
                settings.selectedLangIndex = ((JComboBox<?>) ev.getSource()).getSelectedIndex();
            }
        });
        
        add(languagesDropDown);
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		
	}

}
