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
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import linecounter.NoFileChosenException;
import linecounter.RunResult;
import linecounter.Settings;

/**
 * A JLabel object that displays the project selection controls.
 * @author Jonathan Thomas
 *
 */
//Suppress serial warnings. We aren't ever going to serialize this object.
@SuppressWarnings("serial")
public class ProjectSelectionPanel extends SourceCounterPanel {

	private JLabel rootFolderLabel		= new JLabel("Root folder: None selected");
    private JButton chooseRootButton	= new JButton("Choose root folder");

	/**
	 * Construct a new panel for project slection.
	 * @param  set Settings to use.
	 * @return     New ProjectSelectionPanel object.
	 */
    public ProjectSelectionPanel(Settings set) {
    	this.settings = set;

    	rootFolderLabel.setText("Root folder: "+settings.rootFolder.getName());

        chooseRootButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    settings.chooseRootFolder();
                    rootFolderLabel.setText("Root folder: "+settings.rootFolder.getName());
                } catch (NoFileChosenException e) {}
            }
        });

        setLayout(new GridLayout(2, 1));
        add(chooseRootButton);
        add(rootFolderLabel);
        setBorder(new TitledBorder("Project"));
    }

	/**
	 * Update the panel.
	 * @param result Result of the run to take data from.
	 */
	@Override
	public void update(RunResult result) {
		// TODO Make it update.
	}

}
