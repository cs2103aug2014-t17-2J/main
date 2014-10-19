package ui;

public class HelpGuide {
	private static final String HTML_BREAK = "<br>";
	
	public static String buildHelpGuideAddString() {

		StringBuilder str = new StringBuilder();

		str.append("");
		str.append(HTML_BREAK);
		str.append(Keywords.getAddTaskIdentifier() + " | ");
		str.append(Keywords.getViewTaskIdentifier() + " | ");
		str.append(Keywords.getEditTaskIdentifier() + " | ");
		str.append(Keywords.getDeleteTaskIdentifier());

		return "";
	}
}
