package userInterface;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import ui.UserInterfaceMain;
import definedEnumeration.Priority;


//@author A0112675H

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

        setPriorityBackGroundColor(component, row, column, priority);
        setDoneBackGroundColor(component, row, column, checkBox);
        setSelectedRowColor(component, row, column, selectedRow);
        setIndexLayout(component, row, column);

    }

    private static void setSelectedRowColor(Component component, int row,
            int column, int selectedRow) {

        final Color BORDER_COLOR = Color.BLUE;
        final Color FOREGROUND_COLOR = Color.BLACK;
        final int BORDER_THICKNESS = 3;
        final int NO_BORDER = 0;
        
        JComponent jComponent = (JComponent) component;
        jComponent.setForeground(FOREGROUND_COLOR);
        if (selectedRow == row) {
            if (column == InteractiveTableModel.INDEX_CHECK) {
                jComponent.setBorder(new MatteBorder(BORDER_THICKNESS,
                        NO_BORDER, BORDER_THICKNESS, BORDER_THICKNESS,
                        BORDER_COLOR));
            } else if (column == InteractiveTableModel.INDEX_TASK) {
                jComponent.setBorder(new MatteBorder(BORDER_THICKNESS,
                        BORDER_THICKNESS, BORDER_THICKNESS, NO_BORDER,
                        BORDER_COLOR));
            } else {
                jComponent.setBorder(new MatteBorder(BORDER_THICKNESS,
                        NO_BORDER, BORDER_THICKNESS, NO_BORDER, BORDER_COLOR));
            }
        } else {
            if (column == InteractiveTableModel.INDEX_TASK) {
                jComponent.setBorder(new MatteBorder(NO_BORDER, NO_BORDER,
                        NO_BORDER, NO_BORDER, BORDER_COLOR));
            } else {
                jComponent.setBorder(BorderFactory.createEmptyBorder());
            }
        }
        UserInterfaceMain.TimerFocusTextfield();
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
        final Color PRIORITY_HIGH_COLOR = Color.getHSBColor(0.099f, 1f, 1f);

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

        int columnToSet = InteractiveTableModel.INDEX_PRIORITY;

        if (priority.equals(Priority.PRIORITY_HIGH.toString())) {
            if (column == columnToSet) {
                DefaultWeDoTableColor
                        .setPriorityHighBackGroundColour(component);
            }

        } else if (priority.equals(Priority.PRIORITY_MEDIUM.toString())) {
            if (column == columnToSet) {
                DefaultWeDoTableColor
                        .setPriorityMediumBackGroundColour(component);
            }
        } else if (priority.equals(Priority.PRIORITY_LOW.toString())) {
            if (column == columnToSet) {
                DefaultWeDoTableColor.setPriorityLowBackGroundColour(component);
            }
        }
    }

    private static void setIndexLayout(Component component,
            int row, int column) {

        final Color INDEX_COLOR = new Color(0,0,0,40);
        int columnToSet = InteractiveTableModel.INDEX_TASK;
        JComponent jComponent = (JComponent) component;
        Border border = jComponent.getBorder();
     
            if (column == columnToSet) {            
                border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(0,0,0,1,INDEX_COLOR));
                jComponent.setBorder(border);

            }
    }

    private static void setDoneBackGroundColor(Component component, int row,
            int column, Object checkBox) {

        final Color color = Color.getHSBColor(0.5861f, 0.7f, 1f);

        if (!(checkBox instanceof Boolean)) {
            return;
        }

        boolean isDone = (Boolean) checkBox;

        if (isDone) {
            component.setBackground(color);
        }
    }
}
