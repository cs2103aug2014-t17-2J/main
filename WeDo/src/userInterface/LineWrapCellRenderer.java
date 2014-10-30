/**
 * @author Maryam
 * This class enables the wrap-text function of the table.
 */

package userInterface;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class LineWrapCellRenderer extends JTextArea implements
		TableCellRenderer {

	public LineWrapCellRenderer() {
		setLineWrap(true);
		setWrapStyleWord(true);
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		setText((String) value);

		setSize(table.getColumnModel().getColumn(column).getWidth(),
				getPreferredSize().height);

		if (table.getRowHeight(row) != getPreferredSize().height) {
			table.setRowHeight(row, getPreferredSize().height);
		}
		
		table.setOpaque(true);

		return this;
	}
}
