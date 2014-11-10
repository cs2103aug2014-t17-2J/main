package ui;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import logic.LogicManager;
import logic.command.commandList.AddCommand;
import logic.command.commandList.EditCommand;
import logic.command.commandList.SearchCommand;
import logic.command.commandList.ViewCommand;
import logic.exception.InvalidCommandException;
import logic.parser.DynamicParseResult;
import logic.parser.ParseResult;
import logic.parser.ParserFlags;
import logic.utility.KeyWordMappingList;
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
 // @author A0112636M
  * This class handles all the GUI logic which the
  * user execute.
  */
public class UserInterfaceMain {
	private static final String DATE_FORMAT_FIRST = "dd-MMM-yy";
	private static final String DATE_FORMAT_SECOND = "dd/MM/yyyy";
	private static final int taskbarHeight = 40;

	private static String userInput = new String();
	private static String VIEW_TASKS_SOMEDAY_STRING = "Someday";
	private static final SimpleDateFormat sdf_first = new SimpleDateFormat(DATE_FORMAT_FIRST);
	private static final SimpleDateFormat sdf_second = new SimpleDateFormat(DATE_FORMAT_SECOND);

	/**
	 * This operation initialize all the Processes 
	 */
	public static void initProcess() {
		setupFrameLocation();
		ListenerHandler.addFrameWindowFocusListener();
		initAllListener();
		FormatHandler.formatAll();
		UserIntSwing.lblCommandGuide.setText(CommandGuide.buildGeneralGuideString());
		UserIntSwing.lblTodayDate.setText(setTodayDate());
	}

	/**
	 * This operation initialize all the Listener Processes
	 */
	private static void initAllListener() {
		ListenerHandler.addFrameLocationListener();
		ListenerHandler.addBtnHelpListener();
		ListenerHandler.addBtnAddListener();
		ListenerHandler.addBtnViewListener();
		ListenerHandler.addBtnEditListener();
		ListenerHandler.addBtnDeleteListener();
		ListenerHandler.addBtnSearchListener();
		ListenerHandler.addBtnEnterListener();
		ListenerHandler.addBtnMinimizeListener();
		ListenerHandler.addBtnCloseListener();
		ListenerHandler.addBtnSettingListener();
		ListenerHandler.addSystemTrayWindowStateListener();
		ListenerHandler.addTextfieldKeyListener();
		ListenerHandler.addTextFieldActionListener();
		ListenerHandler.addBtnEnterActionListener();
		ListenerHandler.setBalloonTipVisibleFalse();
	}

