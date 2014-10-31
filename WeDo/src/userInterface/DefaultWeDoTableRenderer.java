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
            DefaultWeDoTableColor.setDefaultBackGroundColour(component, row, column, table.getSelectedRow(), tableModel.getValueAt(row, InteractiveTableModel.INDEX_PRIORITY), tableModel.getValueAt(row, InteractiveTableModel.INDEX_CHECK));
        }

        return component;
    }

}
