package linecounter.userinterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import linecounter.Settings;

/**
 * A JLabel object that displays the run controls of the program.
 * @author Jonathan Thomas
 *
 */
//Suppress serial warnings. We aren't ever going to serialize this object.
@SuppressWarnings("serial")
public class RunProgramPanel extends SourceCounterPanel {
	
	private JButton runButton;
    private JCheckBox ignoreCommentsCheckbox;
    
    Settings settings;
	
	public RunProgramPanel(Settings set) {
		
		this.settings = set;
		
		runButton = = new JButton("Run counter");
		ignoreCommentsCheckbox = new JCheckBox("Include comments and whitespace");
		
		ignoreCommentsCheckbox.setSelected(settings.ignoreComments);
		
        ignoreCommentsCheckbox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		settings.ignoreComments = ignoreCommentsCheckbox.isSelected();
        	}
        });
		
        runButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                // Run the program, and update the results labels appropriately
                projectReader.run();
                
                numLinesLabel.setText("Total lines: "+projectReader.totalLines());
                numFilesLabel.setText("Total files searched: "+projectReader.numFiles());
                numFoldersLabel.setText("Total folders searched: "+projectReader.numFolders());
            }
        });
        
        setLayout(new GridLayout(2, 1));
        add(runButton);
        add(ignoreCommentsCheckbox);
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		
	}

}
