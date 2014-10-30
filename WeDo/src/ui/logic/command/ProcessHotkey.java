package ui.logic.command;

import logic.exception.InvalidCommandException;
import logic.parser.ParseResult;
import userInterface.UserIntSwing;

/**
 * @author Andy Hsu Wei Qiang 
 * 
 */
public class ProcessHotkey {
	
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
    	UserIntSwing.logicManager.executeCommand(getUndoCommand);
    }
    
    public static void redo() throws InvalidCommandException {
    	UserIntSwing.logicManager.executeCommand(getRedoCommand);
    }
}
