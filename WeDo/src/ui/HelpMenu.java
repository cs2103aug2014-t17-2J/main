package ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

import userInterface.UserIntSwing;

import javax.swing.ImageIcon;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
 
/*
 * This class is the help menu the shows
 * different command to the user
 */
public class HelpMenu {
    public static JFrame frame;
	JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
    
	private static final int Xcoordinate = 310;
	private static final int Ycoordinate = 225;
 
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;
        //Create the menu bar.
        menuBar = new JMenuBar();
 
        //Build the first menu.
        menu = new JMenu("<Add Command>");
        menu.setIcon(null);
        menuBar.add(menu);
 
        //Build second menu in the menu bar.
        menu = new JMenu("<View Command>");
        menuBar.add(menu);
        
        //Build second menu in the menu bar.
        menu = new JMenu("<Edit Command>");
        menuBar.add(menu);
        
        //Build second menu in the menu bar.
        menu = new JMenu("<Delete Command>");
        menuBar.add(menu);
 
        return menuBar;
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Help Menu");
        frame.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent arg0) {
        		if(arg0.getKeyCode() == KeyEvent.VK_F1){
        			frame.dispose();
        		}
        	}
        });
   		
        //Create and set up the content pane.
        HelpMenu menu = new HelpMenu();
        frame.setJMenuBar(menu.createMenuBar());
        
        //Display the window.
        frame.setSize(450, 260);
        frame.setVisible(true);
        
        //Set the location of the Help Menu
        frame.setLocation(Xcoordinate, Ycoordinate);
        
        //Set the focus to the main frame
        frame.setFocusable(true);
        
        //Create a Help content container on the frame
        frame.setContentPane(menu.createHelpPane());
        
        JLabel lblEnter = new JLabel("Press <F1> again to exit the Help Menu");
        lblEnter.setBackground(new Color(0, 255, 0));
        frame.getContentPane().add(lblEnter, BorderLayout.SOUTH);
    }
    
    public Container createHelpPane() {
        //Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);
 
        //Create a scrolled text area.
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);
 
        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);
 
        return contentPane;
    }
 
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