/**
 * @author Andy Hsu Wei Qiang 
 * This class creates the Help Menu when 
 * user hotkey <F1> is pressed
 * 
 */
package ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
 
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
	
//    public Container createHelpPane() {
//        //Create the content-pane-to-be.
//        JPanel contentPane = new JPanel(new BorderLayout());
//        contentPane.setOpaque(true);
// 
//        //Create a scrolled text area.
//        output = new JTextArea(5, 30);
//        output.setEditable(false);
//        scrollPane = new JScrollPane(output);
// 
//        //Add the text area to the content pane.
//        contentPane.add(scrollPane, BorderLayout.CENTER);
// 
//        return contentPane;
//    }
    
	/**
	 * This class creates the Main Tab Menu
	 */
    private static JTabbedPane createMenuTab() {
		JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP);
        
        JPanel jp1 = new JPanel();
        JLabel label1 = new JLabel();
        label1.setText("You are in area of Tab1");
        jp1.add(label1);
        jtp.addTab("Tab1", jp1);
        JPanel jp2 = new JPanel();
        JLabel label2 = new JLabel();
        label2.setText("You are in area of Tab2");
        jp2.add(label2);
        jtp.addTab("Tab2", jp2);
 
        return jtp;
    }
	/**
	 * This class creates the Exit Insruction Label
	 */
    private static JLabel createExitLabel(){
        JLabel lblExit = new JLabel("   Press <F1> again to exit the Help Menu");
        lblExit.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblExit.setBackground(new Color(127, 255, 212));
        lblExit.setBounds(0, 193, 434, 30);
        lblExit.setOpaque(true);
        
        return lblExit;
    }
	/**
	 * Initialize the contents of the frame.
	 */
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
        frame.getContentPane().add(createMenuTab(), BorderLayout.NORTH);
        
        //Display the window.
        frame.setSize(450, 261);
        frame.setVisible(true);
        
        //Set the location of the Help Menu beside the main window
        frame.setLocation(Xcoordinate, Ycoordinate);
        
        //Set the focus to the main frame
        frame.setFocusable(true);
        frame.getContentPane().setLayout(null);
        
        frame.getContentPane().add(createExitLabel());
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