package ui.guide;

import java.text.SimpleDateFormat;
import java.util.Date;

import ui.logic.command.Action;
import ui.logic.command.Keywords;

/**
 * @author Andy Hsu Wei Qiang 
 * This class create the guide String for all commands
 * All method using the commands will be called here
 * 
 */
/**
 * @author Hsuper
 *
 */
public class CommandGuide {
	private static final String GENERAL_GUIDE = buildGeneralGuideString();
	private static final String ADD_GUIDE = buildAddGuideString();
	private static final String VIEW_GUIDE = buildViewGuideString();
	private static final String EDIT_GUIDE = buildEditGuideString();
	private static final String DELETE_GUIDE = buildDeleteGuideString();
	private static final String SEARCH_GUIDE = buildSearchGuideString();
	private static final String UNDO_GUIDE = buildUndoGuideString();
	private static final String REDO_GUIDE = buildRedoGuideString();
	
	private static final String HTML_OPEN = "<html>";
	private static final String HTML_CLOSE = "</html>";
	private static final String HTML_BREAK = "<br>";
	private static final String HTML_UNDERLINE_OPEN = "<u>";
	private static final String HTML_UNDERLINE_CLOSE = "</u>";
	private static final String TAG_WRAP_STRING = "%s%s%s";
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final String WHITESPACE_PATTERN = "\\s+";
	private static final String IDENTIFIER_PLACEHOLDER = "%1$s";
	
	private static final String COMMAND_TODAY = "today";
	private static final String COMMAND_YESTERDAY = "yesterday";
	private static final String COMMAND_TOMORROW = "tomorrow";
	private static final String LABEL_SAMPLE_DATE = "14/10/2014";
	
	private static final int MIN_TOKENS_LENGTH = 1;
	private static final int ACTION_IDENTIFIER_INDEX = 0;
	/**
	 * To get the relevant command guide according to command
	 * @param commandString user input in String format
	 * @return message the relevant command guide which the
	 * user input command
	 */
	public static String getGuideMessage(String commandString) {

		/* Check that there is at least 1 token */
		String[] tokens = commandString.split(WHITESPACE_PATTERN);
		boolean isValidLength = (tokens.length >= MIN_TOKENS_LENGTH);

		if (!isValidLength) {
			return GENERAL_GUIDE;
		}

		String identifier = tokens[ACTION_IDENTIFIER_INDEX];
		String message = buildGuideMessage(identifier);

		return message;
	}

	/**
	 * Format the command guide to String format
	 * @param identifier the command the user input
	 * @return the command guide in String format
	 */
	public static String buildGuideMessage(String identifier) {

		identifier = identifier.toLowerCase();
		Action action = Keywords.resolveActionIdentifier(identifier);

		switch (action) {
		case ADD:
			return String.format(ADD_GUIDE, identifier);
		case VIEW:
			return String.format(VIEW_GUIDE, identifier);
		case EDIT:
			return String.format(EDIT_GUIDE, identifier);
		case DELETE:
			return String.format(DELETE_GUIDE, identifier);
		case SEARCH:
			return String.format(SEARCH_GUIDE, identifier);
		case UNDO:
			return String.format(UNDO_GUIDE, identifier);
		case REDO:
			return String.format(REDO_GUIDE, identifier);
		default:
			return GENERAL_GUIDE;
		}
	}

	/**
	 * @return GENERAL Command Guide in String(HTML format)
	 */
	public static String buildGeneralGuideString() {

		StringBuilder str = new StringBuilder();

		str.append("Type any of the following to begin:");
		str.append(HTML_BREAK);
		str.append(Keywords.getAddTaskIdentifier() + " | ");
		str.append(Keywords.getViewTaskIdentifier() + " | ");
		str.append(Keywords.getEditTaskIdentifier() + " | ");
		str.append(Keywords.getDeleteTaskIdentifier() + " | ");
		str.append(Keywords.getSearchTaskIdentifier() + " | ");
		str.append(Keywords.getUndoActionIdentifier() + " | ");
		str.append(Keywords.getRedoActionIdentifier());

		return wrapWithHtmlTag(str.toString());
	}
	
