package ui.logic.command;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import logic.command.UndoHandler;
import logic.exception.InvalidCommandException;
import logic.parser.ParseResult;
import ui.guide.FeedbackGuide;
import userInterface.UserIntSwing;

/**
 //@author A0112636M 
 * Handles all the Hotkey Functions
 */
public class HotkeyHandler {
	private static final String getAddCommand = Keywords.getAddTaskIdentifier();
	private static final String getViewCommand = Keywords.getViewTaskIdentifier();
	private static final String getEditCommand = Keywords.getEditTaskIdentifier();
	private static final String getDeleteCommand = Keywords.getDeleteTaskIdentifier();
	private static final String getSearchCommand = Keywords.getSearchTaskIdentifier();
	private static final InputMap im = UserIntSwing.textField.getInputMap(JComponent.WHEN_FOCUSED);
	private static final ActionMap am = UserIntSwing.textField.getActionMap();
	
	private static final String ADD_COMMAND_SUGGESTION_STRING = " meeting today pri high";
	private static final String VIEW_COMMAND_SUGGESTION_STRING = " today";
	private static final String EDIT_COMMAND_SUGGESTION_STRING = " 1 tmr";
	private static final String DELETE_COMMAND_SUGGESTION_STRING = " 1 to 3";
	private static final String SEARCH_COMMAND_SUGGESTION_STRING = " work";
	
	public static void add() {
        UserIntSwing.textField.setText(getAddCommand + ADD_COMMAND_SUGGESTION_STRING);
        UserIntSwing.textField.setSelectionStart(4);
    }
    
    public static void view() {
    	UserIntSwing.textField.setText(getViewCommand + VIEW_COMMAND_SUGGESTION_STRING);
    	UserIntSwing.textField.setSelectionStart(5);
    }
    
    public static void edit() { 	
        UserIntSwing.textField.setText(getEditCommand + EDIT_COMMAND_SUGGESTION_STRING);
        UserIntSwing.textField.setSelectionStart(5);
    }
    
    public static void delete() {
        UserIntSwing.textField.setText(getDeleteCommand + DELETE_COMMAND_SUGGESTION_STRING);
        UserIntSwing.textField.setSelectionStart(7);
    }
    
    public static void search() {
        UserIntSwing.textField.setText(getSearchCommand + SEARCH_COMMAND_SUGGESTION_STRING);
        UserIntSwing.textField.setSelectionStart(7);
    }
    
	/**
	 * This operation process the undo key function(Ctrl-z) 
	 * using InputMap and ActionMap
	 */
	public static void undo() {
		 ParseResult getUndoCommand = UserIntSwing.logicManager.processCommand("undo");
		im.put(KeyStroke.getKeyStroke(VK.undo_Zkey(), InputEvent.CTRL_MASK), 
				"listenCtrlzKey");
		am.put("listenCtrlzKey", new AbstractAction() {

			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent arg0) {
		    	if(UndoHandler.canUndo()){
		    		try {
						UserIntSwing.logicManager.executeCommand(getUndoCommand);
						FeedbackGuide.undoCompleted();
					} catch (InvalidCommandException e) {
						e.printStackTrace();
					}
		    	} else {
		    		UserIntSwing.lblFeedback.setText(
		    				FeedbackGuide.isEmptyUndoInput());
		    		FeedbackHandler.feedbackTimerReset();
		    	}
			}
		});
	}
	
	/**
	 * This operation process the redo key function(ctrl-y) 
	 * using InputMap and ActionMap
	 */
	public static void redo() {	
		ParseResult getRedoCommand = UserIntSwing.logicManager.processCommand("redo");
		im.put(KeyStroke.getKeyStroke(VK.redo_Ykey(), InputEvent.CTRL_MASK), 
				"listenCtrlyKey");
		am.put("listenCtrlyKey", new AbstractAction(){

			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent arg0) {
		    	if(UndoHandler.canRedo()){
		    		try {
						UserIntSwing.logicManager.executeCommand(getRedoCommand);
						FeedbackGuide.redoCompleted();
					} catch (InvalidCommandException e) {
						e.printStackTrace();
					}
		    	} else {
		    		UserIntSwing.lblFeedback.setText(
		    				FeedbackGuide.isEmptyRedoInput());
		    		FeedbackHandler.feedbackTimerReset();
		    	}
			}
		});
	}
	
	/**
	 * This operation process minimise to SystemTray operation (ctrl-m) 
	 * using InputMap and ActionMap
	 */
	public static void minimise() {	
		im.put(KeyStroke.getKeyStroke(VK.minimise_Mkey(), InputEvent.CTRL_MASK), 
				"listenCtrlmKey");
		am.put("listenCtrlmKey", new AbstractAction() {

			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserIntSwing.frame.setState(Frame.ICONIFIED);
			}
		});
	}
	
	/**
	 * This operation process the scrolling up of the table
	 */
	public static void scrollUpTable() {	
		im.put(KeyStroke.getKeyStroke(VK.scrollUp_UpKey(), InputEvent.SHIFT_DOWN_MASK), 
				"listenShiftUpKey");
		am.put("listenShiftUpKey", new AbstractAction() {

			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
}
