package userInterface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import dataStorage.FileHandler;
import logic.exception.InvalidCommandException;
import logic.utility.Task;
import edu.emory.mathcs.backport.java.util.Arrays;

//@author A0112675H
@SuppressWarnings("serial")
public class InteractiveForm extends JPanel {

	public static final String[] columnNames = { "ID", "description",
			"start date", "end date", "start time", "end time", "priority",
			"done", "" };

	private static final int COLUMN_WIDTH_TASK = 30;
	private static final int COLUMN_WIDTH_DESCRIPTION = 200;
	private static final int COLUMN_WIDTH_PRIORITY = 70;
	private static final int COLUMN_WIDTH_DONE = 40;
	private static final int COLUMN_WIDTH_STANDARD = 80;

	protected JTable table;
	protected JScrollPane scroller;
	protected InteractiveTableModel tableModel;

	public InteractiveForm() {
		initComponent();
	}

	public void updateTable(ArrayList<Task> taskList) {

		tableModel.updateTable(taskList);
	}

	/**
	 * table settings
	 */
	public void initComponent() {

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

		table.setDefaultRenderer(Object.class, new TableDefaultRenderer());

		table.setPreferredScrollableViewportSize(new java.awt.Dimension(800,
				300));

		TableColumn hidden = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_HIDDEN);

		hidden.setMinWidth(1);
		hidden.setPreferredWidth(1);
		hidden.setMaxWidth(1);
		hidden.setCellRenderer(new InteractiveRenderer(
				InteractiveTableModel.INDEX_HIDDEN));

