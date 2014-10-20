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
    
	private static final int Xcoordinate = 189;
	private static final int Ycoordinate = 285;
	
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
        
        JPanel jpAdd = new JPanel();
        JLabel lblAdd = new JLabel();
        lblAdd.setText("You are in area of Tab1");
        jpAdd.add(lblAdd);
        jtp.addTab("<Add>", jpAdd);
        
        JPanel jpView = new JPanel();
        JLabel lblView = new JLabel();
        lblView.setText("You are in area of Tab2");
        jpView.add(lblView);
        jtp.addTab("<View>", jpView);
        
        JPanel jpEdit = new JPanel();
        JLabel lblEdit = new JLabel();
        lblEdit.setText("You are in area of Tab3");
        jpEdit.add(lblEdit);
        jtp.addTab("<Edit>", jpEdit);
        
        JPanel jpDelete = new JPanel();
        JLabel lblDelete = new JLabel();
        lblDelete.setText("You are in area of Tab4");
        jpDelete.add(lblDelete);
        jtp.addTab("<Delete>", jpDelete);
        
        JPanel jpSearch = new JPanel();
        JLabel lblSearch = new JLabel();
        lblSearch.setText("You are in area of Tab5");
        jpSearch.add(lblSearch);
        jtp.addTab("<Search>", jpSearch);
 
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
        
        //Create the Exit Instruction Label
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