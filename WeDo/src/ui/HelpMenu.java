package ui;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ui.logic.command.VK;
import userInterface.UserIntSwing;

import java.awt.BorderLayout;
import java.awt.Toolkit;

/**
 * @author A0112636M
 * This class creates the Help Menu when 
 * user hotkey <F1> is pressed on the main form
 * 
 */
public class HelpMenu {
    public static JFrame frame;
	JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
    
	private static final int Xcoordinate = 5;
	private static final int Ycoordinate = 5;

	/**
	 * Initialize the contents of the frame.
	 */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("-Help Menu-");
        frame.setIconImage(
        		Toolkit.getDefaultToolkit().getImage(UserIntSwing.class.getResource("/ui/icon/HelpMenuIcon.png")));
        frame.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent arg0) {
        		if(arg0.getKeyCode() == VK.help()){
        			frame.dispose();
        		}
        	}
        });
        
        //Disable Resize
        frame.setResizable(false);
        
        //call the method to change the appearance of the frame
        UserInterfaceHelpMenu.changeAppearance();
        	
        //Create and set up the content pane.
        frame.getContentPane().add(UserInterfaceHelpMenu.createMenuTab(), 
        		BorderLayout.NORTH);
        
        //Display the window.
        frame.setSize(350, 378);
        frame.setVisible(true);
        
        //Set the location of the Help Menu on the top right corner
        frame.setLocation(Xcoordinate, Ycoordinate);
        
        //Set the focus to the main frame
        frame.setFocusable(true);
        frame.getContentPane().setLayout(null);
        
        frame.getContentPane().add(UserInterfaceHelpMenu.createExitLabel());
    }
	/**
	 * Launch the application.
	 */
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}