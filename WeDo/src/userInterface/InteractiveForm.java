package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import logic.utility.Task;

public class InteractiveForm extends JPanel {

	public static final String[] columnNames = { "ID", "description",
			"start date", "end date", "start time", "end time", "priority",
			"check", "" };

	protected JTable table;
	protected JScrollPane scroller;
	protected InteractiveTableModel tableModel;

	static int hi = 1;

	public InteractiveForm() {
		initComponent();
	}

	public void updateTable(ArrayList<Task> taskList) {
		tableModel.updateTable(taskList);
		// colourPriority();
		// ColumnsAutoSizer.sizeColumnsToFit(table, 3);
		// ColumnsAutoSizer.sizeColumnsToFit(table);
	}

	public void initComponent() {
		System.out.println("init count " + hi++);
		tableModel = new InteractiveTableModel(columnNames);
		tableModel
				.addTableModelListener(new InteractiveForm.InteractiveTableModelListener());
		table = new JTable();
		table.setModel(tableModel);
		table.setSurrendersFocusOnKeystroke(true);
		if (!tableModel.hasEmptyRow()) {
			tableModel.addEmptyRow();
		}

		scroller = new javax.swing.JScrollPane(table);
		table.setPreferredScrollableViewportSize(new java.awt.Dimension(800,
				300));

		TableColumn hidden = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_HIDDEN);
		TableColumn taskID = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_TASK);

		taskID.setMinWidth(5);
		taskID.setPreferredWidth(10);
		taskID.setCellRenderer(new InteractiveRenderer(
				InteractiveTableModel.INDEX_TASK));

		hidden.setMinWidth(2);
		hidden.setPreferredWidth(2);
		hidden.setMaxWidth(2);
		hidden.setCellRenderer(new InteractiveRenderer(
				InteractiveTableModel.INDEX_HIDDEN));

		setLayout(new BorderLayout());
		add(scroller, BorderLayout.CENTER);
	}

	// public void colourPriority() {
	//
	// int row = 0;
	// String s = table.getModel()
	// .getValueAt(row, InteractiveTableModel.INDEX_PRIORITY)
	// .toString();
	//
	// TableColumn priorityCol = table.getColumnModel().getColumn(
	// InteractiveTableModel.INDEX_PRIORITY);
	// if (s.equals("high")) {
	// System.out.println("high five");
	// priorityCol.setCellRenderer(null);
	// tableModel.fireTableRowsUpdated(0, tableModel.getRowCount());
	// }
	//
	// }

	public void highlightLastRow(int row) {
		int lastrow = tableModel.getRowCount();
		if (row == lastrow - 1) {
			table.setRowSelectionInterval(lastrow - 1, lastrow - 1);
		} else {
			table.setRowSelectionInterval(row + 1, row + 1);
		}
		table.setColumnSelectionInterval(0, 0);
	}

	class InteractiveRenderer extends DefaultTableCellRenderer {

		protected int interactiveColumn;

		public InteractiveRenderer(int interactiveColumn) {
			this.interactiveColumn = interactiveColumn;
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {

			this.setOpaque(true);
			Component c = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);

			// this is to highlight the priority
			if (column == interactiveColumn
					&& InteractiveForm.this.tableModel.getValueAt(row,
							tableModel.INDEX_PRIORITY).equals("High")) {
				if (!table.isRowSelected(row)) {
					c.setBackground(Color.ORANGE);
				}
				// tableModel.setRowColour(row, Color.BLACK);
				// c.setBackground(tableModel.getRowColour(row));
			} else {
				if (!table.isRowSelected(row)) {
					c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY
							: Color.WHITE);
				}
				table.setPreferredScrollableViewportSize(table.getPreferredSize());

			}
			// this is to highlight the last row
			if (column == interactiveColumn && hasFocus) {
				if ((InteractiveForm.this.tableModel.getRowCount() - 1) == row
						&& !InteractiveForm.this.tableModel.hasEmptyRow()) {
					InteractiveForm.this.tableModel.addEmptyRow();
				}
				highlightLastRow(row);
			}

			return c;
		}
	}

	public void setRowSelectionAllowed(boolean rowSelectionAllowed) {
		int[] selection = table.getSelectedRows();
		for (int i = 0; i < selection.length; i++) {
			selection[i] = table.convertRowIndexToModel(selection[i]);
		}
	}

	public class InteractiveTableModelListener implements TableModelListener {
		public void tableChanged(TableModelEvent evt) {
			if (evt.getType() == TableModelEvent.UPDATE) {
				int column = evt.getColumn();
				int row = evt.getFirstRow();
				System.out.println("row: " + row + " column: " + column);
				table.setColumnSelectionInterval(column + 1, column + 1);
				table.setRowSelectionInterval(row, row);
			}
		}
	}

	public void execute(JFrame frame) {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());

			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent evt) {
					System.exit(0);
				}
			});
			// frame.getContentPane().add(new InteractiveForm());
			// set the position of the table (10,60) and the size of the table
			// (560,200)
			// this.setBounds(10, 60, 660, 200);
			this.setBounds(10, 60, 600, 200);

			frame.getContentPane().add(this);
			frame.pack(); // the window is minimised
			// frame.setVisible(false); //does not make a difference
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ALERT MESSAGE", "TITLE",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}

		table.setSurrendersFocusOnKeystroke(true);
	}
}