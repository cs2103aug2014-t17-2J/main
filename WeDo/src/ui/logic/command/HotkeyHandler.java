package ui.logic.command;

import logic.command.UndoHandler;
import logic.exception.InvalidCommandException;
import logic.parser.ParseResult;
import ui.guide.FeedbackGuide;
import userInterface.UserIntSwing;

/**
 * @author Andy Hsu Wei Qiang - Handles all the Hotkey Functions
 * 
 */
public class HotkeyHandler {
	
	private static final String getAddCommand = Keywords.getAddTaskIdentifier();
	private static final String getViewCommand = Keywords.getViewTaskIdentifier();
	private static final String getEditCommand = Keywords.getEditTaskIdentifier();
	private static final String getDeleteCommand = Keywords.getDeleteTaskIdentifier();
	private static final String getSearchCommand = Keywords.getSearchTaskIdentifier();
	private static final ParseResult getUndoCommand = UserIntSwing.logicManager.processCommand("undo");
	private static final ParseResult getRedoCommand = UserIntSwing.logicManager.processCommand("redo");
	
    public static void add() {
        UserIntSwing.textField.setText(getAddCommand);
    }
    
    public static void view() {
    	UserIntSwing.textField.setText(getViewCommand);
    }
    
    public static void edit() { 	
        UserIntSwing.textField.setText(getEditCommand);
    }
    
    public static void delete() {
        UserIntSwing.textField.setText(getDeleteCommand);
    }
    
    public static void search() {
        UserIntSwing.textField.setText(getSearchCommand);
    }
    
    public static void undo() throws InvalidCommandException {
    	if(UndoHandler.canUndo()){
    		UserIntSwing.logicManager.executeCommand(getUndoCommand);
    	} else {
    		UserIntSwing.lblFeedback.setText(
    				FeedbackGuide.isEmptyUndoInput());
    		FeedbackHandler.feedbackTimerReset();
    	}
    }
    
    public static void redo() throws InvalidCommandException {
    	if(UndoHandler.canRedo()){
    		UserIntSwing.logicManager.executeCommand(getRedoCommand);
    	} else {
    		UserIntSwing.lblFeedback.setText(
    				FeedbackGuide.isEmptyRedoInput());
    		FeedbackHandler.feedbackTimerReset();
    	}
    }
}
