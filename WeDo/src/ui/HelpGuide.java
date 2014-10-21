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
		
		 /* Advance help command for "Add" */
		str.append(makeTitleStr("Advance help command for "));
		str.append(makeCommandStr("\"Add\""));
		str.append(HTML_BREAK);
		
		 /* -You can use these commands: add | create | cre8 to
		  * create a task. */
		str.append("-You can use these commands: ");
		str.append(underline("add") + " | " + underline("create") 
				+ " | " + underline("cre8") + " to");
		str.append(HTML_BREAK);
		str.append(" create a task.");
		
		str.append(HTML_BREAK);
		str.append("-If you add a task without any date or time");
		str.append(HTML_BREAK);
		str.append("E.g. add Finish Revising Programming");
		str.append(HTML_BREAK);
		str.append("This task will categorized as Floating Task");
		

		return wrapWithHtmlTag(str.toString());
	}
	
	public static String buildHelpGuideViewString() {

		StringBuilder str = new StringBuilder();
		
		 /* Advance help command for "View" */
		str.append(makeTitleStr("Advance help command for "));
		str.append(makeCommandStr("\"View\""));
		str.append(HTML_BREAK);
		
		return wrapWithHtmlTag(str.toString());
	}
	
	public static String buildHelpGuideEditString() {

		StringBuilder str = new StringBuilder();
		
		 /* Advance help command for "Edit" */
		str.append(makeTitleStr("Advance help command for "));
		str.append(makeCommandStr("\"Edit\""));
		str.append(HTML_BREAK);
		
		return wrapWithHtmlTag(str.toString());
	}
	
	public static String buildHelpGuideDeleteString() {

		StringBuilder str = new StringBuilder();
		
		 /* Advance help command for "Delete" */
		str.append(makeTitleStr("Advance help command for "));
		str.append(makeCommandStr("\"Delete\""));
		str.append(HTML_BREAK);
		
		return wrapWithHtmlTag(str.toString());
	}
	
	public static String buildHelpGuideSearchString() {

		StringBuilder str = new StringBuilder();
		
		 /* Advance help command for "Search" */
		str.append(makeTitleStr("Advance help command for "));
		str.append(makeCommandStr("\"Search\""));
		str.append(HTML_BREAK);
		
		return wrapWithHtmlTag(str.toString());
	}
	
	
	private static String makeTitleStr(String strMsg){
		strMsg = bold(strMsg);
		strMsg = underline(strMsg);
		strMsg = upFontSize(strMsg);
		
		return strMsg;
	}
	
	private static String makeCommandStr(String strMsg){
		strMsg = bold(strMsg);
		strMsg = underline(strMsg);
		strMsg = upFontSize(strMsg);
		strMsg = italic(strMsg);
		strMsg = fontColor(strMsg);
		
		return strMsg;
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
		
	private static String italic(String text){
		return String.format(TAG_WRAP_STRING, HTML_ITALIC_OPEN, 
				text, HTML_ITALIC_CLOSE);
	}
	
	private static String fontColor(String text){
		return String.format(TAG_WRAP_STRING, HTML_FONTCOLOR_OPEN, 
				text, HTML_FONT_CLOSE);
	}
	
	private static String upFontSize(String text){
		return String.format(TAG_WRAP_STRING, HTML_FONTSIZE_OPEN, 
				text, HTML_FONT_CLOSE);
	}
}
