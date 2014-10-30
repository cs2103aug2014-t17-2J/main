package ui.logic.command;

import java.util.Timer;
import java.util.TimerTask;

import ui.guide.FeedbackGuide;
import userInterface.UserIntSwing;

/**
 * @author Andy Hsu Wei Qiang 
 * This class handle all the Feedback to the user
 */
public class FeedbackHandler {
	
    public static void successfulOperation() {
		UserIntSwing.lblFeedback.setText(FeedbackGuide.isValidString());
		feedbackTimerReset();
    }
    
    public static void NotSuccessfulOperation() {
    	UserIntSwing.lblFeedback.setText(FeedbackGuide.isInvalidString());
		feedbackTimerReset();
    }
    
    /**
     * 
     */
    public static void emptyStringOperation() {
    	UserIntSwing.lblFeedback.setText(FeedbackGuide.isEmptyString());
    	feedbackTimerReset();
    }
    
	public static void emptyHistoryStringOperation(){
		UserIntSwing.lblFeedback.setText(FeedbackGuide.isEmptyHistoryString());
		feedbackTimerReset();
	}
	
	public static void emptyInputStringOperation(){
		UserIntSwing.lblFeedback.setText(FeedbackGuide.isEmptyInput());
		feedbackTimerReset();
	}
	
    /**
     * @param getText get the user input string
     * @return boolean true if user input double space
     * false otherwise
     */
    public static boolean isDoubleSpace(String getText) {

        getText = getText.trim().replaceAll("\\s+", " ");
        if (getText.isEmpty() || getText.matches(" ")) {
            return true;
        }
		return false;
    }
    
	/**
	* This operation process the timer to clear the Warning Label. It is set at
	* 1000 milli-seconds.
	*/
	public static void feedbackTimerReset() {
	
	     Timer timer = new Timer();
	     timer.schedule(new TimerTask() {
	         @Override
	         public void run() {
	             UserIntSwing.lblFeedback.setText("");
	         }
	     }, 1000);
	 }
}
