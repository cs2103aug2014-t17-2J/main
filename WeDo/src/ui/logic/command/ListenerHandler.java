package ui.logic.command;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import logic.exception.InvalidCommandException;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.BalloonTip.AttachLocation;
import net.java.balloontip.BalloonTip.Orientation;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.EdgedBalloonStyle;
import ui.HelpMenu;
import ui.WeDoSystemTray;
import ui.UserInterfaceMain;
import userInterface.UserIntSwing;

/**
 // @author A0112636M 
 *This class process all the Listeners
 */
public class ListenerHandler {
	private static final BalloonTipStyle edgedLook = new EdgedBalloonStyle(Color.WHITE, Color.BLUE);
	private static Point mouseDownCompCoords = null;
	
	/**
	 * Buttons Listener - Help, Add, View, Edit, Delete, Search, 
	 * Enter, Close, Minimize.
	 * Process individual functions of the buttons when Hotkey
	 * is pressed
	 * Also process the balloonTip and Mouse Listener
	 */
	public static void addFrameLocationListener() {
		/* calculate the axis of the frame when mouse is pressed
		 * on the frame*/
		UserIntSwing.frame.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {
                mouseDownCompCoords = null;
            }
            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords = e.getPoint();
            }
            public void mouseExited(MouseEvent e) {
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseClicked(MouseEvent e) {
            }
        });
		/* Set the frame to the location when the Point
		 * of the frame is calculated*/
		UserIntSwing.frame.addMouseMotionListener(new MouseMotionListener(){
			public void mouseMoved(MouseEvent e) {
            }
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                UserIntSwing.frame.setLocation(currCoords.x - mouseDownCompCoords.x, 
                		currCoords.y - mouseDownCompCoords.y);
            }
	    });
	}
	
	public static void addBtnHelpListener() {
		BalloonTip helpBalloonTip = new BalloonTip(UserIntSwing.btnHelp, new JLabel(
				"Press F1 for Help"), edgedLook, Orientation.RIGHT_BELOW,
				AttachLocation.ALIGNED, 40, 20, false);
		helpBalloonTip.setVisible(false);
		UserIntSwing.btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HelpMenu.main(null);
			}
		});
		
		UserIntSwing.btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				helpBalloonTip.setVisible(true);
				UserIntSwing.btnHelp.setBorderPainted(true);
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				helpBalloonTip.setVisible(false);
				UserIntSwing.btnHelp.setBorderPainted(false);
			}
		});
	}

	public static void addBtnAddListener() {
		BalloonTip addBalloonTip = new BalloonTip(UserIntSwing.btnAdd, new JLabel(
				"Press F2 to Add"), edgedLook, Orientation.RIGHT_BELOW,
				AttachLocation.ALIGNED, 40, 20, false);
		addBalloonTip.setVisible(false);
		UserIntSwing.btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HotkeyHandler.add();
				focusTextfield();
			}
		});

		UserIntSwing.btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				addBalloonTip.setVisible(true);
				UserIntSwing.btnAdd.setBorderPainted(true);
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				addBalloonTip.setVisible(false);
				UserIntSwing.btnAdd.setBorderPainted(false);
			}
		});
	}

	public static void addBtnViewListener() {
		BalloonTip viewBalloonTip = new BalloonTip(UserIntSwing.btnView, new JLabel(
				"Press F3 to View"), edgedLook, Orientation.RIGHT_BELOW,
				AttachLocation.ALIGNED, 40, 20, false);
		viewBalloonTip.setVisible(false);

		UserIntSwing.btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotkeyHandler.view();
				focusTextfield();
			}
		});
		UserIntSwing.btnView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				viewBalloonTip.setVisible(true);
				UserIntSwing.btnView.setBorderPainted(true);
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				viewBalloonTip.setVisible(false);
				UserIntSwing.btnView.setBorderPainted(false);
			}
		});
	}

	public static void addBtnEditListener() {
		BalloonTip editBalloonTip = new BalloonTip(UserIntSwing.btnEdit, new JLabel(
				"Press F4 to Edit"), edgedLook, Orientation.RIGHT_BELOW,
				AttachLocation.ALIGNED, 40, 20, false);
		editBalloonTip.setVisible(false);

		UserIntSwing.btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotkeyHandler.edit();
				focusTextfield();
			}
		});
		UserIntSwing.btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				editBalloonTip.setVisible(true);
				UserIntSwing.btnEdit.setBorderPainted(true);
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				editBalloonTip.setVisible(false);
				UserIntSwing.btnEdit.setBorderPainted(false);
			}
		});
	}

	public static void addBtnDeleteListener() {
		BalloonTip delBalloonTip = new BalloonTip(UserIntSwing.btnDelete, new JLabel(
				"Press F5 to delete"), edgedLook, Orientation.RIGHT_BELOW,
				AttachLocation.ALIGNED, 40, 20, false);
		delBalloonTip.setVisible(false);

		UserIntSwing.btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotkeyHandler.delete();
				focusTextfield();
			}
		});
		UserIntSwing.btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				delBalloonTip.setVisible(true);
				UserIntSwing.btnDelete.setBorderPainted(true);
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				delBalloonTip.setVisible(false);
				UserIntSwing.btnDelete.setBorderPainted(false);
			}
		});
	}

	public static void addBtnSearchListener() {
		BalloonTip searchBalloonTip = new BalloonTip(UserIntSwing.btnSearch, new JLabel(
				"Press F6 to search"), edgedLook, Orientation.RIGHT_BELOW,
				AttachLocation.ALIGNED, 40, 20, false);
		searchBalloonTip.setVisible(false);

		UserIntSwing.btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotkeyHandler.search();
				focusTextfield();
			}
		});
		UserIntSwing.btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				searchBalloonTip.setVisible(true);
				UserIntSwing.btnSearch.setBorderPainted(true);
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				searchBalloonTip.setVisible(false);
				UserIntSwing.btnSearch.setBorderPainted(false);
			}
		});
	}
	
	public static void addBtnEnterListener() {
		UserIntSwing.btnEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				UserIntSwing.btnEnter.setBorderPainted(true);
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				UserIntSwing.btnEnter.setBorderPainted(true);
			}
		});
	}
	
	public static void addBtnCloseListener() {
		BalloonTip closeBalloonTip = new BalloonTip(UserIntSwing.btnClose, new JLabel(
				"Press to quit application"), edgedLook, Orientation.RIGHT_BELOW,
				AttachLocation.ALIGNED, 40, 20, false);
		closeBalloonTip.setVisible(false);
		
		UserIntSwing.btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserIntSwing.frame.dispose();
			}
		});
		
		UserIntSwing.btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				closeBalloonTip.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				closeBalloonTip.setVisible(false);
			}
		});
	}
	
	public static void addBtnMinimizeListener() {
		BalloonTip minimizeBalloonTip = new BalloonTip(UserIntSwing.btnMinimize, new JLabel(
				"Minimize to Tray"), edgedLook, Orientation.RIGHT_BELOW,
				AttachLocation.ALIGNED, 40, 20, false);
		minimizeBalloonTip.setVisible(false);
		
		UserIntSwing.btnMinimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				minimizeBalloonTip.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				minimizeBalloonTip.setVisible(false);
			}
		});
		UserIntSwing.btnMinimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserIntSwing.frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
	}
	
	public static void focusTextfield() {
		UserIntSwing.textField.requestFocusInWindow();
	}

	/**
	 * Window State Listener
	 * This operation process the SystemTray when minimise
	 * operation is executed
	 */
	public static void addSystemTrayWindowStateListener() {
		UserIntSwing.frame.addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent arg) {
				WeDoSystemTray.Minimise(arg);
			}
		});
	}

	/**
	 * Window Focus Listener
	 * This operation puts the focus on the textField for the user to type
	 * immediately when the program runs
	 */
	public static void addFrameWindowFocusListener() {
		UserIntSwing.frame.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				UserIntSwing.textField.requestFocusInWindow();
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
	}

	/**
	 * Textfield Action Listener - Process all the text that has
	 * been parsed in the Textfield when user start typing
	 */
	public static void addTextFieldActionListener() {
		UserIntSwing.textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserInterfaceMain.processTextfieldString();
			}
		});
	}

	/**
	 * Button Enter Action Listener - Process all the text has
	 * been pased in the Textfield when Enter is clicked
	 */
	public static void addBtnEnterActionListener() {
		UserIntSwing.btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserInterfaceMain.processTextfieldString();
			}
		});
	}

	/**
	 * Textfield KeyListener
	 * 1. Set the Command guide Label to the indiviual command guide that the user input
	 * 2. Process all the HotKeys Functions
	 * 3. Process the User Typed History
	 * 4. Enter KeyListener - Process all the feedback labels when the user type 
	 * an incorrect input
	 */
	public static void addTextfieldKeyListener() {
		UserIntSwing.textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg1) {
				if(arg1.getKeyCode() == VK.enter()) {
					UserInterfaceMain.processEnterkey(arg1);
				}
				try {
					UserInterfaceMain.processHotKeys(arg1);
				} catch (InvalidCommandException e) {
					e.printStackTrace();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg1) {
				UserInterfaceMain.processTextfieldKeyReleased(arg1);
			}
		});
	}
}
