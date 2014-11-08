/**
 * 
 */
package logic.parser;

import logic.command.commandList.Command;
import logic.command.commandList.EditCommand;
import logic.utility.KeyWordMappingList;
import logic.utility.MultiMapMatcher;
import logic.utility.StringHandler;


//@author A0112887X
/**
 * Parse the command based on the source
 */
public class CommandParser {

    private Command command;
    private String wordUsed;
    private String wordRemaining;
    private boolean lastWordUsed;

    /**
     * <p>
     * The source will be parsed to see if it contains date.
     * 
     * @param source
     *            the String to be parsed
     * @return if source contains valid command
     */
    public boolean tryParse(String source) {
        if (source == null) {
            return false;
        }

        source = source.trim();

        if (source.isEmpty()) {
            return false;
        }

        String firstWord = StringHandler.getFirstWord(source);
        String lastWord = StringHandler.getLastWord(source);

        command = getCommand(firstWord);
        if (command != null) {
            wordUsed = firstWord;
            source = handleEditCommandforFirstWord(source);
            setWordRemaining(StringHandler.removeFirstMatched(source, wordUsed));
            return true;
        }

        command = getCommand(lastWord);
        if (command != null) {
            wordUsed = lastWord;
            source = handleEditCommandForLastWord(source);
            setWordRemaining(StringHandler.removeLastMatch(source, wordUsed));
            lastWordUsed = true;
            return true;
        }

        return false;
    }

    /**
     * Remove the index for edit and append it to the front if valid return
     * source if invalid
     * 
     * @param source
     * @return
     */
    private String handleEditCommandforFirstWord(String source) {
        String index = StringHandler.getDigitAfterFirstWord(source);
        if (handleFlexibleEditCommand(source, index)) {
            source = StringHandler.removeDigitAfterFirstWord(source);
            return index + " " + source;

        } else {
            return source;
        }

    }

    /**
     * Remove the index for edit and append it to the front if valid return
     * source if invalid
     * 
     * @param source
     * @return
     */
    private String handleEditCommandForLastWord(String source) {
        String index = StringHandler.getDigitAfterLastWord(source);
        if (handleFlexibleEditCommand(source, index)) {
            source = StringHandler.removeDigitAfterLastWord(source);
            return index + " " + source;
        } else {
            return source;
        }

    }

    /**
     * determine is action needed to be done
     * 
     * @param source
     * @param index
     * @return true if there's a valid index and command is EditCommand
     */
    private boolean handleFlexibleEditCommand(String source, String index) {
        if (command instanceof EditCommand) {
            if (index != null) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Get command from the word
     * 
     * @param word
     *            that may contains of the command
     * @return command if valid, null if invalid
     */
    private Command getCommand(String word) {
        return MultiMapMatcher.getMatchedKey(
                KeyWordMappingList.getCommandMultiMap(), word);
    }

    /**
     * Get the command that was parsed
     * 
     * @return command that was parsed
     */
    public Command getCommand() {
        return command;
    }

    /**
     * @return the wordRemaining
     */
    public String getWordRemaining() {
        return wordRemaining;
    }

    /**
     * @param wordRemaining
     *            the wordRemaining to set
     */
    public void setWordRemaining(String wordRemaining) {
        this.wordRemaining = wordRemaining;
    }

    /**
     * @return the wordUsed
     */
    public String getWordUsed() {
        return wordUsed;
    }

    /**
     * @param wordUsed
     *            the wordUsed to set
     */
    public void setWordUsed(String wordUsed) {
        this.wordUsed = wordUsed;
    }

    /**
     * @return the lastWordUsed
     */
    public boolean isLastWordUsed() {
        return lastWordUsed;
    }

    /**
     * @param lastWordUsed
     *            the lastWordUsed to set
     */
    public void setLastWordUsed(boolean lastWordUsed) {
        this.lastWordUsed = lastWordUsed;
    }

}
