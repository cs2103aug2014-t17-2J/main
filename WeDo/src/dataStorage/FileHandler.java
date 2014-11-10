package dataStorage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import logic.utility.Task;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import definedEnumeration.Priority;

//@author A0112862L
/**
 * This class handles all the file operations including writing tasks into a
 * text file, retrieving tasks from the text file and logging.
 */

@SuppressWarnings("unchecked")
public class FileHandler {

	private String fileName;
	private final String ID = "ID";
	private final String DESCRPTION = "Description";
	private final String S_DATE = "Start Date";
	private final String E_DATE = "End Date";
	private final String S_TIME = "Start Time";
	private final String E_TIME = "End Time";
	private final String PRIORITY = "Priority";
	private final String STATUS = "Completed";
	private final String FILE_NAME = "WeDo.txt";
	
	private final String IO_ERROR = "IO error :";
	private final String FILE_NOT_FOUND = "File Not Found :";
	private final String JSON_ERROR = "JSON parsing error :";
	private final String FILE_EMPTY = "File Empty :";
	private final String INVALID_DATE_TIME = "Invalid Date Time Format :";
	private final String NULL_POINTER_ERROR = "Null Pointer Encountered :";

	
	private final static String LOG_FILE_NAME = "Log.txt";

	public FileHandler() {

		fileName = FILE_NAME;
		createFile();

	}

	/**
	 * create file if it doesn't exist to avoid the file not found error.
	 */
	public void createFile() {

		try {
			FileWriter fstream = new FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(fstream);

			bw.close();

		} catch (IOException e) {
			log(IO_ERROR + e);
		}

	}

	/**
	 * clear the contents of the "WeDo.txt" file.
	 */
	public void clear() {

		try {
			FileWriter fstream = new FileWriter(fileName, false);
			BufferedWriter bw = new BufferedWriter(fstream);

			bw.write("");
			bw.close();

		} catch (IOException e) {

			log(IO_ERROR + e);
		}

	}

	private void stringToFile(String str) {

		if (isFileEmpty()) {
			return;
		} else {

			try {
				FileWriter fstream = new FileWriter(fileName, false);
				BufferedWriter bw = new BufferedWriter(fstream);

				bw.write(str);

				bw.close();

			} catch (IOException e) {

				log(IO_ERROR + e);
			}

		}

	}

	private boolean isFileEmpty() {

		String currentLine;
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(fileName));
			currentLine = br.readLine();
			br.close();

