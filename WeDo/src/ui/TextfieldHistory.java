package ui;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ui.guide.FeedbackGuide;
import userInterface.UserIntSwing;

public class TextfieldHistory{
	private static final ArrayList<String> text = new ArrayList<String>();
	private static int i = 1;
	
	public static void getTextfieldString(String getText){
		
		text.add(getText);
		for(int j=0; j<text.size(); j++)
			System.out.println(text.get(j));
	}
	
	public static void showTextfieldHistory(KeyEvent arg1){
		if(arg1.getKeyCode() == KeyEvent.VK_UP ){
			if(!text.isEmpty()){
				UserIntSwing.textField.setText(text.get(text.size()-i));
				i++;
				System.out.println("field history counter: " + i);
			}
			else{
				UserIntSwing.lblFeedback.setText(FeedbackGuide.isEmptyHistoryString());
				UserInterfaceMain.feedbackTimerReset();
			}
		}
	}
}
