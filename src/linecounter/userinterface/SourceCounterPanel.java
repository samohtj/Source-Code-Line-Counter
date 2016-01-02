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

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import linecounter.RunResult;
import linecounter.Settings;

/**
 * Abstract definition of a panel for use in this application.
 * @author Jonathan Thomas
 *
 */
//Suppress serial warnings. We aren't ever going to serialize this object.
@SuppressWarnings("serial")
public abstract class SourceCounterPanel extends JPanel {

	Settings settings;

	/**
	 * Update the panel.
	 * @param result Runtim result to pull data from.
	 */
	public abstract void update(RunResult result);

	/**
	 * Add a listener to any buttons on the panel.
	 * @param actionListener Listener to add.
	 */
	public void addButtonListener(ActionListener actionListener) {

	}
}
