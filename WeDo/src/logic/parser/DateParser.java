/**
 * 
 */
package logic.parser;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logic.utility.StringHandler;
import logic.utility.Task;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

//@author A0112887X
/**
 * Parse the date with the help of local date parser and natty parser
 */
public class DateParser {

    private String wordUsed;
    private String wordRemaining;
    private String errorMessage;
    private List<Date> dateList;
    private boolean timeSet;
    private String[] separatedWordRemainings;

    /**
     * <p>
     * The source will be parsed to see if it contains date.
     * 
     * @param source
     *            the String to be parsed
     * @return if source contains valid date
     */
    public boolean tryParse(String source) {

        final String EXCEEDED_DATE_PARSE_LIMIT = "Input contains more than 2 dates to parse";

        if (source == null) {
            return false;
        }

        source = source.trim();

        if (source.isEmpty() || formalDateContainsNegativeNumber(source)
                || formalDateContainsZero(source)) {
            return false;
        }

        source = DateStringMassager.massageData(source);

        Parser nattyParser = new Parser();

        List<DateGroup> dateGroups = nattyParser.parse(source);

        if (dateAvailable(dateGroups)) {
            dateList = getDateList(dateGroups);
            if (exceededDateListLimit()) {
                setErrorMessage(EXCEEDED_DATE_PARSE_LIMIT);
                return false;
            }
            String dateWordUsed = setDateWordUsed(source, dateGroups);
            
            try {
                dateList = parseDateBeforeEpochYear(dateWordUsed, dateList);
            } catch (ParseException e) {
                return false;
            }
            
            timeSet = isTimeInferred(dateGroups);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set the date word used
     * @param source the original message
     * @param dateGroups the group that contains the date info
     * @return
     */
    private String setDateWordUsed(String source, List<DateGroup> dateGroups) {
        String dateWordUsed = getDateWordUsed(source, dateGroups);
        dateWordUsed = DateStringMassager.removeWordDelimiter(dateWordUsed);
        dateWordUsed = DateStringMassager.removeDigitDelimiters(dateWordUsed);

        source = DateStringMassager.removeDigitDelimiters(source);
        source = DateStringMassager.removeWordDelimiter(source);

        String dateConnector = DateStringMassager.getFrontDateConnector(source,
                dateWordUsed);
        wordUsed = dateConnector + dateWordUsed;

        separatedWordRemainings = StringHandler.getSeparatedWord(source,
                wordUsed);
        wordRemaining = StringHandler.removeFirstMatched(source, wordUsed);
        return dateWordUsed;
    }

    /**
     * Check if date limit exceeded
     * @return if there are more than 2 dates
     */
    private boolean exceededDateListLimit() {
        final int MAX_DATE_PARSE = 2;
        return dateList.size() > MAX_DATE_PARSE;
    }

    /**
     * Check if it contains zero in formal dae
     * @param source the original message
     * @return if it contains zero in formal date
     */
    private boolean formalDateContainsZero(String source) {
        String dateWithZeroPattern = ".*\\d\\d\\d\\d/0+/|\\d\\d\\d\\d/\\d+/0+|\\d+/0+/.*";
        return source.matches(dateWithZeroPattern);
    }

    /**
     * Check if it contains negative number in formal date
     * @param source the original message
     * @return if it contains negative number in formal date
     */
    private boolean formalDateContainsNegativeNumber(String source) {
        String dateWithNegativePattern = ".*-\\d+/|/-\\d+.*";
        return source.matches(dateWithNegativePattern);
    }

    /**
     * Handles parsing of year before epoch start year (1970), year is parsed by
     * utilizing LocalDateTime parser
     * 
     * @param source
     *            the String which consist of the date to parse
     * @throws ParseException
     *             if source consist of invalid input
     * @return List<Date> which contains all the date information
     */
    private List<Date> parseDateBeforeEpochYear(String source,
            List<Date> dateList) throws ParseException {
        final int INITIAL_INDEX = 0;
        final int EPOCH_START_YEAR = 1970;
        final int yearGroup = 1;
        final int monthGroup = 2;
        final int dayGroup = 3;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("u/M/d");
        String yyyymmddRegex = "(\\d\\d\\d\\d)[/-](0?[1-9]|1[012])[/-](3[01]|[012]?[0-9])";

        Pattern pattern = Pattern.compile(yyyymmddRegex);
        Matcher matcher = pattern.matcher(source);
        int dateIndex = INITIAL_INDEX;

        while (matcher.find()) {
            int year = Integer.parseInt(matcher.group(1));
            if (year >= EPOCH_START_YEAR) {
                continue;
            }

            Calendar calendar = convertDateToCalendar(dateList.get(dateIndex));

            LocalDate localDate = LocalDate.parse(
                    matcher.group(yearGroup) + "/" + matcher.group(monthGroup)
                            + "/" + matcher.group(dayGroup), formatter);
            LocalTime localTime = getLocalTime(calendar);
            LocalDateTime localDateTime = LocalDateTime
                    .of(localDate, localTime);

            Date parseResult = convertLocalDateToDate(localDateTime);
            dateList.remove(dateIndex);
            dateList.add(dateIndex, parseResult);
            dateIndex++;

        }

        sortDateList(dateList);

        return dateList;
    }

    /**
     * Convert Calendar timing to LocalTime
     * 
     * @param calendar
     *            the calendar which stores the time
     * @return LocalTime which consist of HH:MM:SS
     */
    private LocalTime getLocalTime(Calendar calendar) {
        return LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND),
                calendar.get(Calendar.MILLISECOND));
    }

