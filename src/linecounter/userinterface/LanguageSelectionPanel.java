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

package linecounter.userinterface;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;

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

	private JComboBox<String> languagesDropDown;

	/**
	 * Construct a new panel for language selection.
	 *
	 * @param set Settings to use.
	 */
	public LanguageSelectionPanel(Settings set) {

		this.settings = set;

		languagesDropDown  = new JComboBox<>(settings.languages.availableLangs());

		// Revert the selected language to 0 if the selected index is out of bounds
		if (settings.selectedLangIndex < languagesDropDown.getItemCount()) {
			languagesDropDown.setSelectedIndex(settings.selectedLangIndex);
		} else {
			languagesDropDown.setSelectedIndex(0);
		}
		
		// Changing this dropdown will update the preferences.
        languagesDropDown.addActionListener(new ActionListener() { 
        	@Override 
            public void actionPerformed(ActionEvent ev) { 
                settings.selectedLangIndex = ((JComboBox<?>) ev.getSource()).getSelectedIndex(); 
            } 
        }); 

        setLayout(new GridLayout(2, 1));
        add(new JLabel("Choose your language"));
        add(languagesDropDown);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	@Override
	public void update(RunResult result) {

	}
}