		TableColumn taskID = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_TASK);
		TableColumn description = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_DESCRIPTION);
		TableColumn startDate = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_STARTDATE);
		TableColumn endDate = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_STARTDATE);
		TableColumn startTime = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_STARTTIME);
		TableColumn endTime = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_ENDTIME);
		TableColumn priority = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_PRIORITY);
		TableColumn done = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_CHECK);

		taskID.setMinWidth(COLUMN_WIDTH_TASK);
		taskID.setPreferredWidth(COLUMN_WIDTH_TASK);
		taskID.setMaxWidth(COLUMN_WIDTH_TASK);

		description.setMinWidth(COLUMN_WIDTH_DESCRIPTION);
		description.setPreferredWidth(COLUMN_WIDTH_DESCRIPTION);
		description.setCellRenderer(new LineWrapCellRenderer(tableModel));

		startTime.setPreferredWidth(COLUMN_WIDTH_STANDARD);
		startTime.setMinWidth(COLUMN_WIDTH_STANDARD);

		endTime.setPreferredWidth(COLUMN_WIDTH_STANDARD);
		endTime.setMinWidth(COLUMN_WIDTH_STANDARD);

		startDate.setPreferredWidth(COLUMN_WIDTH_STANDARD);
		startDate.setMinWidth(COLUMN_WIDTH_STANDARD);

		endDate.setPreferredWidth(COLUMN_WIDTH_STANDARD);
		endDate.setMinWidth(COLUMN_WIDTH_STANDARD);

		priority.setMaxWidth(COLUMN_WIDTH_PRIORITY);
		priority.setPreferredWidth(COLUMN_WIDTH_PRIORITY);

		done.setMaxWidth(COLUMN_WIDTH_DONE);
		done.setPreferredWidth(COLUMN_WIDTH_DONE);
		done.setCellRenderer(new BooleanCellRenderer());

		setLayout(new BorderLayout());
		add(scroller, BorderLayout.CENTER);

		setNoHighLightSelectionColor();

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				JTable table = (JTable) me.getSource();
				Point p = me.getPoint();
				int row = table.rowAtPoint(p);
				int column = table.columnAtPoint(p);
				if (column == InteractiveTableModel.INDEX_CHECK) {

					boolean isComplete = (boolean) tableModel.getValueAt(row,
							column);
					try {
						UserIntSwing.logicManager.setComplete(row + 1,
								isComplete);
					} catch (InvalidCommandException error) {
						// log to file
						FileHandler
								.log("MouseListener error: unable to set as done");
					}
				}
			}
		});

	}

	public void highlightLastRow(int row) {

		assert (tableModel != null) : "tableModel should not be null at highlightLastRow";

		int rowCount = tableModel.getRowCount();
		int lastRow;

		if (row == rowCount - 1) {
			lastRow = rowCount - 1;
		} else {
			lastRow = row + 1;
		}
		highLightRow(lastRow);
		scrollToRow(lastRow);
	}

	public void highlightLastRow() {
		assert (tableModel != null) : "tableModel should not be null at highlightLastRow";
		int rowCount = tableModel.getRowCount();
		final int ROW_OFFSET = 1;
		int lastRow = rowCount - ROW_OFFSET;
		highLightRow(lastRow);
		scrollToRow(lastRow);
	}

	/**
	 * <p>
	 * Select and scroll to the row you specified, the row will be at the top of
	 * the list if scrollable
	 * </p>
	 * This function first select the last row, and move up to the row specified
	 * 
	 * @param row
	 */
	public void selectRow(int row) {
		if (!isRowVisible(table, scroller, row)) {
			selectLastRow();
		}
		highLightRow(row);
		scrollToRow(row);
	}

	private void highLightRow(int row) {
		table.setRowSelectionInterval(row, row);
		table.setColumnSelectionInterval(0, 0);
	}

	private void scrollToRow(int row) {
		Rectangle scrollRect = new Rectangle(table.getCellRect(row,
				InteractiveTableModel.INDEX_HIDDEN, true));
		table.scrollRectToVisible(scrollRect);
	}

	private void selectLastRow() {
		assert (tableModel != null) : "tableModel should not be null at highlightLastRow";
		int rowCount = tableModel.getRowCount();
		final int ROW_OFFSET = 1;
		int lastRow = rowCount - ROW_OFFSET;
		highLightRow(lastRow);
		scrollToRow(lastRow);
	}

	public void setNoHighLightSelectionColor() {
		table.setSelectionBackground(table.getBackground());
		table.setSelectionForeground(table.getForeground());
	}

	public boolean isRowVisible(JTable table, JScrollPane scroller, int rowIndex) {

		JViewport viewport = scroller.getViewport();
		Rectangle rect = table.getCellRect(rowIndex, 1, true);
		return viewport.contains(rect.getLocation());

	}

	public class BooleanCellRenderer extends JCheckBox implements
			TableCellRenderer {

		public BooleanCellRenderer() {
			setLayout(new GridBagLayout());
			setMargin(new Insets(0, 0, 0, 0));
			setHorizontalAlignment(JLabel.CENTER);
			this.setBorderPainted(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {

			if (value instanceof Boolean) {
				setSelected((Boolean) value);
			}

			DefaultWeDoTableColor
					.setDefaultBackGroundColour(this, row, column, table
							.getSelectedRow(), tableModel.getValueAt(row,
							InteractiveTableModel.INDEX_PRIORITY), tableModel
							.getValueAt(row, InteractiveTableModel.INDEX_CHECK));

			return this;
		}

	}

	public class TableDefaultRenderer extends DefaultWeDoTableRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {

			setHorizontalAlignment(JLabel.CENTER);

			return super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column, tableModel);
		}
	}

	class InteractiveRenderer extends DefaultWeDoTableRenderer {

		protected int interactiveColumn;

		public InteractiveRenderer(int interactiveColumn) {
			this.interactiveColumn = interactiveColumn;
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {

			Component c = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column, tableModel);

			return c;
		}
	}

	public class CellSelectionListener implements ListSelectionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.event.ListSelectionListener#valueChanged(javax.swing.
		 * event.ListSelectionEvent)
		 */
		@Override
		public void valueChanged(ListSelectionEvent event) {

			if (event.getValueIsAdjusting())
				return;

			Object selectedData = null;

			int[] selectedRow = table.getSelectedRows();
			int[] selectedColumns = table.getSelectedColumns();

			for (int i = 0; i < selectedRow.length; i++) {
				for (int j = 0; j < selectedColumns.length; j++) {
					System.out.println("NO rows is "
							+ Arrays.toString(selectedRow));
					System.out.println("NO cols is"
							+ Arrays.toString(selectedColumns));

					if (selectedColumns[j] == InteractiveTableModel.INDEX_CHECK) {
						System.out.println("Inside ... rows is "
								+ Arrays.toString(selectedRow));
						System.out.println("Inside... cols is"
								+ Arrays.toString(selectedColumns));

						if (selectedRow[i] > tableModel.getRowCount())
							return;

						System.out.println("rows is "
								+ Arrays.toString(selectedRow));
						System.out.println("cols is"
								+ Arrays.toString(selectedColumns));

						selectedData = tableModel.getValueAt(selectedRow[i],
								selectedColumns[j]);
						boolean isComplete = (boolean) tableModel.getValueAt(
								selectedRow[i], selectedColumns[j]);
						System.out.println("Selected: " + selectedData);
						try {
							UserIntSwing.logicManager.setComplete(
									selectedRow[i] + 1, isComplete);
						} catch (InvalidCommandException error) {
							// log to file
							FileHandler
									.log("cannot detect change of values in the table");
						}
					}
				}
			}
		}
	}

	public class InteractiveTableModelListener implements TableModelListener {
		public void tableChanged(TableModelEvent evt) {
			if (evt.getType() == TableModelEvent.UPDATE) {
				int column = evt.getColumn();
				int row = evt.getFirstRow();
				System.out.println("row: " + row + " column: " + column);
			}
		}
	}

	/**
	 * Execute the table in the frame.
	 * 
	 * @param frame
	 */
	public void execute(JFrame frame) {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());

			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent evt) {
					System.exit(0);
				}
			});

			this.setBounds(10, 60, 600, 200);

			frame.getContentPane().add(this);
			frame.pack();
			frame.setVisible(true);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"ALERT MESSAGE: cannot execute frame", "TITLE",
					JOptionPane.WARNING_MESSAGE);
			FileHandler.log("application cannot be executed from interForm");
		}

		table.setSurrendersFocusOnKeystroke(true);
	}
}