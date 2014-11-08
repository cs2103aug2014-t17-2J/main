/**
 * 
 */
package logic.parser;

import logic.utility.StringHandler;

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

    public boolean isDescriptionSeparated(String[] separatedWordsRemaining,
            String commandWordUsed) {
        final int SEPARTED_LENGTH = 3;

        if (separatedWordsRemaining == null || commandWordUsed == null) {
            return false; 
        }

        if (separatedWordsRemaining.length >= SEPARTED_LENGTH) {
            return true;
        }

        for (String word : separatedWordsRemaining) {
            String removedWord = StringHandler.removeFirstMatchedWord(word,
                    commandWordUsed);
            removedWord = removedWord.trim();
            word = word.trim();
            if (!(removedWord.equals(word)) && consistWord(removedWord)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if there are still words
     * 
     * @param source
     *            the string to check
     * @return if there are still words
     */
    private boolean consistWord(String source) {
        return !(source.trim().isEmpty());
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
