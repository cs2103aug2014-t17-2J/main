package dataStorage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

@SuppressWarnings("unchecked")
public class FileHandler {

	final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd");

	private String fileName;
	private final String ID = "ID";
	private final String DESCRPTION = "Description";
	private final String S_DATE = "Start Date";
	private final String E_DATE = "End Date";
	private final String S_TIME = "Start Time";
	private final String E_TIME = "End Time";
	private final String PRIORITY = "Priority";
	private final String STATUS = "Completed";
	private final String DEADLINE = "deadLine";
	private final String TIMED = "timed";
	private final String FLOATING = "floating";

	//@author A0112862L
	public FileHandler() {

		fileName = "WeDo.txt";
		createFile();
		//System.out.println(writeToString(fileName));
		

	}

	//@author A0112862L
	public void createFile() {

		try {
			FileWriter fstream = new FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(fstream);

			bw.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	//@author A0112862L
	public void clear() {

		try {
			FileWriter fstream = new FileWriter(fileName, false);
			BufferedWriter bw = new BufferedWriter(fstream);

			bw.write("");
			bw.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	//@author A0112862L
	private void stringToFile(String str) {
		
		if(isFileEmpty()) {
			return;
		}else {
		
		try {
			FileWriter fstream = new FileWriter(fileName, false);
			BufferedWriter bw = new BufferedWriter(fstream);
			
			bw.write(str);
			
			bw.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

		}
		
	}
	
	//@author A0112862L
	private boolean isFileEmpty() {
		
		String currentLine;
		BufferedReader br;
		

		
		try {
			br = new BufferedReader(new FileReader(fileName));
			currentLine = br.readLine();
			br.close();

			
			if (currentLine == null )
			{
				return true;
			}				
			
		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		
		return false;
	
	}
	
	//@author A0112862L
	private String writeToString(String fileName) {
		
		String currentLine,wholeFile;
		BufferedReader br;
		
		wholeFile = "";

		
		try {
			br = new BufferedReader(new FileReader(fileName));
			

			while ((currentLine = br.readLine()) != null) {
				wholeFile += currentLine + "\r\n";
				
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		
		
		return wholeFile;
	
	}
	
	//@author A0112862L
	private String removeChar(int index,String str) {
		StringBuilder sb = new StringBuilder(str);
		sb.deleteCharAt(index);
		return sb.toString();
	}
	
	
	//@author A0112862L
	public String writeToFile(ArrayList<Task> tasks) {
	
		JSONObject jTasks = toJSON("tasks",tasks);

		try {
			FileWriter fstream = new FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(fstream);
			
			String[] sTasks = jTasks.toString().split(",");
			
			for(String s: sTasks) {
				bw.write(s);
				if(s.charAt(s.length()-1) == '}') {
					bw.newLine();
				}
				bw.newLine();
			}
			
			bw.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;

	}

	//@author A0112862L
	public String writeLog(String log) {

		try {
			FileWriter fstream = new FileWriter("LogFile.txt", true);
			BufferedWriter bw = new BufferedWriter(fstream);

			bw.write(log);
			bw.newLine();
			bw.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

	//@author A0112862L
	private JSONObject toJSON(String type, ArrayList<Task> tasks) {

		JSONObject taskObj = new JSONObject();
		JSONArray allTask = new JSONArray();

		for (Task t : tasks) {
			allTask.add(taskToJSON(t));
		}

		taskObj.put(type, allTask);
		// System.out.println(taskObj.toString());

		return taskObj;
	}

	//@author A0112862L
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
	
	//@author A0112862L
	public void resetID() {
		Task task = new Task();
		task.setUniqueID(0);
	}
	
	//@author A0112862L
	public Multimap<LocalDate,Task> getAllTasks(){
		
		Multimap<LocalDate,Task> tmp;
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
					
					System.out.println(dte);
					
				} catch (NullPointerException n) {
					System.out.println(n);

				}
				if(t != null)
					tmp.put(t.getEndDate(), t);
				
			}


		} catch (ParseException  pe) {
			//e.printStackTrace();
			
			System.out.println("JSON parsing error" + pe);
			if(!isFileEmpty()) {
			stringToFile(removeChar(pe.getPosition(),writeToString(fileName)));
			tmp = getAllTasks();
			}else {
				System.out.println("File is empty");
			}

			
		} catch (IOException e) {
			System.out.println("File Not Found!");
		} 
		
		return tmp;
		
		
	}


	
	//@author A0112862L
	private Task jsonToTask(JSONObject jTask)  {

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

	//@author A0112862L
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
