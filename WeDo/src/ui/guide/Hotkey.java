package ui.guide;

import ui.logic.command.Keywords;
import userInterface.UserIntSwing;

/**
 * @author Andy Hsu Wei Qiang 
 * 
 */
public class Hotkey {
	
	private static String getCommand;
	
    public static void Add(){
    	getCommand = Keywords.getAddTaskIdentifier();
        UserIntSwing.textField.setText(getCommand);
    }
    
    public static void View(){
    	getCommand = Keywords.getViewTaskIdentifier();
        UserIntSwing.textField.setText(getCommand);
    }
    
    public static void Edit(){
    	getCommand = Keywords.getEditTaskIdentifier();
        UserIntSwing.textField.setText(getCommand);
    }
    
    public static void Delete(){
        getCommand = Keywords.getDeleteTaskIdentifier();
        UserIntSwing.textField.setText(getCommand);
    }
    
    public static void Search(){
    	getCommand = Keywords.getSearchTaskIdentifier();
        UserIntSwing.textField.setText(getCommand);
    }
}
