/**
 * 
 */
package logic;

/**
 * @author Kuan Tien Long
 *
 */
public class ChangeableString 
{
    String string;

    public ChangeableString(String string) {
        this.string = string;
    }

    public void changeTo(String newString) {
        string = newString;
    }

    public String toString() {
        return string;
    }
}
