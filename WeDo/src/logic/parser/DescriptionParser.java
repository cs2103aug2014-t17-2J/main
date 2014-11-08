/**
 * 
 */
package logic.parser;

//@author A0112887X
/**
 *
 */
public class DescriptionParser {
    private String wordUsed;
    private String wordRemaining;
    private String description;

    /**
     * <p>
     * The source will be parsed to see if it contains date.
     * 
     * @param source
     *            the String to be parsed
     * @return if source contains valid description
     */
    public boolean tryParse(String source) {
        if (source == null) {
            return false;
        }

        source = source.trim();

        if (source.isEmpty()) {
            return false;
        }

        wordUsed = description = source;
        wordRemaining = "";

        return true;

    }

    /**
     * @return the wordUsed
     */
    public String getWordRemaining() {
        return wordRemaining;
    }

    /**
     * @return the wordRemaining
     */
    public String getWordUsed() {
        return wordUsed;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
