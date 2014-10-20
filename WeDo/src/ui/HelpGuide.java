package ui;

public class HelpGuide {
	private static final String TAG_WRAP_STRING = "%s%s%s";
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

		str.append(underline(bold("Advance Help command for ")));
		str.append(underline(italic(bold(fontColor("ADD")))));
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
	
	private static String bold(String text) {
		return String.format(TAG_WRAP_STRING, HTML_BOLD_OPEN, text, 
				HTML_BOLD_CLOSE);
	}
	
	private static String fontColor(String text){
		return String.format(TAG_WRAP_STRING, HTML_FONTCOLOR_OPEN, 
				text, HTML_FONT_CLOSE);
	}
	
	private static String italic(String text){
		return String.format(TAG_WRAP_STRING, HTML_ITALIC_OPEN, 
				text, HTML_ITALIC_CLOSE);
	}
}
