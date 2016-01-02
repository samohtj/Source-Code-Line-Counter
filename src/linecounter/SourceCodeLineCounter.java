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

package linecounter;

import javax.swing.JFrame;

import linecounter.userinterface.UserInterface;

/**
 * Main program entry point.
 * @author Jonathan Thomas
 *
 */
public class SourceCodeLineCounter {

	/**
	 * Main program entry point.
	 * This is a Swing application, so you don't get to pass in any parameters. Not yet, anyways.
	 * It's possible I might make it run from the command line later on.
	 * @param args
	 */
    public static void main(String[] args) {
        UserInterface frame = new UserInterface();
        frame.setTitle("Source Code Line Counter");
        frame.setSize(725, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
    }
}
