package ui;

import java.awt.event.KeyEvent;
import java.util.Stack;

import ui.guide.FeedbackGuide;
import userInterface.UserIntSwing;

public class TextfieldHistory{
	private static final Stack<String> textStack = new Stack<String>();
	private static final Stack<String> undoStack = new Stack<String>();
	private static String text;
	
	public static void getTextfieldString(String getText){
		
		while(!undoStack.isEmpty()){
			textStack.push(undoStack.pop());
		}
		textStack.push(getText);
		for(int j=0; j<textStack.size(); j++)
			System.out.println(textStack.get(j));
	}
	
	public static void showTextfieldHistoryUpkey(KeyEvent arg1){
		if(arg1.getKeyCode() == KeyEvent.VK_UP){
			if(!textStack.isEmpty()){
				text = textStack.pop();
				undoStack.push(text);
				UserIntSwing.textField.setText(text);
			}
			else{
				UserIntSwing.lblFeedback.setText(FeedbackGuide.isEmptyHistoryString());
				UserInterfaceMain.feedbackTimerReset();
			}
		}
		else if(arg1.getKeyCode() == KeyEvent.VK_DOWN){
			if(!undoStack.isEmpty()){
				text = undoStack.pop();
				textStack.push(text);
				UserIntSwing.textField.setText(text);
				//System.out.println("field history counter down: " + i);
			}
			else{
				UserIntSwing.lblFeedback.setText(FeedbackGuide.isEmptyInput());
				UserInterfaceMain.feedbackTimerReset();
			}
		}
	}
	
}