			if (currentLine == null) {
				return true;
			}

		} catch (FileNotFoundException e) {
			log(FILE_NOT_FOUND + e);
		} catch (IOException e) {
			log(IO_ERROR + e);

		}

		return false;

	}

	/**
	 * Copy the content of "WeDo.txt" to a string for the purpose of handling
	 * JSON error.
	 * 
	 * @param fileName
	 * @return the whole content of "WeDo.txt" file as string
	 */
	private String writeToString(String fileName) {

		String currentLine, wholeFile;
		BufferedReader br;

		wholeFile = "";

		try {
			br = new BufferedReader(new FileReader(fileName));

			while ((currentLine = br.readLine()) != null) {
				wholeFile += currentLine + "\r\n";

			}

			br.close();

		} catch (FileNotFoundException e) {
			log(FILE_NOT_FOUND + e);

		} catch (IOException e) {
			log(IO_ERROR + e);

		}

		return wholeFile;

	}

	/**
	 * delete the character of a string.
	 * 
	 * @param index
	 *            position of the character to be deleted.
	 * @param str
	 *            the string which contains the character to be deleted.
	 * @return the string after the character is deleted.
	 */
	private String removeChar(int index, String str) {
		StringBuilder sb = new StringBuilder(str);
		sb.deleteCharAt(index);
		return sb.toString();
	}

	/**
	 * @param tasks
	 *            list of tasks to be written to the file.
	 */
	public void writeToFile(ArrayList<Task> tasks) {

		JSONObject jTasks = toJSON("tasks", tasks);

		try {
			FileWriter fstream = new FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(fstream);

			String[] sTasks = jTasks.toString().split(",");

			for (String s : sTasks) {
				bw.write(s);
				if (s.charAt(s.length() - 1) == '}') {
					bw.newLine();
				}
				bw.newLine();
			}

			bw.close();

		} catch (IOException e) {
			log(IO_ERROR + e);
		}

	}

	/**
	 * log the error into text file.
	 * 
	 * @param log
	 *            the string to be logged.
	 */
	public static void log(String log) {

		try {
			FileWriter fstream = new FileWriter(LOG_FILE_NAME, true);
			BufferedWriter bw = new BufferedWriter(fstream);

			bw.write(log);
			bw.newLine();
			bw.close();

		} catch (IOException e) {

			log("IO error :" + e);
		}

	}

	/**
	 * Add the task objects into a JSONArray,which will be put into a
	 * JSONObject.
	 * 
	 * @param type
	 *            the key of the JSONObject.
	 * @param tasks
	 *            the list of tasks to be added.
	 * @return JSONObject of JSONArray of tasks.
	 */
	private JSONObject toJSON(String type, ArrayList<Task> tasks) {

		JSONObject taskObj = new JSONObject();
		JSONArray allTask = new JSONArray();

		for (Task t : tasks) {
			allTask.add(taskToJSON(t));
		}

		taskObj.put(type, allTask);

		return taskObj;
	}

	/**
	 * Convert task object into JSONObject.
	 * 
	 * @param task
	 *            the task object to be converted to JSONObject.
	 * @return the converted JSONObject.
	 */
	private JSONObject taskToJSON(Task task) {

		JSONObject tmp = new JSONObject();

		tmp.put(ID, task.getUniqueID());
		tmp.put(DESCRPTION, task.getDescription());
		tmp.put(S_DATE, task.getStartDate().toString());
		tmp.put(E_DATE, task.getEndDate().toString());
		tmp.put(S_TIME, task.getStartTime().toString());
		tmp.put(E_TIME, task.getEndTime().toString());
		tmp.put(PRIORITY, task.getPriority().toString());
		tmp.put(STATUS, task.getCompleted());

		return tmp;
	}

	/**
	 * Reset the ID of the task to "0".
	 */
	public void resetID() {
		Task task = new Task();
		task.setUniqueID(0);
	}

	/**
	 * Retrieve the tasks from the text file.
	 * 
	 * @return the tasks stored in the text file.
	 */
	public Multimap<LocalDate, Task> getAllTasks() {

		Multimap<LocalDate, Task> tmp;
		tmp = ArrayListMultimap.create();

		resetID();

		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(fileName));

			JSONObject jsonObject = (JSONObject) obj;
			JSONArray taskLists = (JSONArray) jsonObject.get("tasks");

			for (Object tObj : taskLists) {
				JSONObject j = (JSONObject) tObj;
				Task t = null;
				try {
					t = jsonToTask(j);
				} catch (DateTimeParseException dte) {

					log(INVALID_DATE_TIME + dte);
				} catch (NullPointerException n) {
					log(NULL_POINTER_ERROR + n);

				}
				if (t != null)
					tmp.put(t.getEndDate(), t);

			}

		} catch (ParseException pe) {

			if (!isFileEmpty()) {
				log(JSON_ERROR + pe);

				stringToFile(removeChar(pe.getPosition(),
						writeToString(fileName)));
				tmp = getAllTasks();
			} else {
				log(FILE_EMPTY);
			}

		} catch (IOException e) {
			log(IO_ERROR + e);
		}

		return tmp;

	}

	/**
	 * Convert the JSONObject to task object.
	 * 
	 * @param jTask
	 *            the JSONObject to be converted.
	 * @return task of the converted JSONObject.
	 */
	private Task jsonToTask(JSONObject jTask) {

		Task task = new Task();

		task.setUniqueID(Integer.parseInt(jTask.get(ID).toString()));
		task.setDescription(jTask.get(DESCRPTION).toString());
		task.setStartDate(LocalDate.parse(jTask.get(S_DATE).toString()));

		task.setEndDate(LocalDate.parse(jTask.get(E_DATE).toString()));
		task.setStartTime(LocalTime.parse(jTask.get(S_TIME).toString()));
		task.setEndTime(LocalTime.parse(jTask.get(E_TIME).toString()));
		task.setPriority(checkPriority(jTask));
		task.setCompleted((boolean) jTask.get(STATUS));

		return task;

	}

	/**
	 * @param jTask 
	 * @return corresponding enum type of Priority.
	 */
	private Priority checkPriority(JSONObject jTask) {

		String pri = jTask.get(PRIORITY).toString();

		if (pri.equalsIgnoreCase(Priority.PRIORITY_HIGH.toString())) {
			return Priority.PRIORITY_HIGH;
		} else if (pri.equalsIgnoreCase(Priority.PRIORITY_MEDIUM.toString())) {
			return Priority.PRIORITY_MEDIUM;
		} else if (pri.equalsIgnoreCase(Priority.PRIORITY_LOW.toString())) {
			return Priority.PRIORITY_LOW;
		} else {
			return Priority.PRIORITY_UNDEFINED;
		}
	}

}
