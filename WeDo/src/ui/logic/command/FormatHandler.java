package ui.logic.command;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;

import ui.guide.CommandGuide;
import ui.guide.FeedbackGuide;
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
		
		UserIntSwing.lblViewTask.setHorizontalAlignment(SwingConstants.RIGHT);
		UserIntSwing.lblViewTask.setFont(new Font("Calibri", Font.ITALIC, 16));

		FeedbackGuide.formatFeedbackLabel();
		CommandGuide.fomatCommandGuideLabel();
	}
}
