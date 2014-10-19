package ui;

import java.awt.Color;

import javax.swing.JLabel;

import userInterface.UserIntSwing;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.BalloonTip.AttachLocation;
import net.java.balloontip.BalloonTip.Orientation;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.EdgedBalloonStyle;

public class BalloonTipGuide {
	public static BalloonTipStyle edgedLook = new EdgedBalloonStyle(Color.WHITE, Color.green);
	public static BalloonTip myBalloonTip = new BalloonTip(UserIntSwing.btnHelp, new JLabel("Press F2 for Add"), edgedLook, 
			Orientation.RIGHT_BELOW, AttachLocation.ALIGNED, 40, 20, false);

	public static void setVisible(boolean b) {
		// TODO Auto-generated method stub
		myBalloonTip.setVisible(true);
		
	}

}
