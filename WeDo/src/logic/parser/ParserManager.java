/**
 * 
 */
package logic.parser;

import java.util.EnumSet;

import logic.command.commandList.AddCommand;
import logic.command.commandList.Command;
import logic.command.commandList.RedoCommand;
import logic.command.commandList.UndoCommand;
import logic.utility.Task;

/**
 * @author Kuan Tien Long
 *
 */
public class ParserManager {
    private Task task;
    private Command command;

    /**
     * @param userInput
     *            the string to be interpreted
     * @return if command and task are parsed successfully
     */
    public boolean interpret(String userInput) {
        DateParser dateParser = new DateParser();
        PriorityParser priorityParser = new PriorityParser();
        DescriptionParser descriptionParser = new DescriptionParser();
        CommandParser commandParser = new CommandParser();

        EnumSet<ParserFlags> parseFlags = tryParse(userInput, dateParser,
                priorityParser, descriptionParser, commandParser);

        if (ParserFlags.isCommandParsed(parseFlags))
        {
            command = commandParser.getCommand();

            if(command instanceof AddCommand && ParserFlags.isParseValidForAdd(parseFlags))
            {
                task = buildTask(parseFlags, dateParser, priorityParser,
                        descriptionParser);
                return true;
            }
            else
            {
                if(command instanceof RedoCommand || command instanceof UndoCommand)
                {
                    return true;
                }
                else if(ParserFlags.isCommandValid(parseFlags))
                {
                    task = buildTask(parseFlags, dateParser, priorityParser,
                            descriptionParser);
                    return true;
                }
                else
                {
                    return false;
                }
            }
           
        } 
        else 
        {
            return false;
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

        System.out.println("to description parser is " + userInput);

        if (descriptionParser.tryParse(userInput)) {
            parseFlags.add(ParserFlags.DESCRIPTION_FLAG);
            userInput = descriptionParser.getWordRemaining();
        }

        System.out.println("to command parser is " + userInput);
        if (commandParser.tryParse(userInput)) {
            parseFlags.add(ParserFlags.COMMAND_FLAG);
        }

        return parseFlags;
    }

    /**
     * @return the task
     */
    public Task getTask() {
        return task;
    }

    /**
     * @return the command
     */
    public Command getCommand() {
        return command;
    }

}
