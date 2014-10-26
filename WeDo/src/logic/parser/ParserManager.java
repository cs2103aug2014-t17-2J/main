/**
 * 
 */
package logic.parser;

import java.util.EnumSet;
import logic.utility.Task;

/**
 * @author Kuan Tien Long
 *
 */
public class ParserManager {

    public DynamicParseResult dynamicParsing(String userInput) 
    {
        DynamicParseResult parseResult = new DynamicParseResult();
        DateParser dateParser = new DateParser();
        PriorityParser priorityParser = new PriorityParser();
        DescriptionParser descriptionParser = new DescriptionParser();
        CommandParser commandParser = new CommandParser();
        EnumSet<ParserFlags> parseFlags = tryParse(userInput, dateParser,
                priorityParser, descriptionParser, commandParser);
        
        
        parseResult.setCommand(commandParser.getCommand());
        parseResult.setTask(buildTask(parseFlags, dateParser,
                priorityParser, descriptionParser));
        parseResult.setParseFlags(parseFlags);
        parseResult.setDateWordUsed(dateParser.getWordUsed());
        parseResult.setPriorityWordUsed(priorityParser.getWordUsed());
        parseResult.setCommandWordUsed(commandParser.getWordUsed());
        parseResult.setDescriptionWordUsed(descriptionParser.getWordUsed());
        
        System.out.println(parseResult);
        return parseResult;
    }



    /**
     * @param userInput
     *            the string to be interpreted
     * @return ParseResult which contains task, command, isSuccessful (to determine whether parse succeed) and failedMessage.
     */
    public ParseResult interpret(String userInput) {

        final String COMMAND_PARSE_FAILED = "No such command";
        final String INSUFFICIENT_ATTRIBUTE = "Insufficient attribute(s) for the command";
        
        ParseResult parseResult = new ParseResult();
        DateParser dateParser = new DateParser();
        PriorityParser priorityParser = new PriorityParser();
        DescriptionParser descriptionParser = new DescriptionParser();
        CommandParser commandParser = new CommandParser();

        EnumSet<ParserFlags> parseFlags = tryParse(userInput, dateParser,
                priorityParser, descriptionParser, commandParser);

        if (!isCommandParsed(parseFlags)) {
            parseResult.setSuccessful(false);
            parseResult.setFailedMessage(COMMAND_PARSE_FAILED);
            return parseResult;
        } else {
            if (!commandParser.getCommand().validate(parseFlags)) {
                parseResult.setSuccessful(false);
                parseResult.setFailedMessage(INSUFFICIENT_ATTRIBUTE);
                return parseResult;
            } else {
                parseResult.setSuccessful(true);
                parseResult.setCommand(commandParser.getCommand());
                parseResult.setTask(buildTask(parseFlags, dateParser,
                        priorityParser, descriptionParser));
                return parseResult;
            }
        }
    }

    /**
     * Build task with the results stored in the parser(s)
     * 
     * @param parseFlags
     *            the set of flags which consist of successful parses
     * @param dateParser
     *            that may consist of the parsed date result
     * @param priorityParser
     *            that may consist of the parsed priority result
     * @param descriptionParser
     *            that may consist of the parsed description result
     * @return
     */
    private Task buildTask(EnumSet<ParserFlags> parseFlags,
            DateParser dateParser, PriorityParser priorityParser,
            DescriptionParser descriptionParser) {

        if (!parseFlags.contains(ParserFlags.DATE_FLAG)) {
            return buildFloatingTask(parseFlags, priorityParser,
                    descriptionParser);
        } else {
            if (dateParser.getNumberOfDates() > 1) {
                return buildTimedTask(parseFlags, dateParser, priorityParser,
                        descriptionParser);
            } else {
                return buildDeadLineTask(parseFlags, dateParser,
                        priorityParser, descriptionParser);
            }
        }

    }

    /**
     * Build deadline with the results stored in the parser(s)
     * 
     * @param parseFlags
     *            the set of flags which consist of successful parses
     * @param dateParser
     *            that may consist of the parsed date result
     * @param priorityParser
     *            that may consist of the parsed priority result
     * @param descriptionParser
     *            that may consist of the parsed description result
     * @return DeadLineTask which consist of description, priority, end date,
     *         end time
     */
    private Task buildDeadLineTask(EnumSet<ParserFlags> parseFlags,
            DateParser dateParser, PriorityParser priorityParser,
            DescriptionParser descriptionParser) {
        if (!parseFlags.contains(ParserFlags.PRIORITY_FLAG)) {
            return new Task(descriptionParser.getDescription(),
                    Task.PRIORITY_NOT_SET, dateParser.getEndDate(),
                    dateParser.getEndTime());
        } else {
            return new Task(descriptionParser.getDescription(),
                    priorityParser.getPriority(), dateParser.getEndDate(),
                    dateParser.getEndTime());
        }
    }

