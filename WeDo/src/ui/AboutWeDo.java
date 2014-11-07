package ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Toolkit;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

/**
 // @author A0112636M
  * This window shows the authors of WeDo Task Manager and
  * the acknowledements 
 */
public class AboutWeDo {

	private JDialog frame;
	private static final int Xcoordinate = 5;
	private static final int Ycoordinate = 5;
	private static final String TAG_WRAP_STRING = "%s%s%s";
	private static final String HTML_OPEN = "<html>";
	private static final String HTML_CLOSE = "</html>";
	private static final String HTML_BREAK = "<br>";

	/**
	 * Initialize the contents of the frame.
	 */
	private AboutWeDo() {
		frame = new JDialog();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AboutWeDo.class.getResource("/ui/icon/Wedo_logo.png")));
		frame.setBounds(100, 100, 334, 300);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocation(Xcoordinate, Ycoordinate);
		
		JLabel lblAbout = new JLabel(buildAboutMessage());
		lblAbout.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAbout.setVerticalAlignment(SwingConstants.TOP);
		lblAbout.setBounds(10, 11, 308, 249);
		frame.getContentPane().add(lblAbout);
		
		JButton btnClose = new JButton("Okay");
		btnClose.setBounds(229, 237, 89, 23);
		frame.getContentPane().add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		JLabel lblIcon = new JLabel("lblIcon");
		lblIcon.setIcon(new ImageIcon(AboutWeDo.class.getResource("/ui/icon/Wedo_logo.png")));
		lblIcon.setBounds(199, 11, 103, 98);
		frame.getContentPane().add(lblIcon);
	}
	
	/**
	 *This operation returns message the "About WeDo" menu item
	 */
	private static String buildAboutMessage() {
		StringBuilder message = new StringBuilder();

		message.append("<b> About WeDo Task Manager: </b>" + HTML_BREAK);
		message.append("(CS2103T   T17/C05   t17-2j)" + HTML_BREAK);
		message.append("Team Members:" + HTML_BREAK);
		message.append("Andy Hsu Wei Qiang" + HTML_BREAK);
		message.append("Kuan Tien Long" + HTML_BREAK);
		message.append("Wai Min" + HTML_BREAK);
		message.append("Sitti Maryam Binte Rashid Ridza" + HTML_BREAK);
		message.append(HTML_BREAK + HTML_BREAK);
		message.append("<b> Acknowledements: </b>" + HTML_BREAK);
		message.append("BalloonTip dependency - https://balloontip.java.net/");

		return wrapWithHtmlTag(message.toString());
	}
	
	/**
	 * @param text String to be wrapped in HTML
	 * @return String wrapped with HTML format
	 */
	private static String wrapWithHtmlTag(String text) {
		return String.format(TAG_WRAP_STRING, HTML_OPEN, text, HTML_CLOSE);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutWeDo window = new AboutWeDo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
