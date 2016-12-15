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

import java.io.File;

/**
 * ProjectReader will take a given folder, search through it, and use a SourceFileReader to process
 * each file found in the project.
 * @author Jonathan Thomas
 *
 */
public class ProjectReader {

    /*
     * Store the overall number of lines, files, and folders for the whole project
     */
    private long numFiles   = 0;
    private long numFolders = 0;

    private long totalLines     = 0;

    public Settings settings;

    /*
     * Getters for the various line counts
     */
    public long totalLines() { return totalLines; }
    public long numFiles() { return numFiles; }
    public long numFolders() { return numFolders; }

    /**
     * Construct a project reader from the given settings.
     * @param settings Settings to use.
     */
    public ProjectReader(Settings settings) {
    	this.settings = settings;
    }

    /**
     * Run the program on the set root folder.
     * @param ignoreComments
     */
    public RunResult run() {
        // Reset counters before running the program!
        numFiles        = 0;
        numFolders      = 0;
        totalLines      = 0;

        recursiveDirectoryCheck(settings.rootFolder);

        return new RunResult(numFiles, numFolders, totalLines);
    }

    /**
     * Recursively loop through a directory and its subdirectories, and run a line counter on each file.
     * @param dir The directory to run on.
     */
    private void recursiveDirectoryCheck(File dir) {
        // Create a an array of files with all the contents of this directory
        File[] files = dir.listFiles();

        // If no files are found in this directory, end the function
        if (files==null) {
            return;
        }

        for (File file: files) {
            // If the current file is a directory, perform this same process again
            if (file.isDirectory()) {
                recursiveDirectoryCheck(file);
                numFolders++;
            }
            // Ignore files that do not contain source code
            else if (isSourceCodeFile(file)) {
                numFiles++;

                totalLines += SourceFileReader.numberOfLines(
                		file, 
                		settings.selectedLang(), 
                		settings.ignoreComments);
            }
        }
    }

    /**
     * Determine if a given file is a programming source code file.
     * @param file
     * @return
     */
    public boolean isSourceCodeFile(File file) {
        // Split the filename to get the extension
        String[] bits = file.getName().split("\\.");

        // If the file even has an extension, check to see if it's valid
        if (bits.length>1) {
            for (String ext: settings.selectedLang().extensions) {
                if (ext.equals(bits[bits.length-1])) {
                    return true;
                }
            }
        }

        return false;
    }
}
