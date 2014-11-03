package userInterface;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import logic.utility.Task;

@SuppressWarnings("serial")
public class InteractiveTableModel extends AbstractTableModel {

	public static final int INDEX_TASK = 0;
	public static final int INDEX_DESCRIPTION = 1;
	public static final int INDEX_STARTDATE = 2;
	public static final int INDEX_ENDDATE = 3;
	public static final int INDEX_STARTTIME = 4;
	public static final int INDEX_ENDTIME = 5;
	public static final int INDEX_PRIORITY = 6;
	public static final int INDEX_CHECK = 7;
	public static final int INDEX_HIDDEN = 8;

	protected String[] columnNames;
	protected Vector dataVector;

	public InteractiveTableModel(String[] columnNames) {
		this.columnNames = columnNames;
		dataVector = new Vector();
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	public boolean isCellEditable(int row, int column) {
		if (column == INDEX_CHECK)
			return false;
		else
			return false;
	}

	public Class getColumnClass(int column) {
		switch (column) {
		case INDEX_TASK:
		case INDEX_DESCRIPTION:
		case INDEX_STARTDATE:
		case INDEX_ENDDATE:
		case INDEX_STARTTIME:
		case INDEX_ENDTIME:
		case INDEX_PRIORITY:
			return String.class;
		case INDEX_CHECK:
			return Boolean.class;
		default:
			return Object.class;
		}
	}

	public Object getValueAt(int row, int column) {
		TableInformation tableInfo = (TableInformation) dataVector.get(row);
		switch (column) {

		case INDEX_TASK:
			return tableInfo.getTask();
		case INDEX_DESCRIPTION:
			return tableInfo.getDescription();
		case INDEX_STARTDATE:
			return tableInfo.getStartDate();
		case INDEX_ENDDATE:
			return tableInfo.getEndDate();
		case INDEX_STARTTIME:
			return tableInfo.getStartTime();
		case INDEX_ENDTIME:
			return tableInfo.getEndTime();
		case INDEX_PRIORITY:
			return tableInfo.getPriority();
		case INDEX_CHECK:
			return tableInfo.getCheck();
		default:
			return new Object();
		}
	}

	public void setValueAt(Object value, int row, int column) {
		TableInformation tableInfo = (TableInformation) dataVector.get(row);
		switch (column) {
		case INDEX_TASK:
			tableInfo.setTask((String) value);
			break;
		case INDEX_DESCRIPTION:
			tableInfo.setDescription((String) value);
			break;
		case INDEX_STARTDATE:
			tableInfo.setStartDate((String) value);
			break;
		case INDEX_ENDDATE:
			tableInfo.setEndDate((String) value);
			break;
		case INDEX_STARTTIME:
			tableInfo.setStartTime((String) value);
			break;
		case INDEX_ENDTIME:
			tableInfo.setEndTime((String) value);
			break;
		case INDEX_PRIORITY:
			tableInfo.setPriority((String) value);
			break;
		case INDEX_CHECK:
			tableInfo.setCheck((Boolean) (value));
			break;
		default:
			System.out.println("invalid index");
		}
		fireTableCellUpdated(row, column);
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return dataVector.size();
	}

	public boolean hasEmptyRow() {
		if (dataVector.size() == 0)
			return false;
		TableInformation tableInfo = (TableInformation) dataVector
				.get(dataVector.size() - 1);
		if (tableInfo.getTask().trim().equals("")
				&& tableInfo.getDescription().trim().equals("")
				&& tableInfo.getStartDate().trim().equals("")
				&& tableInfo.getEndDate().trim().equals("")
				&& tableInfo.getStartTime().trim().equals("")
				&& tableInfo.getEndTime().trim().equals("")
				&& tableInfo.getPriority().trim().equals("")
				&& tableInfo.getCheck().FALSE) {
			return true;
		} else
			return false;
	}

	public void addEmptyRow() {
		dataVector.add(new TableInformation());
		fireTableRowsInserted(dataVector.size() - 1, dataVector.size() - 1);
	}

	/**
	 * This method populates the table with values from the data storage.
	 * 
	 * @param taskList
	 */
	public void updateTable(ArrayList<Task> taskList) {
		int row = 0;

		DateTimeFormatter dateFormatter = DateTimeFormatter
				.ofPattern("dd/MM/YYYY");
		DateTimeFormatter timeFormatter = DateTimeFormatter
				.ofPattern("hh:mm a");

		clearRows();

		for (Task task : taskList) {
			if (!this.hasEmptyRow()) {
				this.addEmptyRow();
			}

			this.setValueAt("" + (row + 1), row, INDEX_TASK);

			this.setValueAt(task.getDescription(), row, INDEX_DESCRIPTION);

			if (!task.getStartDate().equals(null)
					&& !task.getStartDate().equals(LocalDate.MAX)) {
				this.setValueAt(task.getStartDate().format(dateFormatter), row,
						INDEX_STARTDATE);
			}
			if (!task.getEndDate().equals(null)
					&& !task.getEndDate().equals(LocalDate.MAX)) {
				this.setValueAt(task.getEndDate().format(dateFormatter), row,
						INDEX_ENDDATE);
			}
			if (!task.getStartTime().equals(null)
					&& !task.getStartTime().equals(LocalTime.MAX)) {
				this.setValueAt(task.getStartTime().format(timeFormatter), row,
						INDEX_STARTTIME);
			}
			if (!task.getEndTime().equals(null)
					&& !task.getEndTime().equals(LocalTime.MAX)) {
				this.setValueAt(task.getEndTime().format(timeFormatter), row,
						INDEX_ENDTIME);
			}
			if (task.getPriority() != null
					&& !task.getPriority().toString().isEmpty()) {
				this.setValueAt(task.getPriority().toString(), row,
						INDEX_PRIORITY);
			}
			if (task.getCompleted() == true) {
				this.setValueAt(true, row, INDEX_CHECK);
			}
			row++;
		}
	}

	public void clearRows() {
		dataVector.clear();
		// this.addEmptyRow();
	}
}
