package dataStorage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Observer;

import logic.command.commandList.Command;
import logic.command.commandList.CompleteCommand;
import logic.command.commandList.IncompleteCommand;
import logic.exception.InvalidCommandException;
import logic.utility.KeyWordMappingList;
import logic.utility.MultiMapMatcher;
import logic.utility.Task;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import definedEnumeration.Priority;

public class BasicDataHandler implements DataHandler {

	private final String SOMEDAY = "someday";
	private final String DEADLINE = "deadLine";
	private final String TIMED = "timed";
	private final String FLOATING = "floating";
	private final String ALL = "all";

	private static Task currentView;

	FileHandler fileHandler;

	ObservableList<Task> observableList;
	Multimap<LocalDate, Task> mainList;

	// @author A0112862L
	public BasicDataHandler() {
		initialize();
		populateLists();
		showToday();
		FileHandler.log(LocalTime.now() + " : DataHandler initialized");

	}
	
	private void initialize() {
		fileHandler = new FileHandler();
		observableList = new ObservableList<Task>(new ArrayList<Task>());

	}
	
	// @author A0112862L
	/**
	 * This function add all the lists into a Multimap according to list type
	 * 
	 * @return whether the operation is successful.
	 */
	public void populateLists() {

		mainList = ArrayListMultimap.create();
		mainList = fileHandler.getAllTasks();
		currentView = new Task();

	}

	// @author A0112862L
	public void showToday() {
		ArrayList<Task> today = new ArrayList<Task>(mainList.get(LocalDate
				.now()));
		observableList.replaceList(today);
		currentView.setEndDate(LocalDate.now());
	}

	public ArrayList<Task> getCompleted() {
		ArrayList<Task> tmp = new ArrayList<Task>();

		for (Task t : mainList.values()) {
			if (t.getCompleted() == true) {
				tmp.add(t);
			}
		}

		return tmp;
	}

	// @author A0112862L
	public ArrayList<Task> getUncompleted() {
		ArrayList<Task> tmp = new ArrayList<Task>();

		for (Task t : mainList.values()) {
			if (t.getCompleted() == false) {
				tmp.add(t);
			}
		}

		return tmp;
	}

	// @author A0112862L
	public boolean withinRange(LocalDate startDate, LocalDate endDate, Task task) {

		while (startDate.isBefore(endDate) || startDate.equals(endDate)) {
			if (task.getStartDate().equals(startDate)
					|| task.getEndDate().equals(endDate)
					|| task.getStartDate().equals(endDate)
					|| task.getEndDate().equals(startDate)) {
				return true;
			}
			startDate = startDate.plusDays(1);

		}
		return false;
	}

	public ObservableList<Task> getObservableList() {

		FileHandler.log(LocalTime.now() + " : ObservableList retrieved!");
		return observableList;
	}

	public void addObserver(Observer observer) {
		observableList.addObserver(observer);
		FileHandler.log(LocalTime.now() + " : Added observer "
				+ observer.toString());
	}



	// @author A0112862L
	public ArrayList<Task> getAllTasks() {
		ArrayList<Task> tmp = new ArrayList<Task>(mainList.values());

		return tmp;
	}

	// @author A0112862L
	public boolean addTask(Task task) throws InvalidCommandException {
		addThenView(task);

		int index = observableList.getList().size();
		addTask(index, task);
		return true;
	}

	// @author A0112862L
	public boolean addTask(int index, Task task) {

		String display = currentView.getDescription();
		mainList.put(task.getEndDate(), task);

		if (display.equals(TIMED)) {
			if (withinRange(currentView.getStartDate(),
					currentView.getEndDate(), task)) {
				observableList.add(index, task);
			}
		} else if (display.equals(ALL)
				|| currentView.getEndDate().equals(task.getEndDate())) {

			observableList.add(index, task);
		}

		save();
		System.out.println(task.getUniqueID() + " is added");

		// fileHandler.read("deadLine");
		FileHandler.log(LocalTime.now() + " : Added Task "
				+ task.getUniqueID());
		return true;
	}

	// @author A0112862L
	public String save() {

		fileHandler.clear();
		fileHandler.writeToFile(new ArrayList<Task>(mainList.values()));
		FileHandler.log(LocalTime.now() + " : Saved!");
		return null;
	}

	// @author A0112862L
	/**
	 * Check if the task should be added to or removed from both main list and
	 * observable list
	 * 
	 * @param task
	 * @return whether the task should be on display
	 */