	/**
	 * @return ADD Command Guide in String(HTML format)
	 */
	public static String buildAddGuideString() {

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String date = sdf.format(new Date());
		StringBuilder str = new StringBuilder();
		
		str.append("To schedule a task, type:");
		str.append(HTML_BREAK);

		str.append("- Normal Task: ");
		str.append(underline(IDENTIFIER_PLACEHOLDER) + " meet Dr Damith ");
		str.append(underline(Keywords.getDateIdentifier()) + " " + date + " ");
		str.append(underline(Keywords.getTimeIdentifier()) + " 12pm - 2pm");
		str.append(HTML_BREAK);
		
		str.append("- Deadline Task: ");
		str.append(underline(IDENTIFIER_PLACEHOLDER) + " submit assignment ");
		str.append(underline(Keywords.getDueDateIdentifier()) + " " + date
				+ " ");
		str.append(underline(Keywords.getTimeIdentifier()) + " 10pm");
		str.append(HTML_BREAK);

		str.append("- Floating Task: ");
		str.append(underline(IDENTIFIER_PLACEHOLDER) + " learn programming");

		return wrapWithHtmlTag(str.toString());
	}
	
	/**
	 * @return VIEW Command Guide in String(HTML format)
	 */
	public static String buildViewGuideString(){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String date = sdf.format(new Date());
		StringBuilder str = new StringBuilder();

		str.append("-To view tasks for today, type:" + HTML_BREAK);
		str.append(underline(IDENTIFIER_PLACEHOLDER) + " ");
		str.append(underline(COMMAND_TODAY));
		str.append(HTML_BREAK);

		str.append("-To view tasks for a certain day, type:" + HTML_BREAK);
		str.append(underline(IDENTIFIER_PLACEHOLDER) + " " + date + " | ");
		str.append(underline(COMMAND_YESTERDAY) + " | ");
		str.append(underline(COMMAND_TOMORROW));

		return wrapWithHtmlTag(str.toString());
	}
	
	/**
	 * @return EDIT Command Guide in String(HTML format)
	 */
	public static String buildEditGuideString(){
		StringBuilder str = new StringBuilder();

		str.append("To edit a task, ");
		str.append("select the task number and type:" + HTML_BREAK);
		str.append(underline(IDENTIFIER_PLACEHOLDER) + " 1 ");
		str.append(LABEL_SAMPLE_DATE);

		return wrapWithHtmlTag(str.toString());
	}
	
	/**
	 * @return DELETE Command Guide in String(HTML format)
	 */
	public static String buildDeleteGuideString(){
		StringBuilder str = new StringBuilder();

		str.append("To delete a task, ");
		str.append("select the task number and type:" + HTML_BREAK);
		str.append(underline(IDENTIFIER_PLACEHOLDER) + " 1");

		return wrapWithHtmlTag(str.toString());
	}
	
	
	/**
	 * @return SEARCH Command Guide in String(HTML format)
	 */
	public static String buildSearchGuideString(){
		StringBuilder str = new StringBuilder();

		str.append("To search for keyword, ");
		str.append("type the word you want to search:" + HTML_BREAK);
		str.append(underline(IDENTIFIER_PLACEHOLDER) + " meeting");

		return wrapWithHtmlTag(str.toString());
	}
	
	/**
	 * @return UNDO Command Guide in String(HTML format)
	 */
	public static String buildUndoGuideString() {

		StringBuilder str = new StringBuilder();

		str.append("To Undo an action, simply type undo");
		str.append(HTML_BREAK);
		str.append(underline(IDENTIFIER_PLACEHOLDER) + " + [ENTER] ");
		str.append("or simply use the shortcut key [Ctrl-Z]");
		
		return wrapWithHtmlTag(str.toString());
	}
	
	/**
	 * @return REDO Command Guide in String(HTML format)
	 */
	public static String buildRedoGuideString() {

		StringBuilder str = new StringBuilder();

		str.append("To Redo an action, simply type redo");
		str.append(HTML_BREAK);
		str.append(underline(IDENTIFIER_PLACEHOLDER) + " + [ENTER] ");
		str.append("or simply use the shortcut key [Ctrl-Y]");
		
		return wrapWithHtmlTag(str.toString());
	}

	/**
	 * @param text String to be wrapped in HTML
	 * @return String wrapped with HTML format
	 */
	private static String wrapWithHtmlTag(String text) {
		return String.format(TAG_WRAP_STRING, HTML_OPEN, text, HTML_CLOSE);
	}
	
	/**
	 * @param text String to be wrapped in HTML underline format
	 * @return String wrapped with HTML underline format
	 */
	private static String underline(String text) {
		return String.format(TAG_WRAP_STRING, HTML_UNDERLINE_OPEN, text,
				HTML_UNDERLINE_CLOSE);
	}
}
