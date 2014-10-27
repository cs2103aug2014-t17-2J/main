import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TestYo {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestYo window = new TestYo();
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
	public TestYo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(263, 11, 51, 23);
		frame.getContentPane().add(btnEdit);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(109, 11, 51, 23);
		frame.getContentPane().add(btnAdd);

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(164, 11, 89, 23);
		frame.getContentPane().add(btnNewButton_1);

		JPanel panel = new JPanel();
		panel.setBounds(10, 78, 414, 172);
		frame.getContentPane().add(panel);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(new Object[][] {
				{ "First", "Second", "Third", "Fourth" },
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null }, },
				new String[] { "First", "New column", "New column",
						"New column" }));
		//table.setBackground(Color.CYAN);
		int row = table.getRowCount();
		table.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(table);
	}

	class InteractiveRenderer extends DefaultTableCellRenderer {

		protected int interactiveColumn;

		public InteractiveRenderer(int interactiveColumn) {
			this.interactiveColumn = interactiveColumn;
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {

			Component c = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);

			// set alternating row colour
			if (!table.isRowSelected(row)) {
				c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
			}

			return c;
		}
	}
}
