package ui.guide;

import java.awt.Color;
import java.awt.Font;

import userInterface.UserIntSwing;

/**
 * @author Andy Hsu Wei Qiang 
 * This class create the guide String for feedback
 * It return the string to the Feedback Label and
 * give user appropriate feedbacks.
 * 
 */
public class FeedbackGuide {
	
	private static final String feedbackEmptyString = "The input is blank! Please enter something!";
	private static final String feedbackInvalidString = "Invalid Command!";
	private static final String feedbackValidString = "Command Accepted!";
	private static final String feedbackEmptyHistory = "Nothing typed previously!";
	private static final String feedbackEmptyInput = "No more input!";
	
	public static String isEmptyString(){
		return feedbackEmptyString;
	}
	
	public static String isInvalidString(){
		return feedbackInvalidString;
	}
	
	public static String isValidString(){
		return feedbackValidString;
	}
	
	public static String isEmptyHistoryString(){
		return feedbackEmptyHistory;
	}
	
	public static String isEmptyInput(){
		return feedbackEmptyInput;
	}
	
	public static void formatFeedbackLabel(){
        UserIntSwing.lblFeedback.setFont(new Font("Arial", Font.ITALIC, 12));
        UserIntSwing.lblFeedback.setForeground(Color.red);
        UserIntSwing.lblFeedback.setOpaque(false);
	}
}
