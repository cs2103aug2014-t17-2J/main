/**
 * 
 */
package logic.taskParser.taskFieldSetter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.Task;
import logic.utility.KeyMatcher;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;
import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import definedEnumeration.Priority;
import edu.emory.mathcs.backport.java.util.Collections;

/**
 * @author Kuan Tien Long
 *
 */
public interface TaskFieldSetter {
    String set(Task task, String arguments);
}


