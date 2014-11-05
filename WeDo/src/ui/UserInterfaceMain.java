package ui;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import logic.LogicManager;
import logic.command.commandList.AddCommand;
import logic.command.commandList.EditCommand;
import logic.command.commandList.RedoCommand;
import logic.command.commandList.UndoCommand;
import logic.command.commandList.ViewCommand;
import logic.exception.InvalidCommandException;
import logic.parser.DynamicParseResult;
import logic.parser.ParseResult;
import logic.parser.ParserFlags;
import logic.utility.StringHandler;
import logic.utility.Task;
import ui.guide.CommandGuide;
import ui.guide.FeedbackGuide;
import ui.logic.command.FeedbackHandler;
import ui.logic.command.FormatHandler;
import ui.logic.command.HotkeyHandler;
import ui.logic.command.ListenerHandler;
import ui.logic.command.VK;
import userInterface.UserIntSwing;

/**
 * @author Andy Hsu Wei Qiang This class handles all the GUI logic which the
 *         user execute.
 */
public class UserInterfaceMain {
	private static final String DATE_FORMAT_FIRST = "dd-MMM-yy";
	private static final String DATE_FORMAT_SECOND = "dd/MM/yyyy";
	private static final int taskbarHeight = 40;

	private static String userInput = new String();
	private static final SimpleDateFormat sdf_first = new SimpleDateFormat(DATE_FORMAT_FIRST);
	private static final SimpleDateFormat sdf_second = new SimpleDateFormat(DATE_FORMAT_SECOND);
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_SECOND);

	/**
	 * This operation initialize all the Processes 
	 */
	public static void initProcess() {
		setupFrameLocation();
		ListenerHandler.addFrameWindowFocusListener();
		initAllListener();
		FormatHandler.formatAll();
		UserIntSwing.lblHelp.setText(CommandGuide.buildGeneralGuideString());
		UserIntSwing.lblTodayDate.setText(setTodayDate());
	}

	/**
	 * This operation initialize all the Listener Processes
	 */
	private static void initAllListener() {
		ListenerHandler.addBtnHelpListener();
		ListenerHandler.addBtnAddListener();
		ListenerHandler.addBtnViewListener();
		ListenerHandler.addBtnEditListener();
		ListenerHandler.addBtnDelListener();
		ListenerHandler.addBtnSearchListener();
		ListenerHandler.addSystemTrayWindowStateListener();
		ListenerHandler.addTextfieldKeyListener();
		ListenerHandler.addTextFieldActionListener();
		ListenerHandler.addBtnEnterActionListener();
	}

	/**This operation display the date range of the table
	 * @return dateDisplay the date in String
	 */
	private static String setTodayDate() {	
		Calendar calendar = Calendar.getInstance();
		int dayOfWeekInt = calendar.get(Calendar.DAY_OF_WEEK);
		String date = sdf_first.format(new Date());
		String dayOfWeekString;

		switch (dayOfWeekInt){
			case Calendar.MONDAY: dayOfWeekString = "Monday";
				break;
			case Calendar.TUESDAY: dayOfWeekString = "Tuesday";
				break;
			case Calendar.WEDNESDAY: dayOfWeekString = "Wednesday";
				break;
			case Calendar.THURSDAY: dayOfWeekString = "Thursday";
				break;
			case Calendar.FRIDAY: dayOfWeekString = "Friday";
				break;
			case Calendar.SATURDAY: dayOfWeekString = "Saturday";
				break;
			default: dayOfWeekString = "Sunday";
				break;
		}
		String dateDisplay = date + " " + dayOfWeekString;

		return dateDisplay;
	}

	/**Process lblViewTask to view tasks that the user is 
	 * currently viewing
	 * @param parseResult 
	 * @return String telling the user what date is he viewing
	 */
	public static String viewDateTask(ParseResult parseResult) {
		String getDateStr = parseResult.getTask().getDateTimeString();
		boolean viewCommand = parseResult.getCommand() instanceof ViewCommand;
		boolean addCommand = parseResult.getCommand() instanceof AddCommand;
		boolean editCommand = parseResult.getCommand() instanceof EditCommand;
		boolean undoCommand = parseResult.getCommand() instanceof UndoCommand;
		boolean redoCommand = parseResult.getCommand() instanceof RedoCommand;
		
		if(viewCommand || addCommand || editCommand || undoCommand || redoCommand) {
			if(getDateStr.isEmpty()) {
				getDateStr = UserIntSwing.lblDateProcess.getText();
			}
			
			if(getDateStr.matches(dateToday())) {
				return FeedbackGuide.formatViewTodayTask();
			}
			else if(getDateStr.matches(dateTomorrow())) {
				return FeedbackGuide.formatViewTomorrowTask();
			}
			else if(getDateStr.matches(dateYesterday())) {
				return FeedbackGuide.formatViewYesterdayTask();
			}
			else{
				return FeedbackGuide.formatViewDateTask(getDateStr);
			}
		}
		return FeedbackGuide.formatViewTodayTask();
	}

	/**
	 * @return todayAsString the date today as String
	 */
	private static String dateToday() {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		String todayAsString = sdf_second.format(today);

		return todayAsString;
	}

	/**
	 * @return tomorrowAsString the date tomorrow as String
	 */
	private static String dateTomorrow() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		String tomorrowAsString = sdf_second.format(tomorrow);

		return tomorrowAsString;
	}

	/**
	 * @return yesterdayAsString the date yesterday as String
	 */
	private static String dateYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date yesterday = calendar.getTime();
		String yesterdayAsString = sdf_second.format(yesterday);

		return yesterdayAsString;
	}

	/**
	 * This operation sets the program at the bottom right hand corner of screen
	 */
	private static void setupFrameLocation() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
		int Xcoordinate = (int) rect.getMaxX() - UserIntSwing.frame.getWidth();
		int Ycoordinate = (int) rect.getMaxY() - UserIntSwing.frame.getHeight() - taskbarHeight;
		UserIntSwing.frame.setLocation(Xcoordinate, Ycoordinate);
	}

	/**
	 * Process the parser and Feedback
	 */
	public static void processTextfieldString() {
		userInput = UserIntSwing.textField.getText();
		ParseResult parseResult = UserIntSwing.logicManager.processCommand(userInput);

		if (parseResult.isSuccessful()) {
			try {
				UserIntSwing.lblHelp.setText(CommandGuide.buildGeneralGuideString());
				UserIntSwing.logicManager.executeCommand(parseResult);
				UserIntSwing.lblViewTask.setText(viewDateTask(parseResult));
			} 
			catch (InvalidCommandException exception) {
				FeedbackHandler.NotSuccessfulOperation(exception.getMessage());
				UserIntSwing.textField.setText(null);
				// Log this error.
				//exception.printStackTrace();
				return;
			}
			FeedbackHandler.successfulOperation();
		} 
		else if(UserIntSwing.textField.getText().isEmpty()) {
			FeedbackHandler.emptyStringOperation();
		} 
		else if (FeedbackHandler.isDoubleSpace(userInput)) {
			FeedbackHandler.emptyStringOperation();
		} 
		else {
			FeedbackHandler.NotSuccessfulOperation(parseResult.getFailedMessage());
		}
		UserIntSwing.textField.setText(null);
	}

	/**
	 * This operation process the hotkeys shortcut function
	 * @param key KeyEvent keylistener from the textfield
	 * @throws InvalidCommandException 
	 */
	public static void processHotKeys(KeyEvent key) throws InvalidCommandException {
		if (key.getKeyCode() == VK.help()) {
			HelpMenu.main(null);
		} else if (key.getKeyCode() == VK.add()) {
			HotkeyHandler.add();
		} else if (key.getKeyCode() == VK.view()) {
			HotkeyHandler.view();
		} else if (key.getKeyCode() == VK.edit()) {
			HotkeyHandler.edit();
		} else if (key.getKeyCode() == VK.delete()) {
			HotkeyHandler.delete();
		} else if (key.getKeyCode() == VK.search()) {
			HotkeyHandler.search();
		} 
		
		userInput = UserIntSwing.textField.getText();
		UserIntSwing.lblHelp.setText(CommandGuide.getGuideMessage(userInput));
		/*process the redo and undo using InputMap and ActionMap*/
		HotkeyHandler.undo();
		HotkeyHandler.redo();
		HotkeyHandler.minimise();
		HotkeyHandler.scrollUpTable();
	}

	/**
	 * This operation process the labels that the user input from the textfield
	 * and show what will be parsed
	 * 
	 * @param logicManager
	 * @return DynamicParseResult the result that was parsed on run time
	 */
	public static DynamicParseResult processUserParse(KeyEvent arg1, LogicManager logicManager) {
		DynamicParseResult parseResult = logicManager.dynamicParse(
				UserIntSwing.textField.getText());

		return parseResult;
	}

	/**
	 * Clear all the labels related to dynamic parsing
	 */
	public static void clearDynamicParseLabels() {
		UserIntSwing.lblCommandProcess.setText(null);
		UserIntSwing.lblDateProcess.setText(null);
		UserIntSwing.lblDescriptionProcess.setText(null);
		UserIntSwing.lblPriorityProcess.setText(null);
	}

	/**
	 * Show the parse result to the user
	 * @param parseResult the result that was parse
	 * @param task the task that was parse
	 */
	public static void showParseResult(DynamicParseResult parseResult, Task task) {
		for (ParserFlags parseFlag : parseResult.getParseFlags()) {
			switch (parseFlag) {
			case COMMAND_FLAG:
				UserIntSwing.lblCommandProcess.setText(parseResult
						.getCommandWordUsed());
				break;
			case DATE_FLAG:
				UserIntSwing.lblDateProcess.setText(task.getDateTimeString());
				break;
			case DESCRIPTION_FLAG:
				if (!task.getDescription().isEmpty())
					UserIntSwing.lblDescriptionProcess.setText(task
							.getDescription());
				break;
			case PRIORITY_FLAG:
				UserIntSwing.lblPriorityProcess.setText(task.getPriority()
						.toString());
				processLblPriority();
				break;
			default:
				break;
			}
		}
	}

	/**
	 * get the string which contains the index at the first word
	 * @param task the new task that will edit the old task
	 * @return the string which contains the index
	 */
	public static String getIndexString(Task task) {
		String indexString = StringHandler.getIntegerFromFirstSlot(
				task.getDescription());
		return indexString;
	}

	/**
	 * Help determines if the parse result contains valid edit command
	 * @param parseResult the parse result
	 * @return if it contains valid edit command
	 */
	public static boolean containsValidEditCommand(DynamicParseResult parseResult) {
		return parseResult.getParseFlags().contains(ParserFlags.COMMAND_FLAG)
				&& parseResult.getParseFlags().contains(
						ParserFlags.DESCRIPTION_FLAG)
						&& parseResult.getCommand() instanceof EditCommand;
	}

	/**
	 * Show the task that is to be edited on the GUI
	 * @param taskToBeEdited the task to be edited
	 */
	public static void showTaskToBeEdited(Task taskToBeEdited) {
		UserIntSwing.lblDateProcess.setText(taskToBeEdited.getDateTimeString());
		UserIntSwing.lblDescriptionProcess.setText(taskToBeEdited
				.getDescription());
		UserIntSwing.lblPriorityProcess.setText(taskToBeEdited.getPriority()
				.toString());
	}

	/**
	 * Convert string to integer
	 * @param indexString the string which contains the index to extract
	 * @return the index in integer form
	 */
	public static int getTaskToBeEditedIndex(String indexString) {
		final int ARRAY_OFFSET = -1;
		return StringHandler.parseStringToInteger(indexString) + ARRAY_OFFSET;
	}

	/**
	 * This operation process the priority label Red: High; orange: Medium; Green:
	 * Low
	 */
	private static void processLblPriority() {
		if (UserIntSwing.lblPriorityProcess.getText().matches("High")) {
			UserIntSwing.lblPriorityProcess.setBackground(Color.red);
		} else if (UserIntSwing.lblPriorityProcess.getText().matches("Low")) {
			UserIntSwing.lblPriorityProcess.setBackground(Color.green);
		} else {
			UserIntSwing.lblPriorityProcess.setBackground(Color.yellow);
		}
	}
}
