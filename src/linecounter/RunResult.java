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

/**
 * Result of a run of the program.
 * @author Jonathan Thomas
 *
 */
public class RunResult {
    private long numFiles   = 0;
    private long numFolders = 0;
    private long totalLines = 0;

    /**
     * Construct a new result.
     * @param   numFiles Number of files detected.
     * @param   numFolders Number of folders detected.
     * @param   totalLines Number of lines detected.
     * @return  New RunResult object.
     */
    public RunResult(long numFiles, long numFolders, long totalLines) {
    	this.numFiles = numFiles;
    	this.numFolders = numFolders;
    	this.totalLines = totalLines;
    }

    public long numFiles() { return numFiles; }
    public long numFolders() { return numFolders; }
    public long totalLines() { return totalLines; }
}
