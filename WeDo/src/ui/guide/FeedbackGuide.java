package ui.guide;

import java.awt.Color;
import java.awt.Font;

import userInterface.UserIntSwing;

public class FeedbackGuide {
	
	private static final String feedbackEmptyString = "The input is blank! Please enter something!";
	private static final String feedbackInvalidString = "Invald Command!";
	private static final String feedbackValidString = "Command Accepted!";
	
	public static String isEmptyString(){
		return feedbackEmptyString;
	}
	
	public static String isInvalidString(){
		return feedbackInvalidString;
	}
	
	public static String isValidString(){
		return feedbackValidString;
	}
	
	public static void formatFeedbackLabel(){
        UserIntSwing.lblFeedback.setFont(new Font("Arial", Font.ITALIC, 12));
        UserIntSwing.lblFeedback.setForeground(Color.red);
        UserIntSwing.lblFeedback.setOpaque(false);
	}
}
