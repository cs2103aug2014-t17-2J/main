package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowStateListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import logic.LogicManager;
import logic.command.commandList.EditCommand;
import logic.exception.InvalidCommandException;
import logic.parser.DynamicParseResult;
import logic.parser.ParserFlags;
import logic.utility.StringHandler;
import logic.utility.Task;
import ui.guide.CommandGuide;
import ui.guide.FeedbackGuide;
import ui.logic.command.Action;
import ui.logic.command.ProcessHotkey;
import ui.logic.command.Keywords;
import ui.logic.command.VK;
import userInterface.UserIntSwing;

/**
 * @author Andy Hsu Wei Qiang This class handles all the GUI logic which the
 *         user execute.
 */
public class UserInterfaceMain {
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String WHITESPACE_PATTERN = "\\s+";
    private static final int taskbarHeight = 40;
    
    /**
     * This operation initialize all the Processes 
     */
    public static void initProcess() {
    	
    	UserIntSwing.frame.pack();
    	setupFrameLocation();
    	addFrameWindowFocusListener();
    	addSystemTrayWindowStateListener();
		formatLabels();
		UserIntSwing.lblHelp.setText(CommandGuide.buildGeneralGuideString());
		UserIntSwing.lblTodayDate.setText(UserInterfaceMain.setTodayDate());
		addTextfieldKeyListener();
    }

    private static void formatLabels() {

        UserIntSwing.lblCommand.setFont(new Font("Tahoma", Font.BOLD, 12));
        UserIntSwing.lblCommandProcess.setFont(new Font("Tahoma", Font.ITALIC,
                11));
        UserIntSwing.lblCommandProcess.setForeground(new Color(255, 0, 0));
        UserIntSwing.lblCommandProcess.setBackground(new Color(255, 204, 255));
        UserIntSwing.lblCommandProcess.setOpaque(true);

        UserIntSwing.lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
        UserIntSwing.lblDateProcess
                .setFont(new Font("Tahoma", Font.ITALIC, 11));
        UserIntSwing.lblDateProcess.setForeground(new Color(0, 128, 0));
        UserIntSwing.lblDateProcess.setBackground(new Color(255, 204, 255));
        UserIntSwing.lblDateProcess.setOpaque(true);

        UserIntSwing.lblPriority.setFont(new Font("Tahoma", Font.BOLD, 12));
        UserIntSwing.lblPriorityProcess.setFont(new Font("Tahoma", Font.ITALIC,
                11));
        UserIntSwing.lblPriorityProcess.setBackground(new Color(255, 204, 255));
        UserIntSwing.lblPriorityProcess.setOpaque(true);

        UserIntSwing.lblDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
        UserIntSwing.lblDescriptionProcess.setFont(new Font("Tahoma",
                Font.ITALIC, 11));
        UserIntSwing.lblDescriptionProcess.setBackground(new Color(255, 204,
                255));
        UserIntSwing.lblDescriptionProcess.setOpaque(true);
        
		FeedbackGuide.formatFeedbackLabel();
		CommandGuide.fomatCommandGuideLabel();
    }

    /**
     * This operation puts the focus on the textField for the user to type
     * immediately when the program runs
     */
    private static void addFrameWindowFocusListener() {

        UserIntSwing.frame.addWindowFocusListener(new WindowFocusListener() {
            public void windowGainedFocus(WindowEvent arg0) {

                UserIntSwing.textField.requestFocusInWindow();
            }
            
            public void windowLostFocus(WindowEvent arg0) {
            }
        });
    }
    
