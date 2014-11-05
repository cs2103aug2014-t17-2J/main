package ui.logic.command;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;

import userInterface.UserIntSwing;

/**
 * @author Andy Hsu Wei Qiang
 *This class format all the Swing design of the application
 */
public class FormatHandler {
	
	public static void format() {
		UserIntSwing.lblCommand.setFont(new Font("Tahoma", Font.BOLD, 12));
		UserIntSwing.lblCommandProcess.setFont(new Font("Tahoma", Font.ITALIC,11));
		UserIntSwing.lblCommandProcess.setForeground(new Color(255, 0, 0));
		UserIntSwing.lblCommandProcess.setBackground(new Color(255, 204, 255));
		UserIntSwing.lblCommandProcess.setOpaque(true);

		UserIntSwing.lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		UserIntSwing.lblDateProcess.setFont(new Font("Tahoma", Font.ITALIC, 11));
		UserIntSwing.lblDateProcess.setForeground(new Color(0, 128, 0));
		UserIntSwing.lblDateProcess.setBackground(new Color(255, 204, 255));
		UserIntSwing.lblDateProcess.setOpaque(true);

		UserIntSwing.lblPriority.setFont(new Font("Tahoma", Font.BOLD, 12));
		UserIntSwing.lblPriorityProcess.setFont(new Font("Tahoma", Font.ITALIC, 11));
		UserIntSwing.lblPriorityProcess.setBackground(new Color(255, 204, 255));
		UserIntSwing.lblPriorityProcess.setHorizontalAlignment(SwingConstants.CENTER);
		UserIntSwing.lblPriorityProcess.setOpaque(true);

		UserIntSwing.lblDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		UserIntSwing.lblDescriptionProcess.setFont(new Font("Tahoma", Font.ITALIC, 11));
		UserIntSwing.lblDescriptionProcess.setBackground(new Color(255, 204, 255));
		UserIntSwing.lblDescriptionProcess.setOpaque(true);
		
		UserIntSwing.btnHelp.setForeground(new Color(0, 0, 0));
		UserIntSwing.btnHelp.setBackground(new Color(255, 153, 255));
		UserIntSwing.btnAdd.setForeground(new Color(0, 0, 0));
		UserIntSwing.btnAdd.setBackground(new Color(255, 153, 255));
		UserIntSwing.btnView.setForeground(new Color(0, 0, 0));
		UserIntSwing.btnView.setBackground(new Color(255, 153, 255));
		UserIntSwing.btnEdit.setForeground(new Color(0, 0, 0));
		UserIntSwing.btnEdit.setBackground(new Color(255, 153, 255));
		UserIntSwing.btnDel.setForeground(new Color(0, 0, 0));
		UserIntSwing.btnDel.setBackground(new Color(255, 153, 255));
		UserIntSwing.btnSearch.setForeground(new Color(0, 0, 0));
		UserIntSwing.btnSearch.setBackground(new Color(255, 153, 255));
		
		UserIntSwing.lblTodayDate.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		UserIntSwing.lblTodayDate.setHorizontalAlignment(SwingConstants.RIGHT);
		UserIntSwing.lblTodayDate.setVerticalAlignment(SwingConstants.TOP);
		
		UserIntSwing.lblViewTask.setFont(new Font("Calibri", Font.ITALIC, 18));
		UserIntSwing.lblViewTask.setHorizontalAlignment(SwingConstants.LEFT);
		UserIntSwing.lblViewTask.setVerticalAlignment(SwingConstants.TOP);
		
		UserIntSwing.lblHelp.setVerticalAlignment(SwingConstants.TOP);
		UserIntSwing.textField.setColumns(10);
		UserIntSwing.btnEnter.setBackground(new Color(255, 153, 255));
		
		formatFeedbackLabel();
		fomatCommandGuideLabel();
	}
	
	public static void formatFeedbackLabel() {
        UserIntSwing.lblFeedback.setFont(new Font("Arial", Font.ITALIC, 14));
        UserIntSwing.lblFeedback.setForeground(Color.red);
        UserIntSwing.lblFeedback.setOpaque(false);
	}
	
	/**
	 * Format the Command Guide Label
	 */
	public static void fomatCommandGuideLabel(){
		UserIntSwing.lblQuickHelp.setFont(new Font("Times New Roman", Font.BOLD
				| Font.ITALIC, 14));
	}
}