    /**
     * Build timed task with the results stored in the parser(s)
     * 
     * @param parseFlags
     *            the set of flags which consist of successful parses
     * @param dateParser
     *            that may consist of the parsed date result
     * @param priorityParser
     *            that may consist of the parsed priority result
     * @param descriptionParser
     *            that may consist of the parsed description result
     * @return TimedTask which consist of description, priority, end date, end
     *         time, start date, start time
     */
    private Task buildTimedTask(EnumSet<ParserFlags> parseFlags,
            DateParser dateParser, PriorityParser priorityParser,
            DescriptionParser descriptionParser) {
        if (!parseFlags.contains(ParserFlags.PRIORITY_FLAG)) {
            return new Task(descriptionParser.getDescription(),
                    Task.PRIORITY_NOT_SET, dateParser.getStartDate(),
                    dateParser.getStartTime(), dateParser.getEndDate(),
                    dateParser.getEndTime());
        } else {
            return new Task(descriptionParser.getDescription(),
                    priorityParser.getPriority(), dateParser.getStartDate(),
                    dateParser.getStartTime(), dateParser.getEndDate(),
                    dateParser.getEndTime());
        }
    }

    /**
     * Build timed task with the results stored in the parser(s)
     * 
     * @param parseFlags
     *            the set of flags which consist of successful parses
     * @param dateParser
     *            that may consist of the parsed date result
     * @param priorityParser
     *            that may consist of the parsed priority result
     * @param descriptionParser
     *            that may consist of the parsed description result
     * @return FloatingTask which consist of description, priority
     */
    private Task buildFloatingTask(EnumSet<ParserFlags> parseFlags,
            PriorityParser priorityParser, DescriptionParser descriptionParser) {
        if (!parseFlags.contains(ParserFlags.PRIORITY_FLAG)) {
            return new Task(descriptionParser.getDescription(),
                    Task.PRIORITY_NOT_SET);
        } else {
            return new Task(descriptionParser.getDescription(),
                    priorityParser.getPriority());
        }
    }

    /**
     * This function try to parse the userInput with the available parser(s).<br>
     * Parse(s) which are successful are added to the parseFlags. <br>
     * 
     * @param userInput
     *            the String that will be parsed.
     * @param dateParser
     *            the parser which could parse date.
     * @param priorityParser
     *            the parser which could parse priority.
     * @param descriptionParser
     *            the parser which could parse description.
     * @param commandParser
     *            the parser which could parse command.
     * @return parseFlags which consist of successful parses
     */
    public EnumSet<ParserFlags> tryParse(String userInput,
            DateParser dateParser, PriorityParser priorityParser,
            DescriptionParser descriptionParser, CommandParser commandParser) {
        EnumSet<ParserFlags> parseFlags = EnumSet.noneOf(ParserFlags.class);

        System.out.println("to date parser " + userInput);
        if (dateParser.tryParse(userInput)) {
            parseFlags.add(ParserFlags.DATE_FLAG);
            userInput = dateParser.getWordRemaining();
        }

        System.out.println("to priority parser is " + userInput);

        if (priorityParser.tryParse(userInput)) {
            parseFlags.add(ParserFlags.PRIORITY_FLAG);
            userInput = priorityParser.getWordRemaining();
        }
        
        System.out.println("to command parser is " + userInput);
        if (commandParser.tryParse(userInput)) {
            parseFlags.add(ParserFlags.COMMAND_FLAG);
            userInput = commandParser.getWordRemaining();
        }
        
        System.out.println("to description parser is " + userInput);

        if (descriptionParser.tryParse(userInput)) {
            parseFlags.add(ParserFlags.DESCRIPTION_FLAG);
            userInput = descriptionParser.getWordRemaining();
        }



        return parseFlags;
    }

    /**
     * <p>
     * Determine whether the valid command is parsed
     * <p>
     * 
     * @param flagSet
     *            the set of ParserFlag to be tested
     * @return if it contains of the Command flag
     */
    public boolean isCommandParsed(EnumSet<ParserFlags> flagSet) {

        if (flagSet.contains(ParserFlags.COMMAND_FLAG)) {
            return true;
        } else {
            return false;
        }
    }
}
