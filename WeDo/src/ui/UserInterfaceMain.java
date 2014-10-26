package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import logic.parser.DynamicParseResult;
import logic.parser.ParserFlags;
import logic.utility.Task;
import ui.guide.FeedbackGuide;
import ui.logic.command.Action;
import ui.logic.command.Keywords;
import userInterface.UserIntSwing;

/**
 * @author Andy Hsu Wei Qiang 
 * This class handles all the GUI logic
 * which the user execute.
 * 
 */
public class UserInterfaceMain {
	//private static final String EXIT_PROGRAM = "exit";
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final int taskbarHeight = 47;
	private static final String WHITESPACE_PATTERN = "\\s+";
	
	public static void formatLabels(){
		
		UserIntSwing.lblCommand.setFont(new Font("Tahoma", Font.BOLD, 12));
		UserIntSwing.lblCommandProcess.setFont(new Font("Tahoma", Font.ITALIC, 11));
		UserIntSwing.lblCommandProcess.setForeground(new Color(255, 0, 0));
		UserIntSwing.lblCommandProcess.setBackground(new Color(255, 204, 255));
		UserIntSwing.lblCommandProcess.setOpaque(true);
		
		UserIntSwing.lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		UserIntSwing.lblDateProcess.setFont(new Font("Tahoma", Font.ITALIC, 11));
		UserIntSwing.lblDateProcess.setForeground(new Color(0, 128, 0));
		UserIntSwing.lblDateProcess.setBackground(new Color(255, 204, 255));
		UserIntSwing.lblDateProcess.setOpaque(true);
	
		UserIntSwing.lblPriority.setFont(new Font("Tahoma", Font.BOLD, 12));
		UserIntSwing.lblPriorityProcess.setFont(new Font("Tahoma", Font.ITALIC, 11));
		UserIntSwing.lblPriorityProcess.setBackground(new Color(255, 204, 255));
		UserIntSwing.lblPriorityProcess.setOpaque(true);
		
		UserIntSwing.lblDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		UserIntSwing.lblDescriptionProcess.setFont(new Font("Tahoma", Font.ITALIC, 11));
		UserIntSwing.lblDescriptionProcess.setBackground(new Color(255, 204, 255));
		UserIntSwing.lblDescriptionProcess.setOpaque(true);
	}
	
	/**
	 *This operation puts the focus on the textField 
	 *for the user to type immediately when the program runs 
	 */
	public static void addFrameWindowFocusListener() {
		UserIntSwing.frame.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {

				UserIntSwing.textField.requestFocusInWindow();
			}
			public void windowLostFocus(WindowEvent arg0) {}
		});
	}
	
	/**
	 *This operation sets the date for today and display
	 *on the top of the application
	 */
	public static String setTodayDate(){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String date = sdf.format(new Date());
		String dateDisplay = "You are viewing: " + date;
		
		return dateDisplay;
	}
	
	/**
	 *This operation sets the program at the 
	 *bottom right hand corner of screen
	 */
	public static void setupFrameLocation() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
		int Xcoordinate = (int) rect.getMaxX() - UserIntSwing.frame.getWidth();
		int Ycoordinate = (int) rect.getMaxY() - UserIntSwing.frame.getHeight() - taskbarHeight;
		UserIntSwing.frame.setLocation(Xcoordinate, Ycoordinate);
	}
	
	/**
	 *This operation process the hotkeys shortcut function. 
	 */
	public static void processHotKeys(KeyEvent arg1){
		String getCommand;
		if(arg1.getKeyCode() == KeyEvent.VK_F1){
			HelpMenu.main(null);
		}
		if (UserIntSwing.textField.getText().length() == 0){
			if(arg1.getKeyCode() == KeyEvent.VK_F2){
				getCommand = Keywords.getAddTaskIdentifier();
				UserIntSwing.textField.setText(getCommand);
			}
			else if(arg1.getKeyCode() == KeyEvent.VK_F3){
				getCommand = Keywords.getViewTaskIdentifier();
				UserIntSwing.textField.setText(getCommand);
			}
			else if(arg1.getKeyCode() == KeyEvent.VK_F4){
				getCommand = Keywords.getEditTaskIdentifier();
				UserIntSwing.textField.setText(getCommand);
			}
			else if(arg1.getKeyCode() == KeyEvent.VK_F5){
				getCommand = Keywords.getDeleteTaskIdentifier();
				UserIntSwing.textField.setText(getCommand);
			}
		}
	}
	
	/**
	 *This operation process the Feedback Label 
	 */
	public static String processFeedbackLabel(String text){
		
		text = text.trim().replaceAll("\\s+", "");
		if(text.isEmpty() || text.matches(" ")){
			return FeedbackGuide.isEmptyString();
		}
		
		text = text.toLowerCase();
		String[] tokens = text.split(WHITESPACE_PATTERN);
		Action action = Keywords.resolveActionIdentifier(tokens[0]);

		switch (action) {
		case ADD: case VIEW: case EDIT: case DELETE: case SEARCH:
			return FeedbackGuide.isValidString();
		default:
			return FeedbackGuide.isInvalidString();
		}
	}
	
	/**
	 *This operation process the timer to clear the Warning 
	 *Label. It is set at 1000 milli-seconds. 
	 */
	public static void feedbackTimerReset(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  UserIntSwing.lblFeedback.setText("");
			  }
			}, 1000);
	}
	
	/**
	 *This operation process the labels that the user input from
	 *the textfield and show what will be parsed
	 */
	public static void processUserParse(KeyEvent arg1)
    {
         DynamicParseResult parseResult = UserIntSwing.logicManager.dynamicParse(UserIntSwing.
        		 textField.getText());
        
         Task task = parseResult.getTask();
         
         // clear all label 1st..
         UserIntSwing.lblCommandProcess.setText(null);
         UserIntSwing.lblDateProcess.setText(null);
         UserIntSwing.lblDescriptionProcess.setText(null);
         UserIntSwing.lblPriorityProcess.setText(null);
         
        for(ParserFlags parseFlag : parseResult.getParseFlags())
        {
            switch (parseFlag)
            {
            case COMMAND_FLAG: 
            	UserIntSwing.lblCommandProcess.setText(parseResult.getCommandWordUsed());
                break;
            case DATE_FLAG: 
            	UserIntSwing.lblDateProcess.setText(task.getDateTimeString());
                break;
            case DESCRIPTION_FLAG: 
            	UserIntSwing.lblDescriptionProcess.setText(task.getDescription());
                break;
            case PRIORITY_FLAG: 
            	UserIntSwing.lblPriorityProcess.setText(task.getPriority().toString());
            	processLblPriorityProcess();
            	break;
            default:
                break;
            }
        }
    }
	
	private static void processLblPriorityProcess(){
		if(UserIntSwing.lblPriorityProcess.getText().matches("High")){
			UserIntSwing.lblPriorityProcess.setForeground(Color.red);
		}
		else if(UserIntSwing.lblPriorityProcess.getText().matches("Low")){
			UserIntSwing.lblPriorityProcess.setForeground(Color.green);
		}
		else{
			UserIntSwing.lblPriorityProcess.setForeground(Color.blue);
		}
	}
}
