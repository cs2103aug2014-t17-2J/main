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
	
	private static final String VIEW_STRING = "You are viewing: ";
	private static final String VIEW_STRING_APPEND = "'s tasks.";
	private static final String VIEW_STRING_TODAY = "Today";
	private static final String VIEW_STRING_TOMORROW = "Tomorrow";
	private static final String VIEW_STRING_YESTERDAY = "Yesterday";
	
	private static final String HTML_OPEN = "<html>";
	private static final String HTML_CLOSE = "</html>";
	private static final String TAG_WRAP_STRING = "%s%s%s";
	private static final String HTML_FONTCOLORGREY_OPEN = "<font color=#B0B0B0>"; //grey
	private static final String HTML_FONTCOLORGREEN_OPEN = "<font color=#00CC00>"; //green
	private static final String HTML_FONTCOLORRED_OPEN = "<font color=#FF0000>"; //red
	private static final String HTML_FONT_CLOSE = "</font>";
	
	public static String isEmptyString() {
		return feedbackEmptyString;
	}
	
	public static String isInvalidString() {
		return feedbackInvalidString;
	}
	
	public static String isValidString() {
		return formatValidCommand(feedbackValidString);
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
	
	private static String formatValidCommand(String text) {
		StringBuilder str = new StringBuilder();
		
		str.append(String.format(TAG_WRAP_STRING, HTML_FONTCOLORGREEN_OPEN, 
				text, HTML_FONT_CLOSE));
		
		return wrapWithHtmlTag(str.toString());
	}
	
	public static String formatViewTodayTask() {
		StringBuilder str = new StringBuilder();
		
		str.append(VIEW_STRING);
		str.append(String.format(TAG_WRAP_STRING, HTML_FONTCOLORRED_OPEN, 
				VIEW_STRING_TODAY, HTML_FONT_CLOSE));
		str.append(VIEW_STRING_APPEND);
		
		return wrapWithHtmlTag(str.toString());
	}
	
	public static String formatViewTomorrowTask() {
		StringBuilder str = new StringBuilder();
		
		str.append(VIEW_STRING);
		str.append(String.format(TAG_WRAP_STRING, HTML_FONTCOLORRED_OPEN, 
				VIEW_STRING_TOMORROW, HTML_FONT_CLOSE));
		str.append(VIEW_STRING_APPEND);
		
		return wrapWithHtmlTag(str.toString());
	}
	
	public static String formatViewYesterdayTask() {
		StringBuilder str = new StringBuilder();
		
		str.append(VIEW_STRING);
		str.append(String.format(TAG_WRAP_STRING, HTML_FONTCOLORRED_OPEN, 
				VIEW_STRING_YESTERDAY, HTML_FONT_CLOSE));
		str.append(VIEW_STRING_APPEND);
		
		return wrapWithHtmlTag(str.toString());
	}
	
	public static String formatViewDateTask(String text) {
		StringBuilder str = new StringBuilder();
		
		str.append(VIEW_STRING);
		str.append(String.format(TAG_WRAP_STRING, HTML_FONTCOLORRED_OPEN, 
				text, HTML_FONT_CLOSE));
		str.append(VIEW_STRING_APPEND);
		
		return wrapWithHtmlTag(str.toString());
	}
	
	public static String formatViewAllTask() {
		StringBuilder str = new StringBuilder();
		
		str.append(VIEW_STRING);
		str.append(String.format(TAG_WRAP_STRING, HTML_FONTCOLORRED_OPEN, 
				"All", HTML_FONT_CLOSE));
		str.append(" tasks.");
		
		return wrapWithHtmlTag(str.toString());
	}
	
	private static String wrapWithHtmlTag(String text) {
		return String.format(TAG_WRAP_STRING, HTML_OPEN, text, HTML_CLOSE);
	}
	
	private static String fontColorGrey(String text) {
		return String.format(TAG_WRAP_STRING, HTML_FONTCOLORGREY_OPEN, 
				text, HTML_FONT_CLOSE);
	}
}
