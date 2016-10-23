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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import linecounter.RunResult;
import linecounter.Settings;

/**
 * A JLabel object that displays the run controls of the program.
 * @author Jonathan Thomas
 *
 */
//Suppress serial warnings. We aren't ever going to serialize this object.
@SuppressWarnings("serial")
public class RunProgramPanel extends SourceCounterPanel {

	private JButton rerunButton;
    private JCheckBox ignoreCommentsCheckbox;

    Settings settings;

	/**
	 * Construct a panel to hold runtime controls.
	 * @param  set Settings to use.
	 * @return     New RunProgramPanel object.
	 */
	public RunProgramPanel(Settings set) {

		this.settings = set;

		rerunButton = new JButton("Recount");
		ignoreCommentsCheckbox = new JCheckBox("Include comments and whitespace");

		ignoreCommentsCheckbox.setSelected(settings.ignoreComments);

        ignoreCommentsCheckbox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		settings.ignoreComments = !ignoreCommentsCheckbox.isSelected();
        	}
        });

        setLayout(new GridLayout(1, 2));
        add(ignoreCommentsCheckbox);
        add(rerunButton);
	}

	/**
	 * Add an ActionListener to the button in this object.
	 * @param listener Listener to add.
	 */
	public void addButtonListener(ActionListener listener) {
		rerunButton.addActionListener(listener);
	}

	/**
	 * Update the panel.
	 * @param result Runtim result to pull data from.
	 */
	@Override
	public void update(RunResult result) {

	}
}