	private String determineTaskType(Task task) {
		if (task.getEndDate().equals(LocalDate.MAX)
				&& task.getStartDate().equals(LocalDate.MAX)) {
			return FLOATING;
		} else if (!task.getStartDate().equals(LocalDate.MAX)
				&& !task.getEndDate().equals(LocalTime.MAX)) {
			return TIMED;
		} else {
			return DEADLINE;
		}
	}

	public ArrayList<Task> getDisplayedTasks(LocalDate startDate,
			LocalDate endDate) {
		return observableList.getList();
	}

	public void setDisplayedTasks(ArrayList<Task> displayedTask) {
		observableList.replaceList(displayedTask);
		FileHandler.log(LocalTime.now() + " : changed displayed list ");

	}

	// @author A0112862L
	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.BasicDataHandler#canRemove(int)
	 */
	public boolean indexValid(int index) {

		if (index >= (observableList.getList().size()) || index < 0) {
			return false;
		} else {
			return true;
		}
	}

	// @author A0112862L
	public ArrayList<Task> getList(LocalDate startDate, LocalDate endDate) {
		ArrayList<Task> tmp = new ArrayList<Task>();

		while (startDate.isBefore(endDate) || startDate.equals(endDate)) {
			tmp.addAll(mainList.get(startDate));
			startDate = startDate.plusDays(1);
		}

		return tmp;
	}

	// @author A0112862L
	public boolean editTask(Task source, Task replacement)
			throws InvalidCommandException {

		FileHandler.log(LocalTime.now() + " : edited "
				+ source.getUniqueID());
		int index;
		removeTask(source);
		addThenView(replacement);
		index = observableList.getList().size();
		addTask(index, replacement);

		return true;
	}

	// @author A0112862L
	public Task getTask(int index) {

		return observableList.get(index);
	}

	// @author A0112862L
	public boolean removeTask(Task task) {


		observableList.remove(task);
		mainList.remove(task.getEndDate(), task);
		save();
		return true;
	}

	// @author A0112862L
	public void addThenView(Task task) throws InvalidCommandException {
		String type = determineTaskType(task);

		if (type.equals(DEADLINE) || type.equals(TIMED)) {
			view(task);
		} else {
			Task tmp = new Task();
			tmp.setDescription(SOMEDAY);
			view(tmp);
		}
	}

	// @author A0112862L
	private ArrayList<Task> getPriTasks(Priority pri) {

		ArrayList<Task> tmp = new ArrayList<Task>();

		for (Task t : mainList.values()) {
			if (t.getPriority().equals(pri)) {
				tmp.add(t);
			}
		}

		return tmp;
	}

	private Command getCommand(String firstWord) {
		return MultiMapMatcher.getMatchedKey(
				KeyWordMappingList.getCompletedUnCompleteMultiMap(), firstWord);
	}

	// @author A0112862L
	public void view(Task task) throws InvalidCommandException {

		ArrayList<Task> tmp = new ArrayList<Task>();
		String type = determineTaskType(task);
		currentView.setDescription(task.getDescription());
		currentView.setStartDate(task.getStartDate());
		currentView.setEndDate(task.getEndDate());

		Command cmd = getCommand(task.getDescription());

		if (type.equals(DEADLINE)) {
			tmp.addAll(mainList.get(task.getEndDate()));
		} else if (type.equals(TIMED)) {
			tmp.addAll(getList(task.getStartDate(), task.getEndDate()));
		} else if (cmd instanceof CompleteCommand) {
			tmp.addAll(getCompleted());
		} else if (cmd instanceof IncompleteCommand) {
			tmp.addAll(getUncompleted());
		} else if (task.getPriority() != null
				&& !task.getPriority().equals(Task.PRIORITY_NOT_SET)) {
			tmp = getPriTasks(task.getPriority());
		} else if (task.getDescription().equalsIgnoreCase(ALL)) {
			tmp.addAll(mainList.values());
		} else if (task.getDescription().equals(SOMEDAY)) {
			tmp.addAll(mainList.get(LocalDate.MAX));
		} else {
			FileHandler.log("No Such View");
			throw new InvalidCommandException("No Such View");
		}

		observableList.replaceList(tmp);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#completeTask(logic.utility.Task)
	 */
	@Override
	public boolean setCompleteTask(Task task, boolean isComplete) {
		// Task taskToComplete = getTask(index);
		task.setCompleted(isComplete);
		save();
		return observableList.edit(task);
	}

}