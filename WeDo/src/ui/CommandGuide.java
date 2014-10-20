//@ Andy Hsu Wei Qiang
package ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import ui.Keywords;

public class CommandGuide {
	private static final String GENERAL_GUIDE = buildGeneralGuideString();
	private static final String ADD_GUIDE = buildAddGuideString();
	private static final String VIEW_GUIDE = buildViewGuideString();
	private static final String EDIT_GUIDE = buildEditGuideString();
	private static final String DELETE_GUIDE = buildDeleteGuideString();
	
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
		default:
			return GENERAL_GUIDE;
		}
	}

	public static String buildGeneralGuideString() {

		StringBuilder str = new StringBuilder();

		str.append("Type any of the following to begin:");
		str.append(HTML_BREAK);
		str.append(Keywords.getAddTaskIdentifier() + " | ");
		str.append(Keywords.getViewTaskIdentifier() + " | ");
		str.append(Keywords.getEditTaskIdentifier() + " | ");
		str.append(Keywords.getDeleteTaskIdentifier());

		return wrapWithHtmlTag(str.toString());
	}
	
	static String buildAddGuideString() {

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
	
	static String buildViewGuideString(){
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
	
	static String buildEditGuideString(){
		StringBuilder str = new StringBuilder();

		str.append("To edit a task, ");
		str.append("select the task number and type:" + HTML_BREAK);
		str.append(underline(IDENTIFIER_PLACEHOLDER) + " 1 ");
		str.append(LABEL_SAMPLE_DATE);

		return wrapWithHtmlTag(str.toString());
	}
	
	static String buildDeleteGuideString(){
		StringBuilder str = new StringBuilder();

		str.append("To delete a task, ");
		str.append("select the task number and type:" + HTML_BREAK);
		str.append(underline(IDENTIFIER_PLACEHOLDER) + " 1");

		return wrapWithHtmlTag(str.toString());
	}

	private static String wrapWithHtmlTag(String text) {
		return String.format(TAG_WRAP_STRING, HTML_OPEN, text, HTML_CLOSE);
	}
	
	private static String underline(String text) {
		return String.format(TAG_WRAP_STRING, HTML_UNDERLINE_OPEN, text,
				HTML_UNDERLINE_CLOSE);
	}
}