    /**
     * This operation process the SystemTray when minimise
     * operation is executed
     */
    private static void addSystemTrayWindowStateListener() {
    	UserIntSwing.frame.addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent arg) {
				MinimiseToTray.Minimise(arg);
			}
		});
    }
    
	/**
	 *Textfield KeyListener
	 *1. Set the Command guide Label to the indiviual command guide that the user input
	 *2. Process all the HotKeys Functions
	 *3. Process the User Typed History
	 *4. Enter KeyListener - Process all the feedback labels when the user type 
	 *an incorrect input
	 */
    private static void addTextfieldKeyListener() {
    	
		UserIntSwing.textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg1) {
				String userInput = UserIntSwing.textField.getText();
				try {
					processTextfield(arg1, userInput);
					
					if(arg1.getKeyCode() == VK.enter()){
						processEnterkey(arg1);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg1) {
				DynamicParseResult parseResult = 
						UserInterfaceMain.processUserParse(arg1, UserIntSwing.logicManager);
				Task task = parseResult.getTask();
				clearDynamicParseLabels();
		        handleDynamicEdit(parseResult, task);
		        showParseResult(parseResult, task);

			}
            private void handleDynamicEdit(DynamicParseResult parseResult,
                    Task task) {
                if (containsValidEditCommand(parseResult)) 
		        {
		            String indexString = getIndexString(task);
		            int index = getTaskToBeEditedIndex(indexString);
		            Task taskToBeEdited = UserIntSwing.logicManager.getTaskToBeEdited(index);
		            if(taskToBeEdited != null)
		            {
		                task.setDescription(StringHandler.removeFirstMatched(
		                        task.getDescription(), indexString));
		                showTaskToBeEdited(taskToBeEdited);
		                UserIntSwing.interForm.highLightRow(index);
		            }
		            else
		            {
		                showInvalidIndexMessage(task);
		            }
		        }
            }
            private void showInvalidIndexMessage(Task task) {
                final String INVALID_INDEX = "The index you are editing is INVALID";
                task.setDescription(INVALID_INDEX);
            }
		});
    }
    
	/**
	 *Textfield processes
	 *@param arg1 KeyEvent from the textfield
	 *@param userInput Input that the user entered from the textfield
	 * @throws InvalidCommandException 
	 */
    private static void processTextfield(KeyEvent arg1, String userInput)
    		throws InvalidCommandException {

		UserIntSwing.lblHelp.setText(CommandGuide.getGuideMessage(userInput));
		UserIntSwing.frame.setVisible(true);
		
		TextfieldHistory.showTextfieldHistory(arg1);

		UserInterfaceMain.processHotKeys(arg1);
    }
    
	/**
	 *Enter Key Listener process
	 *@param arg1 KeyEvent Enter from the textfield
	 */
    private static void processEnterkey(KeyEvent arg1) {
    	String getText = UserIntSwing.textField.getText();
		
		UserIntSwing.lblFeedback.setText(
				UserInterfaceMain.processFeedbackLabel(getText));
		TextfieldHistory.getTextfieldString(getText);
    }

    /**
     * This operation sets the date for today and display on the top of the
     * application
     */
    private static String setTodayDate() {

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String date = sdf.format(new Date());
        String dateDisplay = "You are viewing: " + date;

        return dateDisplay;
    }

    /**
     * This operation sets the program at the bottom right hand corner of screen
     */
    private static void setupFrameLocation() {

        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int Xcoordinate = (int) rect.getMaxX() - UserIntSwing.frame.getWidth();
        int Ycoordinate = (int) rect.getMaxY() - UserIntSwing.frame.getHeight() 
        		- taskbarHeight;
        UserIntSwing.frame.setLocation(Xcoordinate, Ycoordinate);
    }

    /**
     * This operation process the hotkeys shortcut function
     * @param key KeyEvent keylistener from the textfield
     * @throws InvalidCommandException 
     */
    private static void processHotKeys(KeyEvent key) throws InvalidCommandException {

        if (key.getKeyCode() == VK.help()) {
            HelpMenu.main(null);
        }
        if (UserIntSwing.textField.getText().isEmpty()) {
            if (key.getKeyCode() == VK.add()) {
            	ProcessHotkey.add();
            } else if (key.getKeyCode() == VK.view()) {
            	ProcessHotkey.view();
            } else if (key.getKeyCode() == VK.edit()) {
            	ProcessHotkey.edit();
            } else if (key.getKeyCode() == VK.delete()) {
            	ProcessHotkey.delete();
            } else if (key.getKeyCode() == VK.search()) {
            	ProcessHotkey.search();
            } else if (key.getKeyCode() == VK.undo()) {
            	ProcessHotkey.undo();
            } else if (key.getKeyCode() == VK.redo()) {
            	ProcessHotkey.redo();
            }
        }
    }

    /**
     * This operation process the Feedback Label
     * @param getText gets the text from what the user input
     */
    private static String processFeedbackLabel(String getText) {

        // text = text.trim().replaceAll("\\s+", "");
        if (getText.isEmpty() || getText.matches(" ")) {
        	feedbackTimerReset();
            return FeedbackGuide.isEmptyString();
        }

        getText = getText.toLowerCase();
        String[] tokens = getText.split(WHITESPACE_PATTERN);
        Action action = Keywords.resolveActionIdentifier(tokens[0]);

        switch (action) {
        case ADD:
        case VIEW:
        case EDIT:
        case DELETE:
        case SEARCH:
        case UNDO:
        case REDO:
        	feedbackTimerReset();
            return FeedbackGuide.isValidString();
        default:
        	feedbackTimerReset();
            return FeedbackGuide.isInvalidString();
        }
    }

    /**
     * This operation process the timer to clear the Warning Label. It is set at
     * 1000 milli-seconds.
     */
    public static void feedbackTimerReset() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                UserIntSwing.lblFeedback.setText("");
            }
        }, 1000);
    }

    /**
     * This operation process the labels that the user input from the textfield
     * and show what will be parsed
     * 
     * @param logicManager
     * @return DynamicParseResult the result that was parsed on run time
     */
    public static DynamicParseResult processUserParse(KeyEvent arg1, LogicManager logicManager) {

        DynamicParseResult parseResult = logicManager
                .dynamicParse(UserIntSwing.textField.getText());

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
    public static void showParseResult(DynamicParseResult parseResult,
            Task task) {
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
        String indexString = StringHandler.getIntegerFromFirstSlot(task
                .getDescription());
        return indexString;
    }

    /**
     * Help determines if the parse result contains valid edit command
     * @param parseResult the parse result
     * @return if it contains valid edit command
     */
    public static boolean containsValidEditCommand(
            DynamicParseResult parseResult) {
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
     * convert string to integer
     * @param indexString the string which contains the index to extract
     * @return the index in integer form
     */
    public static int getTaskToBeEditedIndex(String indexString) {
        final int ARRAY_OFFSET = -1;
        return StringHandler.parseStringToInteger(indexString) + ARRAY_OFFSET;
    }

    /**
     * This operation process the priority label Red: High; Blue: Medium; Green:
     * Low
     */
    private static void processLblPriority() {

        if (UserIntSwing.lblPriorityProcess.getText().matches("High")) {
            UserIntSwing.lblPriorityProcess.setForeground(Color.red);
        } else if (UserIntSwing.lblPriorityProcess.getText().matches("Low")) {
            UserIntSwing.lblPriorityProcess.setForeground(Color.green);
        } else {
            UserIntSwing.lblPriorityProcess.setForeground(Color.blue);
        }
    }
}
