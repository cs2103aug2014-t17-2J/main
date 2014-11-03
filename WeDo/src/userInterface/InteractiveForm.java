package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.util.Comparator;
import java.util.Vector;

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

import logic.exception.InvalidCommandException;
import logic.utility.Task;
import edu.emory.mathcs.backport.java.util.Arrays;

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

		// wrap text for description column
		table.getColumnModel()
				.getColumn(InteractiveTableModel.INDEX_DESCRIPTION)
				.setCellRenderer(new LineWrapCellRenderer(tableModel));

		tableModel.updateTable(taskList);
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

		table.setDefaultRenderer(Object.class, new TableDefaultRenderer());
	    
		table.setPreferredScrollableViewportSize(new java.awt.Dimension(800,
				300));
		

		TableColumn hidden = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_HIDDEN);
		TableColumn taskID = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_TASK);
		TableColumn done = table.getColumnModel().getColumn(
				InteractiveTableModel.INDEX_CHECK);

		taskID.setMinWidth(5);
		taskID.setPreferredWidth(5);

		taskID.setCellRenderer(new InteractiveRenderer(
				InteractiveTableModel.INDEX_TASK));

		hidden.setMinWidth(1);
		hidden.setPreferredWidth(1);
		hidden.setMaxWidth(1);
		hidden.setCellRenderer(new InteractiveRenderer(
				InteractiveTableModel.INDEX_HIDDEN));

		done.setCellRenderer(new BooleanCellRenderer());

		setLayout(new BorderLayout());
		add(scroller, BorderLayout.CENTER);

		// ListSelectionModel cellSelectionModel = table.getSelectionModel();
		// cellSelectionModel
		// .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//
		// cellSelectionModel.addListSelectionListener(new
		// CellSelectionListener());

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
						// print invalid... or log..
						error.printStackTrace();
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

	public void setHighLightSelectionColor(Color color) {
		table.setSelectionBackground(color);
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

			// this is to highlight the last row
			// if (column == interactiveColumn && hasFocus) {
			// if ((InteractiveForm.this.tableModel.getRowCount() - 1) == row
			// && !InteractiveForm.this.tableModel.hasEmptyRow()) {
			// InteractiveForm.this.tableModel.addEmptyRow();
			// }
			// highlightLastRow(row);
			// }

			return c;
		}
	}

	// public void setRowSelectionAllowed(boolean rowSelectionAllowed) {
	// int[] selection = table.getSelectedRows();
	// for (int i = 0; i < selection.length; i++) {
	// selection[i] = table.convertRowIndexToModel(selection[i]);
	// }
	// }

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
							// print invalid... or log..
							error.printStackTrace();
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
				System.out.println("\n\n\n\row: " + row + " column: " + column);
				// if(column == InteractiveTableModel.INDEX_CHECK)
				// {
				// boolean isComplete = (boolean) tableModel.getValueAt(row,
				// column);
				// try {
				// UserIntSwing.logicManager.setComplete(row, isComplete);
				// } catch (InvalidCommandException e)
				// {
				// // print invalid... or log..
				// e.printStackTrace();
				// }
				// }

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
			this.setBounds(10, 60, 600, 200);

			frame.getContentPane().add(this);
			frame.pack(); // the window is minimised
			frame.setVisible(true);
			frame.setOpacity(1.0f);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ALERT MESSAGE", "TITLE",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}

		table.setSurrendersFocusOnKeystroke(true);
	}
}