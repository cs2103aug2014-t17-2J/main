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
 * @author Andy Hsu Wei Qiang
 *This window shows the authors of WeDo Task Manager and
 *the acknowledements 
 */
public class AboutWeDo {

	private JDialog frame;

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

	/**
	 * Create the application.
	 */
	public AboutWeDo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AboutWeDo.class.getResource("/ui/icon/WeDo.png")));
		frame.setBounds(100, 100, 334, 300);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel lblAbout = new JLabel("<html><b>About WeDo Task Manager: </b><br/>\r\n(CS2103T   T17/C05   t17-2j) <br/>\r\nTeam Members: <br/>\r\nAndy Hsu Wei Qiang  <br/>\r\nKuan Tien Long  <br/>\r\nWai Min <br/>\r\nSitti Marya Binte Rashid Ridza <br/>\r\n<br/>\r\n<br/>\r\n<b>Acknowledements: </b> <br/>\r\nBalloonTip dependency - https://balloontip.java.net/\r\n</html>");
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
		lblIcon.setIcon(new ImageIcon(AboutWeDo.class.getResource("/ui/icon/WeDo.png")));
		lblIcon.setBounds(199, 11, 103, 98);
		frame.getContentPane().add(lblIcon);
	}
}
