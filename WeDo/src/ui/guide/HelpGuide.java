package ui.guide;

/**
 // @author A0112636M
 * This class create the Help guide String
 * which is shown on the Help Menu
 * 
 */
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
	private static final String HTML_FONTCOLORRED_OPEN = "<font color=#C80000 >"; //red
	private static final String HTML_FONTCOLORGREEN_OPEN = "<font color=green>"; 
	private static final String HTML_FONT_CLOSE = "</font>";
	private static final String spaceOr = " | ";
	
	/**
	 * @return Advance help command for "Add" in String
	 */
	public static String buildHelpGuideAddString() {

		StringBuilder str = new StringBuilder();
		
		str.append(makeTitleStr("Advance help command for "));
		str.append(makeCommandStr("\"Add\""));
		str.append(HTML_BREAK);
		
		str.append("-You can use these commands: ");
		str.append(underline("add") + spaceOr + underline("create") 
				+ spaceOr + underline("cre8") + " to");
		str.append(HTML_BREAK);
		str.append(" create a task.");
		
		str.append(HTML_BREAK);
		str.append("1. " + bold("Normal Task."));
		str.append(HTML_BREAK);
		str.append("You can add a task specifying the date and time duration");
		str.append(HTML_BREAK);
		str.append(makeExample("E.g. \"add Project Meeting 21/10/2014 12pm - 2pm\""));
		
		str.append(HTML_BREAK);
		str.append("2. " + bold("Deadline Task."));
		str.append(HTML_BREAK);
		str.append("You can add a task specifying the deadline");
		str.append(HTML_BREAK);
		str.append(makeExample("E.g. \"create Submit Assignment by 21/10/2014 at 2pm\""));
		
		str.append(HTML_BREAK);
		str.append("3. " + bold("Floating Task."));
		str.append(HTML_BREAK);
		str.append("You can add a task WITHOUT any date or time");
		str.append(HTML_BREAK);
		str.append(makeExample("E.g. \"cre8 Finish Revising Programming\""));
		
		str.append(HTML_BREAK + HTML_BREAK);
		str.append("Special shortcut keyword can also be used");
		str.append(HTML_BREAK);
		str.append("Specifying the date: ");
		str.append(underline("tomorrow") + spaceOr + underline("tmr") + spaceOr + underline("tml"));
		
		str.append(HTML_BREAK + HTML_BREAK);
		str.append("---------------------------- END ----------------------------");

		return wrapWithHtmlTag(str.toString());
	}
	
	/**
	 * @return  Advance help command for "View" in String
	 */
	public static String buildHelpGuideViewString() {

		StringBuilder str = new StringBuilder();
		
		str.append(makeTitleStr("Advance help command for "));
		str.append(makeCommandStr("\"View\""));
		str.append(HTML_BREAK);
		
		str.append("-You can use these commands: ");
		str.append(underline("view") + spaceOr + underline("read") 
				+ " to view all");
		str.append(HTML_BREAK);
		str.append("the tasks on a specific date.");
		
		str.append(HTML_BREAK);
		str.append("1. " + bold("View Tasks Today."));
		str.append(HTML_BREAK);
		str.append(makeExample("E.g. \"view today\""));
		str.append(HTML_BREAK);
		str.append("All the tasks with today's date will be shown when the");
		str.append(HTML_BREAK);
		str.append("above command is entered.");
		
		str.append(HTML_BREAK);
		str.append("2. " + bold("View Tasks on a specific date."));
		str.append(HTML_BREAK);
		str.append(makeExample("E.g. \"view 21/10/2014\""));
		str.append(HTML_BREAK);
		str.append("You can also use " + underline("yesterday") + spaceOr + underline("tomorrow") 
				+ " to view all the");
		str.append(HTML_BREAK);
		str.append("tasks dated yesterday or tomorrow respectively.");
		
		str.append(HTML_BREAK);
		str.append("3. " + bold("View All tasks / View Completed tasks"));
		str.append(HTML_BREAK);
		str.append(bold("/  View Uncompleted tasks."));
		str.append(HTML_BREAK);
		str.append(makeExample("E.g. \"view all\"") + spaceOr + makeExample("\"view completed\"") + 
				spaceOr + makeExample("\"view uncompleted\""));
		str.append(HTML_BREAK);
		str.append("respectively...");
		
		str.append(HTML_BREAK + HTML_BREAK);
		str.append("---------------------------- END ----------------------------");
		
		return wrapWithHtmlTag(str.toString());
	}
	
	/**
	 * @return Advance help command for "Edit" in String
	 */
	public static String buildHelpGuideEditString() {

		StringBuilder str = new StringBuilder();
		
		str.append(makeTitleStr("Advance help command for "));
		str.append(makeCommandStr("\"Edit\""));
		str.append(HTML_BREAK);
		
		str.append("-You can use these commands: ");
		str.append(underline("edit") + spaceOr + underline("modify") 
				+ spaceOr + underline("update") + " to ");
		str.append(HTML_BREAK);
		str.append(" edit a specific task.");
		
		str.append(HTML_BREAK);
		str.append("1. " + bold("Edit a specific task."));
		str.append(HTML_BREAK);
		str.append(makeExample("E.g. \"edit 1 21/10/2014\""));
		str.append(HTML_BREAK);
		str.append("This will edit the task with ID 1 changing the date to the");
		str.append(HTML_BREAK);
		str.append("one specified in the command.");
		
		str.append(HTML_BREAK + HTML_BREAK);
		str.append("---------------------------- END ----------------------------");

		return wrapWithHtmlTag(str.toString());
	}
	
	/**
	 * @return Advance help command for "Delete" in String
	 */
	public static String buildHelpGuideDeleteString() {

		StringBuilder str = new StringBuilder();
		
		str.append(makeTitleStr("Advance help command for "));
		str.append(makeCommandStr("\"Delete\""));
		str.append(HTML_BREAK);
		
		str.append("-You can use these commands: ");
		str.append(underline("delete") + spaceOr + underline("remove") 
				+ spaceOr + underline("cancel"));
		str.append(HTML_BREAK);
		str.append(" to delete a specific task.");
		
		str.append(HTML_BREAK);
		str.append("1. " + bold("Delete a specific task."));
		str.append(HTML_BREAK);
		str.append(makeExample("E.g. \"delete 2\""));
		str.append(HTML_BREAK);
		str.append("By specifying the ID of the task, the task with the ID 2 in");
		str.append(HTML_BREAK);
		str.append("the above example task will be removed.");
		str.append(HTML_BREAK);
		str.append("2. " + bold("Delete multiple tasks."));
		str.append(HTML_BREAK);
		str.append(makeExample("E.g. \"delete 1 - 10\"") + spaceOr + 
				makeExample("\"delete 1 to 10\""));
		str.append(HTML_BREAK);
		str.append("By specifying the ID tasks of 1 to 10, the tasks with");
		str.append(HTML_BREAK);
		str.append("the ID 1 to 10 will be deleted.");
		
		str.append(HTML_BREAK + HTML_BREAK);
		str.append("---------------------------- END ----------------------------");
		
		return wrapWithHtmlTag(str.toString());
	}
	
	/**
	 * @return Advance help command for "Search" in String
	 */
	public static String buildHelpGuideSearchString() {

		StringBuilder str = new StringBuilder();
		
		str.append(makeTitleStr("Advance help command for "));
		str.append(makeCommandStr("\"Search\""));
		str.append(HTML_BREAK);
		
		str.append("-You can use these commands: ");
		str.append(underline("search") + spaceOr + underline("s") 
				+ spaceOr + underline("find") + spaceOr + underline("f"));
		str.append(HTML_BREAK);
		str.append(" to search for a keyword.");
		
		str.append(HTML_BREAK);
		str.append("1. " + bold("Search a specific task."));
		str.append(HTML_BREAK);
		str.append(makeExample("E.g. \"search meeting\""));
		str.append(HTML_BREAK);
		str.append("By specifying the keyword \"meeting\", all the keywords");
		str.append(HTML_BREAK);
		str.append("\"meeting\" will be shown.");
		
		str.append(HTML_BREAK + HTML_BREAK);
		str.append("---------------------------- END ----------------------------");
		
		return wrapWithHtmlTag(str.toString());
	}
	
	/**
	 * @param strMsg Title of the guide in String
	 * @return text in bold
	 */
	private static String makeTitleStr(String strMsg){
		strMsg = bold(strMsg);
		strMsg = underline(strMsg);
		strMsg = upFontSize(strMsg);
		
		return strMsg;
	}
	
	/**
	 * @param strMsg the command in String
	 * @return text for Command(bold, underline, increase font size,
	 * italic, font color green)
	 */
	private static String makeCommandStr(String strMsg){
		strMsg = bold(strMsg);
		strMsg = underline(strMsg);
		strMsg = upFontSize(strMsg);
		strMsg = italic(strMsg);
		strMsg = fontColorGreen(strMsg);
		
		return strMsg;
	}
	
	/**
	 * @param strMsg the example in String
	 * @return text for example(font color red, italic)
	 */
	private static String makeExample(String strMsg){
		strMsg = fontColorRed(strMsg);
		strMsg = italic(strMsg);
		
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
	
	private static String fontColorRed(String text){
		return String.format(TAG_WRAP_STRING, HTML_FONTCOLORRED_OPEN, 
				text, HTML_FONT_CLOSE);
	}
	
	private static String fontColorGreen(String text){
		return String.format(TAG_WRAP_STRING, HTML_FONTCOLORGREEN_OPEN, 
				text, HTML_FONT_CLOSE);
	}
	
	private static String upFontSize(String text){
		return String.format(TAG_WRAP_STRING, HTML_FONTSIZE_OPEN, 
				text, HTML_FONT_CLOSE);
	}
}