	/**This operation display the date range of the table
	 * 
	 * @return dateDisplay the date in String
	 */
	private static String setTodayDate() {	
		Calendar calendar = Calendar.getInstance();
		int dayOfWeekInt = calendar.get(Calendar.DAY_OF_WEEK);
		String date = sdf_first.format(new Date());
		String dayOfWeekString;

		switch (dayOfWeekInt) {
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

	/**Process lblViewTask to view tasks that the user is currently viewing
	 * 
	 * @param parseResult 
	 * @return String telling the user what date is he viewing
	 */
	public static String viewDateTask(Task task) {
		String getDateStr = task.getDateTimeString();
		
		if(getDateStr.isEmpty() && task.getDateTimeString() != null) {
			return FeedbackGuide.formatViewAllTask(task.getDescription());
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
		else if(task.getEndDate() == Task.DATE_NOT_SET) {
			return FeedbackGuide.formatViewSomedayTask(VIEW_TASKS_SOMEDAY_STRING);
		}
		else{
			return FeedbackGuide.formatViewDateTask(getDateStr);
		}
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
	public static void setupFrameLocation() {
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
		ParseResult parseResult = UserIntSwing.logicManager.interpret(userInput);

		if (parseResult.isSuccessful()) {
			successfulTextfieldOperation(parseResult);
			ListenerHandler.setBalloonTipVisibleFalse();
		} 
		else if(UserIntSwing.textField.getText().isEmpty()) {
			FeedbackHandler.emptyStringOperation();
		} 
		else if (FeedbackHandler.isDoubleSpace(userInput)) {
			FeedbackHandler.emptyStringOperation();
		} 
		else {
			FeedbackHandler.NotSuccessfulOperation(parseResult.getFailedMessage());
			ListenerHandler.setBalloonTipVisibleFalse();
		}
		UserIntSwing.textField.setText(null);
	}
	
	/**
	 * Handle this operation when command entered is correctly input
	 * 
	 * @param parseResult determine what command by taking the user input String
	 */
	private static void successfulTextfieldOperation(ParseResult parseResult) {
		try {
			UserIntSwing.textField.setText(null);
			UserIntSwing.lblCommandGuide.setText(CommandGuide.buildGeneralGuideString());
			UserIntSwing.logicManager.executeCommand(parseResult);
			
			if(correctCommandExtracted(parseResult)) {
				UserIntSwing.lblViewTask.setText(viewDateTask(parseResult.getTask()));
			}
		} 
		catch (InvalidCommandException exception) {
			UserIntSwing.textField.setText(null);
			FeedbackHandler.NotSuccessfulOperation(exception.getMessage());
			// Log this error.
			return;
		}
		FeedbackHandler.successfulOperation();
	}
	
	/**
	 * If the correct command is extracted for the task currently viewing
	 * 
	 * @param parseResult Command extracted
	 * @return boolean if correct command is extracted
	 */
	private static boolean correctCommandExtracted(ParseResult parseResult) {
		return (parseResult.getCommand() instanceof AddCommand) 
				|| (parseResult.getCommand() instanceof ViewCommand)
				|| (parseResult.getCommand() instanceof SearchCommand && 
						parseResult.getTask().getEndDate() != Task.DATE_NOT_SET);
	}
	
	/**
	 * Enter Key Listener process
	 * 
	 * @param arg1 KeyEvent Enter from the textfield
	 */
	public static void processEnterkey(KeyEvent arg1) {
		userInput = UserIntSwing.textField.getText();
		TextfieldHistory.getTextfieldString(userInput);
	}
	
	/**
	 * Textfield processeses
	 * 
	 * @param arg1 KeyEvent from the textfield
	 * @param userInput Input that the user entered from the textfield
	 * @throws InvalidCommandException 
	 */
	public static void processTextfield(KeyEvent arg1) {
		userInput = UserIntSwing.textField.getText();
		UserIntSwing.lblCommandGuide.setText(CommandGuide.getGuideMessage(userInput));
		TextfieldHistory.showTextfieldHistory(arg1);
	}
	
	/**
	 * Process the textField when key is released
	 * 
	 * @param arg1 Keyevent code from keyboard
	 */
	public static void processTextfieldKeyReleased(KeyEvent arg1) {
		processTextfield(arg1);
		DynamicParseResult parseResult = processUserParse(arg1, UserIntSwing.logicManager);
		Task task = parseResult.getTask();
		clearDynamicParseLabels();
		handleDynamicEdit(parseResult, task);
		showParseResult(parseResult, task);
	}

	/**
	 * This operation process the hotkeys shortcut function
	 * 
	 * @param key KeyEvent keylistener from the textfield
	 * @throws InvalidCommandException 
	 */
	public static void processHotKeys(KeyEvent key) {
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
		UserIntSwing.lblCommandGuide.setText(CommandGuide.getGuideMessage(userInput));
		/*process the redo and undo using InputMap and ActionMap*/
		HotkeyHandler.undo();
		HotkeyHandler.redo();
		HotkeyHandler.minimise();
		HotkeyHandler.scrollUpTable();
		ListenerHandler.setBalloonTipVisibleFalse();
	}
	
	public static void handleDynamicEdit(DynamicParseResult parseResult,
			Task task) {
		if (containsValidEditCommand(parseResult)) {
			String indexString = getIndexString(task);
			int index = getTaskToBeEditedIndex(indexString);
			Task taskToBeEdited = UserIntSwing.logicManager.getTaskToBeEdited(index);
			if(taskToBeEdited != null)
			{
				task.setDescription(StringHandler.removeFirstMatched(
						task.getDescription(), indexString));
				parseResult = handleSomeDayEdit(parseResult, indexString);
				showTaskToBeEdited(taskToBeEdited);
				UserIntSwing.interactiveForm.selectRow(index);
			}else {
				showInvalidIndexMessage(task);
			}
		}
	}
	
	/**
	 * Help remove the date and someday description if it is in the description
	 * 
	 * @param parseResult the parse result
	 * @param indexString the index in string
	 * @return dynamic parse result with someday removed if there is
	 */
	private static DynamicParseResult handleSomeDayEdit(DynamicParseResult parseResult, String indexString) {
        boolean somedaySpecified;

        Collection<String> someDayCollection = KeyWordMappingList.getSomeDayKeyWord().
        		get(Task.DATE_NOT_SET);
        String[] someDayKeyWords = (String[]) someDayCollection.toArray();

        somedaySpecified = isSomeDaySpecified(
                parseResult.getDescriptionWordUsed(), someDayKeyWords);

        if (somedaySpecified) {
            String newDescription =removeSomeDayKeyWord(parseResult.getDescriptionWordUsed(), someDayKeyWords);
            newDescription = StringHandler.removeFirstMatched(newDescription, indexString).trim();
            if(!(newDescription.isEmpty()))
            {
                parseResult.getParseFlags().add(ParserFlags.DESCRIPTION_FLAG);
                parseResult.setDescriptionWordUsed(" " + newDescription + " ");
                parseResult.getTask().setDescription(" " + newDescription + " ");
            }
            else {
                parseResult.getParseFlags().remove(ParserFlags.DESCRIPTION_FLAG);
            }
            if(somedaySpecified) {
                parseResult.getParseFlags().add(ParserFlags.DATE_FLAG);
                parseResult.setTask(setSomeday(parseResult.getTask()));
            }
        }
        return parseResult;
	}
	
	  /**
     * Set the task to someday
     * 
     * @param editedTask the task to be set to someday    
     * @return the task to be shown with someday set.
     */
    private static Task setSomeday(Task taskToBeShown) {
        taskToBeShown.setEndDate(Task.DATE_NOT_SET);
        taskToBeShown.setStartDate(Task.DATE_NOT_SET);
        taskToBeShown.setEndTime(Task.TIME_NOT_SET);
        taskToBeShown.setStartTime(Task.TIME_NOT_SET);
        return taskToBeShown;
    }

    /**
     * Remove the some day keyword from the description
     * 
     * @param description the description of the task parsed
     * @param someDayKeyWords the keywords for someday        
     * @return String, the new description with some day removed
     */
    private static String removeSomeDayKeyWord(String description, String[] someDayKeyWords) {
        String matchedWord = StringHandler.getContainsWord(
                description, someDayKeyWords);
        String newDescription = StringHandler.removeFirstMatchedWord(
                description, matchedWord);
        
        return newDescription;
    }

    /**
     * Check if someday is specified
     * 
     * @param description the description of the parsed task
     * @param someDayKeyWords the keywords for someday
     * @return if someday is specified
     */
    private static boolean isSomeDaySpecified(String description, String[] someDayKeyWords) {
        boolean somedaySpecified;

        if (description != null) {
            if (StringHandler.containsWord(description,
                    someDayKeyWords)) {
                somedaySpecified = true;
            } else
                somedaySpecified = false;
        } else {
            somedaySpecified = false;
        }
        return somedaySpecified;
    }
	
	/**
	 * Show the user error message when error command is pressed
	 * 
	 * @param task Determine what task it it
	 */
	private static void showInvalidIndexMessage(Task task) {
		final String INVALID_INDEX = "The index you are editing is INVALID";
		task.setDescription(INVALID_INDEX);
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
		UserIntSwing.lblPriorityProcess.setOpaque(false);
	}

	/**
	 * Show the parse result to the user
	 * 
	 * @param parseResult the result that was parse
	 * @param task the task that was parse
	 */
	public static void showParseResult(DynamicParseResult parseResult, Task task) {
		for (ParserFlags parseFlag : parseResult.getParseFlags()) {
			switch (parseFlag) {
			case COMMAND_FLAG:
				UserIntSwing.lblCommandProcess.setText(parseResult.getCommandWordUsed());
				break;
			case DATE_FLAG:
				UserIntSwing.lblDateProcess.setText(task.getDateTimeString());
				ListenerHandler.addLblDateProcessListener();
				break;
			case DESCRIPTION_FLAG:
				if (!task.getDescription().isEmpty()) {
					UserIntSwing.lblDescriptionProcess.setText(task.getDescription());
					ListenerHandler.addLblDescriptionProcessListener();
				}
				break;
			case PRIORITY_FLAG:
				UserIntSwing.lblPriorityProcess.setOpaque(true);
				UserIntSwing.lblPriorityProcess.setText(task.getPriority().toString());
				processLblPriority();
				break;
			default:
				break;
			}
		}
	}

	/**
	 * get the string which contains the index at the first word
	 * 
	 * @param task the new task that will edit the old task
	 * @return the string which contains the index
	 */
	private static String getIndexString(Task task) {
		String indexString = StringHandler.getIntegerFromFirstSlot(
				task.getDescription());
		return indexString;
	}

	/**
	 * Help determines if the parse result contains valid edit command
	 * 
	 * @param parseResult the parse result
	 * @return if it contains valid edit command
	 */
	private static boolean containsValidEditCommand(DynamicParseResult parseResult) {
		return parseResult.getParseFlags().contains(ParserFlags.COMMAND_FLAG)
				&& parseResult.getParseFlags().contains(
						ParserFlags.DESCRIPTION_FLAG)
						&& parseResult.getCommand() instanceof EditCommand;
	}

	/**
	 * Show the task that is to be edited on the GUI
	 * 
	 * @param taskToBeEdited the task to be edited
	 */
	private static void showTaskToBeEdited(Task taskToBeEdited) {
		UserIntSwing.lblDateProcess.setText(taskToBeEdited.getDateTimeString());
		UserIntSwing.lblDescriptionProcess.setText(taskToBeEdited
				.getDescription());
		UserIntSwing.lblPriorityProcess.setText(taskToBeEdited.getPriority()
				.toString());
	}

	/**
	 * Convert string to integer
	 * 
	 * @param indexString the string which contains the index to extract
	 * @return the index in integer form
	 */
	private static int getTaskToBeEditedIndex(String indexString) {
		final int ARRAY_OFFSET = -1;
		return StringHandler.parseStringToInteger(indexString) + ARRAY_OFFSET;
	}

	/**
	 * This operation process the priority label Red: High; Orange: Medium; Green: Low
	 */
	private static void processLblPriority() {
		if (UserIntSwing.lblPriorityProcess.getText().matches("High")) {
			UserIntSwing.lblPriorityProcess.setBackground(Color.orange);
		} else if (UserIntSwing.lblPriorityProcess.getText().matches("Low")) {
			UserIntSwing.lblPriorityProcess.setBackground(Color.green);
		} else {
			UserIntSwing.lblPriorityProcess.setBackground(Color.yellow);
		}
	}
}
