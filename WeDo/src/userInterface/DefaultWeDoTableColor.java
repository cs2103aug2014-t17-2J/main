/**
 * 
 */
package userInterface;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.MatteBorder;

import definedEnumeration.Priority;

/**
 * @author Kuan Tien Long
 *
 */
public class DefaultWeDoTableColor {
	/**
	 * This function set the default background colour for WeDo table
	 * 
	 * @param component
	 *            the component to set
	 * @param row
	 *            the row to check which color to set
	 */
	public static void setDefaultBackGroundColour(Component component, int row,
			int column, int selectedRow, Object priority, Object checkBox) {

		final Color FIRST_COLOR = Color.LIGHT_GRAY;
		final Color ALTERNATE_COLOR = Color.WHITE;

		if (isRowEvenNumber(row)) {
			component.setBackground(FIRST_COLOR);
		} else {
			component.setBackground(ALTERNATE_COLOR);
		}

		setPriorityBackGroundColor(component, column, column, priority);
		setDoneBackGroundColor(component, column, column, checkBox);
		setSelectedRowColor(component, row, column, selectedRow);
	}

	private static void setSelectedRowColor(Component component, int row, int column,
			int selectedRow) {
	    
	    final Color BORDER_COLOR = Color.BLUE;
	    final int BORDER_THICKNESS = 2;
	    final int NO_BORDER = 0;
	    
        JComponent jComponent = (JComponent) component;

		if (selectedRow == row) 
		{
		    if(column == InteractiveTableModel.INDEX_CHECK)
		    {
	              jComponent.setBorder(new MatteBorder(BORDER_THICKNESS, NO_BORDER, BORDER_THICKNESS, BORDER_THICKNESS, BORDER_COLOR));
		    }
		    else if (column == InteractiveTableModel.INDEX_TASK)
		    {
                jComponent.setBorder(new MatteBorder(BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS, NO_BORDER, BORDER_COLOR));
		    }
		    else
		    {
		        jComponent.setBorder(new MatteBorder(BORDER_THICKNESS, NO_BORDER, BORDER_THICKNESS, NO_BORDER, BORDER_COLOR));
		    }
		} else 
		{
            jComponent.setBorder(BorderFactory.createEmptyBorder());
		}
	}

	/**
	 * This function set the priority high colour for WeDo table
	 * 
	 * @param component
	 *            the component to set
	 * @param row
	 *            the row to check which color to set
	 */
	private static void setPriorityHighBackGroundColour(Component component) {
		final Color PRIORITY_HIGH_COLOR = Color.RED;

		component.setBackground(PRIORITY_HIGH_COLOR);
	}

	/**
	 * This function set the priority high colour for WeDo table
	 * 
	 * @param component
	 *            the component to set
	 * @param row
	 *            the row to check which color to set
	 */
	private static void setPriorityMediumBackGroundColour(Component component) {
		final Color PRIORITY_MEDIUM_COLOR = Color.YELLOW;
		component.setBackground(PRIORITY_MEDIUM_COLOR);
	}

	/**
	 * This function set the priority high colour for WeDo table
	 * 
	 * @param component
	 *            the component to set
	 * @param row
	 *            the row to check which color to set
	 * @return
	 */
	private static void setPriorityLowBackGroundColour(Component component) {
		final Color PRIORITY_LOW_COLOR = Color.GREEN;
		component.setBackground(PRIORITY_LOW_COLOR);
	}

	/**
	 * Determine if row is an even number
	 * 
	 * @param row
	 *            the row to check
	 * @return if row is an even number
	 */
	private static boolean isRowEvenNumber(int row) {
		final int DIVISOR = 2;
		final int EVEN_REMAINDER = 0;
		return row % DIVISOR == EVEN_REMAINDER;
	}

	private static void setPriorityBackGroundColor(Component component,
			int row, int column, Object priority) {

		int colToSet1 = InteractiveTableModel.INDEX_TASK;
		int colToSet2 = InteractiveTableModel.INDEX_PRIORITY;

		if (priority.equals(Priority.PRIORITY_HIGH.toString())) {
			if (column == colToSet1 || column == colToSet2) {
				DefaultWeDoTableColor
						.setPriorityHighBackGroundColour(component);
			}

		} else if (priority.equals(Priority.PRIORITY_MEDIUM.toString())) {
			if (column == colToSet1 || column == colToSet2) {
				DefaultWeDoTableColor
						.setPriorityMediumBackGroundColour(component);
			}
		} else if (priority.equals(Priority.PRIORITY_LOW.toString())) {
			if (column == colToSet1 || column == colToSet2) {
				DefaultWeDoTableColor.setPriorityLowBackGroundColour(component);
			}
		}
	}

	private static void setDoneBackGroundColor(Component component, int row,
			int column, Object checkBox) {

		final Color color = Color.CYAN;

		if (!(checkBox instanceof Boolean)) {
			return;
		}

		boolean isDone = (Boolean) checkBox;

		if (isDone) {
			component.setBackground(color);
		}
	}
}
