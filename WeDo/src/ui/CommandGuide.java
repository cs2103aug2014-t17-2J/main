//@ Andy Hsu Wei Qiang
package ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import ui.Keywords;
import userInterface.UserIntSwing;

public class CommandGuide {
	private static final String GENERAL_GUIDE = buildGeneralGuideString();
	private static final String ADD_GUIDE = buildAddGuideString();
	
	private static final String HTML_OPEN = "<html>";
	private static final String HTML_CLOSE = "</html>";
	private static final String HTML_BREAK = "<br>";
	private static final String HTML_UNDERLINE_OPEN = "<u>";
	private static final String HTML_UNDERLINE_CLOSE = "</u>";
	private static final String TAG_WRAP_STRING = "%s%s%s";
	private static final String DATE_FORMAT = "dd/MM/yy";
	private static final String WHITESPACE_PATTERN = "\\s+";
	private static final String IDENTIFIER_PLACEHOLDER = "%1$s";
	
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

	private static String buildGuideMessage(String identifier) {

		identifier = identifier.toLowerCase();
		Action action = Keywords.resolveActionIdentifier(identifier);

		switch (action) {
		case ADD:
			return String.format(ADD_GUIDE, identifier);
		default:
			return GENERAL_GUIDE;
		}
	}
	
	public static void processGuide(){
		if(UserIntSwing.textField.getText() == ""){
			UserIntSwing.lblWarning.setText(buildGeneralGuideString());
		}
		else if(UserIntSwing.textField.getText() == "-add"){
		
		}
	}

	public static String buildGeneralGuideString() {

		StringBuilder str = new StringBuilder();

		str.append("Type any of the following to begin:");
		str.append(HTML_BREAK);
		str.append(Keywords.getAddTaskIdentifier() + " | ");
		str.append(Keywords.getUpdateTaskIdentifier() + " | ");
		str.append(Keywords.getDeleteTaskIdentifier() + " | ");
		str.append(Keywords.getListTaskIdentifier());

		return wrapWithHtmlTag(str.toString());
	}
	
	private static String buildAddGuideString() {

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String date = sdf.format(new Date());
		StringBuilder str = new StringBuilder();

		str.append("To schedule a task, type:");
		str.append(HTML_BREAK);

		str.append("- Normal Task: ");
		str.append(underline(IDENTIFIER_PLACEHOLDER) + " meet Prof Aaron ");
		str.append(underline(Keywords.getDateIdentifier()) + " " + date + " ");
		str.append(underline(Keywords.getTimeIdentifier()) + " 12.00 - 14.00 ");
		str.append(underline(Keywords.getRemarksIdentifier()) + " Consultation");
		str.append(HTML_BREAK);

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
