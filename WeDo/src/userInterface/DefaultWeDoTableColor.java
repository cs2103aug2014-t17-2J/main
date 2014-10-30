/**
 * 
 */
package userInterface;

import java.awt.Color;
import java.awt.Component;

/**
 * @author Kuan Tien Long
 *
 */
public class DefaultWeDoTableColor 
{
    /**
     * This function set the default background colour for WeDo table
     * 
     * @param component
     *            the component to set
     * @param row
     *            the row to check which color to set
     */
    public static void setDefaultBackGroundColour(Component component, int row) {
        final Color FIRST_COLOR = Color.LIGHT_GRAY;
        final Color ALTERNATE_COLOR = Color.WHITE;

        if (isRowEvenNumber(row)) {
            component.setBackground(FIRST_COLOR);
        } else {
            component.setBackground(ALTERNATE_COLOR);
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
    public static void setPriorityHighBackGroundColour(Component component) {
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
    public static void setPriorityMediumBackGroundColour(Component component) {
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
    public static void setPriorityLowBackGroundColour(Component component) {
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

}
