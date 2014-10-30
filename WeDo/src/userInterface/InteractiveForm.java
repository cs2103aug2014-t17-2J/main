package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import logic.utility.Task;

public class InteractiveForm extends JPanel {

	public static final String[] columnNames = { "ID", "description",
			"start date", "end date", "start time", "end time", "priority",
			"done", "" };

	protected JTable table;
	protected JScrollPane scroller;
	protected InteractiveTableModel tableModel;

	static int hi = 1;

	public InteractiveForm() {
		initComponent();
	}

	public void updateTable(ArrayList<Task> taskList) {
		tableModel.updateTable(taskList);

		// for wrap text
		table.getColumnModel()
				.getColumn(InteractiveTableModel.INDEX_DESCRIPTION)
				.setCellRenderer(new LineWrapCellRenderer());
	}

	public void initComponent() {
		System.out.println("init count " + hi++);
		tableModel = new InteractiveTableModel(columnNames);
		tableModel
				.addTableModelListener(new InteractiveForm.InteractiveTableModelListener());

		table = new JTable();
		table.setModel(tableModel);
		table.setOpaque(true);
		table.setVisible(true);
		table.setRowSelectionAllowed(true);
		table.setSurrendersFocusOnKeystroke(true);

		if (!tableModel.hasEmptyRow()) {
			tableModel.addEmptyRow();
		}

		scroller = new javax.swing.JScrollPane(table);
		table.setPreferredScrollableViewportSize(new java.awt.Dimension(800,
				300));

		TableColumn taskCol = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_TASK);

		TableColumn descriptionCol = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_DESCRIPTION);

		TableColumn startDateCol = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_STARTDATE);

		TableColumn endDateCol = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_ENDDATE);

		TableColumn startTimeCol = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_STARTTIME);

		TableColumn endTimeCol = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_ENDTIME);

		TableColumn priorityCol = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_PRIORITY);

		TableColumn checkCol = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_CHECK);

		TableColumn hidden = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_HIDDEN);


		taskCol.setMinWidth(5);
		taskCol.setPreferredWidth(10);
		taskCol.setCellRenderer(new InteractiveRenderer(
				InteractiveTableModel.INDEX_TASK));

		hidden.setMinWidth(2);
		hidden.setPreferredWidth(2);
		hidden.setMaxWidth(2);
		hidden.setCellRenderer(new InteractiveRenderer(
				InteractiveTableModel.INDEX_HIDDEN));

		descriptionCol.setCellRenderer(new InteractiveRenderer(
				InteractiveTableModel.INDEX_DESCRIPTION));

		startDateCol.setCellRenderer(new InteractiveRenderer(
				InteractiveTableModel.INDEX_STARTDATE));

		endDateCol.setCellRenderer(new InteractiveRenderer(
				InteractiveTableModel.INDEX_ENDTIME));

		startTimeCol.setCellRenderer(new InteractiveRenderer(
				InteractiveTableModel.INDEX_STARTTIME));

		endTimeCol.setCellRenderer(new InteractiveRenderer(
				InteractiveTableModel.INDEX_ENDTIME));

		priorityCol.setCellRenderer(new InteractiveRenderer(
				InteractiveTableModel.INDEX_PRIORITY));

		//checkCol.setCellRenderer(new InteractiveRenderer(
		//		InteractiveTableModel.INDEX_CHECK));
		//checkCol.setCellRenderer(new CheckBoxRenderer());

		setLayout(new BorderLayout());
		add(scroller, BorderLayout.CENTER);

	}

	public void highlightLastRow(int row) {
		int lastrow = tableModel.getRowCount();
		if (row == lastrow - 1) {
			table.setRowSelectionInterval(lastrow - 1, lastrow - 1);
		} else {
			table.setRowSelectionInterval(row + 1, row + 1);
		}
		table.setColumnSelectionInterval(0, 0);
	}

	public void selectRow(int row) {
		highLightRow(row);
		scrollToRow(row);
	}

	public void highLightRow(int row) {
		table.setRowSelectionInterval(row, row);
		table.setColumnSelectionInterval(0, 0);
	}

	public void scrollToRow(int row) {
		table.scrollRectToVisible(new Rectangle(table.getCellRect(row, 0, true)));
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

			this.setOpaque(true);

			// set alternating row colour
			if (!table.isRowSelected(row)) {
				table.setOpaque(true);
				c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
			}

			// set default row colour
			Color originalColour = c.getBackground();

			// highlight completed tasks
			// if ((boolean) InteractiveForm.this.tableModel.getValueAt(row,
			// InteractiveTableModel.INDEX_CHECK)) {
			// if (!table.isRowSelected(row)) {
			// c.setBackground(Color.GREEN);
			// }
			// }

			// this is to highlight priority level
			if (!table.isRowSelected(row)) {
				if (InteractiveForm.this.tableModel.getValueAt(row,
						InteractiveTableModel.INDEX_PRIORITY).equals("High")) {

					this.setOpaque(true);
				//	c.setBackground(Color.RED);
					c.setBackground(Color.getHSBColor(325, 100, 100));
				} else if (InteractiveForm.this.tableModel.getValueAt(row,
						InteractiveTableModel.INDEX_PRIORITY).equals("Low")) {

					this.setOpaque(true);
					c.setBackground(Color.CYAN);
				}
			}

			else {
				this.setOpaque(true);
				c.setBackground(originalColour);
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

		public Component getPrepareRenderer(TableCellRenderer renderer,
				int row, int column) {

			Component c = getPrepareRenderer(renderer, row, column);
			Color originalColour = c.getBackground();

			return c;
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
				table.setOpaque(true);
				table.setVisible(true);
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
			frame.setVisible(true); // does not make a difference
			frame.setOpacity(1.0f);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ALERT MESSAGE", "TITLE",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}

		table.setSurrendersFocusOnKeystroke(true);
	}
}