    /**
     * Convert java.util.date to calendar
     * 
     * @param date
     *            the date to be converted to calendar
     * @return calendar which consist of the date converted
     */
    private Calendar convertDateToCalendar(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Convert local date to date
     * @param localDate the local date to be converted to date
     * @return the date inside local date
     */
    private Date convertLocalDateToDate(LocalDateTime localDate) {
        return Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Get number of dates
     * @return number of dates
     */
    public int getNumberOfDates() {
        return dateList.size();
    }

    /**
     * Determines if time is set
     * @return is time set
     */
    public boolean isTimeSet() {
        return timeSet;
    }

    /**
     * @return the start date
     */
    public LocalDate getStartDate() {
        final int START_DATE_INDEX = 0;
        return dateToLocalDate(dateList.get(START_DATE_INDEX));
    }



    /**
     * @return get end date
     */
    public LocalDate getEndDate() {
        final int LIST_OFFSET = 1;
        final int END_DATE_INDEX = dateList.size() - LIST_OFFSET;
        final int START_DATE_INDEX = 0;

        if (END_DATE_INDEX == START_DATE_INDEX) {
            return getStartDate();

        } else {
            return dateToLocalDate(dateList.get(END_DATE_INDEX));
        }
    }

    /**
     * @return start time
     */
    public LocalTime getStartTime() {
        final int START_TIME_INDEX = 0;

        if (timeSet) {
            return dateToLocalTime(dateList.get(START_TIME_INDEX));
        } else {
            return Task.TIME_NOT_SET;
        }

    }

    /**
     * @return end time
     */
    public LocalTime getEndTime() {
        final int END_TIME_INDEX = dateList.size() - 1;
        final int START_TIME_INDEX = 0;

        if (END_TIME_INDEX == START_TIME_INDEX) {
            return getStartTime();
        }

        if (timeSet) {
            return dateToLocalTime(dateList.get(END_TIME_INDEX));
        } else {
            return Task.TIME_NOT_SET;
        }
    }

    /**
     * @param dateGroup the group which contains the date list
     * @return the date list
     */
    private List<Date> getDateList(List<DateGroup> dateGroup) {

        final int START_INDEX = 0;
        List<Date> dateList = dateGroup.get(START_INDEX).getDates();
        sortDateList(dateList);
        return dateList;
    }

    /**
     * Sort the date list based on which start first.
     * @param dateList the list to be sorted
     */
    private void sortDateList(List<Date> dateList) {
        Collections.sort(dateList);
    }

    /**
     * Check if date is available
     * @param dateGroups the date group which contains the date
     * @return is date available
     */
    private boolean dateAvailable(List<DateGroup> dateGroups) {
        final int NOT_AVAILABLE = 0;
        return dateGroups.size() > NOT_AVAILABLE;
    }

    /**
     * @param dateGroups the date group which contains the date
     * @return is time inferred
     */
    private boolean isTimeInferred(List<DateGroup> dateGroups) {
        final int INITIAL_GROUP = 0;
        return !dateGroups.get(INITIAL_GROUP).isTimeInferred();
    }

    /**
     * Get the date word used for parsing
     * @param source the original message
     * @param dateGroups the date group which contains the date
     * @return the date word used
     */
    private String getDateWordUsed(String source, List<DateGroup> dateGroups) {
        int startPosition = source.length();
        int endPosition = 0;
        for (DateGroup dateGroup : dateGroups) {
            int position = dateGroup.getPosition();
            int length = dateGroup.getText().length();
            startPosition = Math.min(startPosition, position);
            endPosition = Math.max(position + length, endPosition);
        }

        String dateText = source.substring(startPosition, endPosition);

        return dateText;
    }

    /**
     * @param date to be converted
     * @return localdate converted from date
     */
    public LocalDate dateToLocalDate(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * @param time the time to be converted
     * @return localtime converted from time
     */
    public LocalTime dateToLocalTime(Date time) {
        Instant instant = Instant.ofEpochMilli(time.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                .toLocalTime();
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
     * @return the wordRemaining
     */
    public String getWordRemaining() {
        return wordRemaining;
    }


    /**
     * @return the warningMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param warningMessage
     *            the warningMessage to set
     */
    public void setErrorMessage(String warningMessage) {
        this.errorMessage = warningMessage;
    }

    /**
     * @return the separatedWordRemainings
     */
    public String[] getSeparatedWordRemainings() {
        return separatedWordRemainings;
    }

    /**
     * @param separatedWordRemainings the separatedWordRemainings to set
     */
    public void setSeparatedWordRemainings(String[] separatedWordRemainings) {
        this.separatedWordRemainings = separatedWordRemainings;
    }

}
