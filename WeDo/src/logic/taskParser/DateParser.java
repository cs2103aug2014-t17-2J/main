/**
 * 
 */
package logic.taskParser;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import logic.utility.AbstractTask;
import logic.utility.StringHandler;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * @author Kuan Tien Long This class help filter our the date for natty parser.
 */
public class DateParser {

    private String wordUsed;
    private String wordRemaining;
    private List<Date> dateList;
    private boolean isTimeSet;

    public boolean parseDate(String source) {

        if (source == null) {
            return false;
        }

        source = source.trim();

        if (source.isEmpty()) {
            return false;
        }

        source = DateStringMassager.massageData(source);

        Parser nattyParser = new Parser();
        List<DateGroup> dateGroups = nattyParser.parse(source);

        if (dateAvailable(dateGroups)) {
            


            
            String dateWordUsed = DateStringMassager.removeWordDelimiter(getDateWordUsed(source, dateGroups));
            
            source = DateStringMassager.removeDigitDelimiters(source);
            source = DateStringMassager.removeWordDelimiter(source);
            
            String dateConnector = DateStringMassager.getDateConnector(source, dateWordUsed);
            wordUsed =  dateConnector + dateWordUsed;
            
            System.out.println("Total wordUsed = " + wordUsed);


                    
            

          
            wordRemaining = StringHandler.removeFirstMatched(source, wordUsed);
            
            dateList = getDateList(dateGroups);
            isTimeSet = isTimeInferred(dateGroups);
            return true;
        } else {
            return false;
        }
    }

    public int getNumberOfDates() {
        return dateList.size();
    }

    public boolean isTimeSet() {
        return isTimeSet;
    }

    public LocalDate getStartDate() {
        final int START_DATE_INDEX = 0;
        return dateToLocalDate(dateList.get(START_DATE_INDEX));
    }

    public LocalDate getEndDate() {
        final int END_DATE_INDEX = dateList.size() - 1;
        final int START_DATE_INDEX = 0;

        if (END_DATE_INDEX == START_DATE_INDEX) {
            return getStartDate();

        } else {
            return dateToLocalDate(dateList.get(END_DATE_INDEX));
        }
    }

    public LocalTime getStartTime() {
        final int START_TIME_INDEX = 0;

        if (isTimeSet) {
            return dateToLocalTime(dateList.get(START_TIME_INDEX));
        } else {
            return AbstractTask.TIME_NOT_SET;
        }

    }

    public LocalTime getEndTime() {
        final int END_TIME_INDEX = dateList.size() - 1;
        final int START_TIME_INDEX = 0;

        if (END_TIME_INDEX == START_TIME_INDEX) {
            return getStartTime();
        }

        if (isTimeSet) {
            return dateToLocalTime(dateList.get(END_TIME_INDEX));
        } else {
            return AbstractTask.TIME_NOT_SET;
        }
    }

    private List<Date> getDateList(List<DateGroup> dateGroup) {

        final int START_INDEX = 0;
        List<Date> dateList = dateGroup.get(START_INDEX).getDates();
        Collections.sort(dateList);
        return dateList;
    }

    private boolean dateAvailable(List<DateGroup> dateGroups) {
        return dateGroups.size() > 0;
    }

    private boolean isTimeInferred(List<DateGroup> dateGroups) {
        return !dateGroups.get(0).isTimeInferred();
    }

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

        System.out.println("wordUsed for date = " + dateText);
        return dateText;
    }

    public LocalDate dateToLocalDate(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                .toLocalDate();
    }

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

}
