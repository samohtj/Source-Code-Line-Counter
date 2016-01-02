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

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import linecounter.RunResult;
import linecounter.Settings;

/**
 * A JLabel object that displays the results of a program execution.
 * This class contains all the components needed to display information about a program execution.
 * Included are a line number display label, a file number display label, and a folder number display
 * label. All the information is created on execution.
 * <p>I will figure out how to make the results and sensors update in the future.
 * @author Jonathan Thomas
 *
 */
// Suppress serial warnings. We aren't ever going to serialize this object.
@SuppressWarnings("serial")
public class ResultsPanel extends SourceCounterPanel {

    // Results labels
    private JLabel numLinesLabel    = new JLabel();
    private JLabel numFilesLabel    = new JLabel();
    private JLabel numFoldersLabel  = new JLabel();

    Settings settings;

	/**
	 * Construct a new JPanel to display a runtime result.
	 * @param  set Settings to use.
	 * @return     New ResultsPanel object.
	 */
	public ResultsPanel(Settings set) {
		setLayout(new GridLayout(3, 2));
        add(numLinesLabel);
        add(numFilesLabel);
        add(new JLabel());
        add(numFoldersLabel);
        setBorder(new TitledBorder("Results"));
	}

	/**
	 * Update the panel.
	 * @param result RunResult to pull data from.
	 */
	@Override
	public void update(RunResult result) {
		numLinesLabel.setText("Total lines: "+result.totalLines());
        numFilesLabel.setText("Total files searched: "+result.numFiles());
        numFoldersLabel.setText("Total folders searched: "+result.numFolders());
	}
}
