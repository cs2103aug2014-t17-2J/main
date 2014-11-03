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
	private static final String feedbackUndoSucceed = "Undo completed!";
	private static final String feedbackRedoSucceed = "Redo completed";
	private static final String feedbackEmptyUndo = "No more input for Undo!";
	private static final String feedbackEmptyRedo = "No more input for Redo!";
	
	private static final String HTML_OPEN = "<html>";
	private static final String HTML_CLOSE = "</html>";
	private static final String TAG_WRAP_STRING = "%s%s%s";
	private static final String HTML_FONTCOLORGREY_OPEN = "<font color=#B0B0B0>";
	private static final String HTML_FONT_CLOSE = "</font>";
	
	public static String isEmptyString() {
		return feedbackEmptyString;
	}
	
	public static String isInvalidString() {
		return feedbackInvalidString;
	}
	
	public static String isValidString() {
		return feedbackValidString;
	}
	
	public static String isEmptyHistoryString() {
		return feedbackEmptyHistory;
	}
	
	public static String isEmptyInput() {
		return feedbackEmptyInput;
	}
	
	public static String undoCompleted() {
		return feedbackUndoSucceed;
	}
	
	public static String isEmptyUndoInput() {
		return feedbackEmptyUndo;
	}
	
	public static String redoCompleted() {
		return feedbackRedoSucceed;
	}
	
	public static String isEmptyRedoInput() {
		return feedbackEmptyRedo;
	}
	
	public static void formatFeedbackLabel(){
        UserIntSwing.lblFeedback.setFont(new Font("Arial", Font.ITALIC, 12));
        UserIntSwing.lblFeedback.setForeground(Color.red);
        UserIntSwing.lblFeedback.setOpaque(false);
	}
	
	public static String textfieldFeedback() {
		StringBuilder str = new StringBuilder();
		
		str.append(fontColorGrey("Enter You Command Here"));
		
		return wrapWithHtmlTag(str.toString());
	}
	
	private static String wrapWithHtmlTag(String text) {
		return String.format(TAG_WRAP_STRING, HTML_OPEN, text, HTML_CLOSE);
	}
	
	private static String fontColorGrey(String text){
		return String.format(TAG_WRAP_STRING, HTML_FONTCOLORGREY_OPEN, 
				text, HTML_FONT_CLOSE);
	}
}
