package ui;

public class HelpGuide {
	private static final String TAG_WRAP_STRING = "%s%s%s";
	private static final String COMMAND_WRAP_STRING = "%s%s";
	private static final String TITLE_WRAP_STRING = "%s%s%s%s%s";
	private static final String HTML_BREAK = "<br>";
	private static final String HTML_OPEN = "<html>";
	private static final String HTML_CLOSE = "</html>";
	private static final String HTML_UNDERLINE_OPEN = "<u>";
	private static final String HTML_UNDERLINE_CLOSE = "</u>";
	private static final String HTML_BOLD_OPEN = "<b>";
	private static final String HTML_BOLD_CLOSE = "</b>";
	private static final String HTML_ITALIC_OPEN = "<i>";
	private static final String HTML_ITALIC_CLOSE = "</i>";
	private static final String HTML_FONTSIZE_OPEN = "<font size=+1>";
	private static final String HTML_FONTCOLOR_OPEN = "<font color=red>";
	private static final String HTML_FONT_CLOSE = "</font>";
	
	public static String buildHelpGuideAddString() {

		StringBuilder str = new StringBuilder();

		str.append(title("Advance Help command for "));
		str.append(command("ADD"));
		str.append(HTML_BREAK);
		str.append(underline("add") + " | " + underline("create") 
				+ " | " + underline("cre8") );
		str.append("task");
		str.append("Date");
		str.append("Time");

		return wrapWithHtmlTag(str.toString());
	}
	
	private static String wrapWithHtmlTag(String text) {
		return String.format(TAG_WRAP_STRING, HTML_OPEN, text, HTML_CLOSE);
	}
	
	private static String underline(String text) {
		return String.format(TAG_WRAP_STRING, HTML_UNDERLINE_OPEN, text,
				HTML_UNDERLINE_CLOSE);
	}
	
	private static String title(String text) {
		return String.format(TITLE_WRAP_STRING, HTML_UNDERLINE_OPEN, 
				HTML_BOLD_OPEN, HTML_FONTSIZE_OPEN, 
				text, HTML_FONT_CLOSE, HTML_BOLD_CLOSE, HTML_UNDERLINE_CLOSE);
	}
	
	private static String command(String text){
		return String.format(COMMAND_WRAP_STRING, HTML_ITALIC_OPEN, HTML_FONTCOLOR_OPEN, 
				text, HTML_FONT_CLOSE, HTML_ITALIC_CLOSE);
	}
}
