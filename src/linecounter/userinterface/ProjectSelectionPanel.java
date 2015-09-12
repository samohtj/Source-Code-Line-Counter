package linecounter.userinterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import linecounter.NoFileChosenException;
import linecounter.Settings;

/**
 * A JLabel object that displays the project selection controls.
 * @author Jonathan Thomas
 *
 */
//Suppress serial warnings. We aren't ever going to serialize this object.
@SuppressWarnings("serial")
public class ProjectSelectionPanel extends SourceCounterPanel{
	
	private JLabel rootFolderLabel		= new JLabel("Root folder: None selected");
    private JButton chooseRootButton	= new JButton("Choose root folder");
    
    public ProjectSelectionPanel(Settings set) {
    	this.settings = set;
    	
    	rootFolderLabel.setText("Root folder: "+settings.rootFolder.getName());
    	
        chooseRootButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
