/**
 * 
 */
package userInterface;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import definedEnumeration.Priority;

@SuppressWarnings("serial")
public class DefaultWeDoTableRenderer extends DefaultTableCellRenderer {

    /**
     * This table does the default rendering for WeDo
     * 
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @param tableModel
     * @return component
     */
    protected Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row,
            int column, InteractiveTableModel tableModel) {

        Component component = super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);

        this.setOpaque(true);

        // set alternating row colour
        if (!table.isRowSelected(row)) {
            DefaultWeDoTableColor.setDefaultBackGroundColour(component, row);
        }

        // highlight completed tasks
        if ((boolean) tableModel.getValueAt(row,
                InteractiveTableModel.INDEX_CHECK)) {
            if (!table.isRowSelected(row)) {
                component.setBackground(Color.CYAN);
            }
        }

        // this is to highlight priority level
        if (tableModel.getValueAt(row, InteractiveTableModel.INDEX_PRIORITY)
                .equals(Priority.PRIORITY_HIGH.toString())) {
            if (!table.isRowSelected(row)) {
                if (column == InteractiveTableModel.INDEX_TASK
                        || column == InteractiveTableModel.INDEX_PRIORITY) {
                    DefaultWeDoTableColor.setPriorityHighBackGroundColour(component);
                }
            }
        } else if (tableModel.getValueAt(row,
                InteractiveTableModel.INDEX_PRIORITY).equals(
                Priority.PRIORITY_MEDIUM.toString())) {
            if (!table.isRowSelected(row)) {
                if (column == InteractiveTableModel.INDEX_TASK
                        || column == InteractiveTableModel.INDEX_PRIORITY) {
                    DefaultWeDoTableColor.setPriorityMediumBackGroundColour(component);
                }
            }
        } else if (tableModel.getValueAt(row,
                InteractiveTableModel.INDEX_PRIORITY).equals(
                Priority.PRIORITY_LOW.toString())) {
            if (!table.isRowSelected(row)) {
                {
                    if (column == InteractiveTableModel.INDEX_TASK
                            || column == InteractiveTableModel.INDEX_PRIORITY)

                    {
                        DefaultWeDoTableColor.setPriorityLowBackGroundColour(component);
                    }
                }
            }
        }

        return component;
    }

}
