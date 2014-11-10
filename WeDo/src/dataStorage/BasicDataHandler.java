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

//@author A0112862L
/**
 * This class handles all the data, including the adding, removing,editing and
 * changing the view.
 *
 */

public class BasicDataHandler implements DataHandler {

	private final String SOMEDAY = "someday";
	private final String DEADLINE = "deadLine";
	private final String TIMED = "timed";
	private final String FLOATING = "floating";
	private final String ALL = "all";
	private final String VIEW_ERROR = "No such view!";

	private static Task currentView;

	FileHandler fileHandler;

	ObservableList<Task> observableList;
	Multimap<LocalDate, Task> mainList;

	public BasicDataHandler() {
		initialize();
		populateLists();
		showToday();

	}

	private void initialize() {
		fileHandler = new FileHandler();
		observableList = new ObservableList<Task>(new ArrayList<Task>());

	}

	public void populateLists() {

		mainList = ArrayListMultimap.create();
		mainList = fileHandler.getAllTasks();
		currentView = new Task();

	}

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

	public ArrayList<Task> getUncompleted() {
		ArrayList<Task> tmp = new ArrayList<Task>();

		for (Task t : mainList.values()) {
			if (t.getCompleted() == false) {
				tmp.add(t);
			}
		}

		return tmp;
	}

	/**
	 * Checks if a task falls within given range of start date and end date.
	 * 
	 * @param startDate
	 *            the beginning of the range
	 * @param endDate
	 *            the end of the range
	 * @param task
	 *            the task to be checked
	 * @return true if the task is within the range. False otherwise.
	 */
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

		return observableList;
	}

	public void addObserver(Observer observer) {
		observableList.addObserver(observer);
	}

	public ArrayList<Task> getAllTasks() {
		ArrayList<Task> tmp = new ArrayList<Task>(mainList.values());

		return tmp;
	}

	public boolean addTask(Task task) throws InvalidCommandException {
		if (task.getDescription() == "") {
			return false;
		}
		addThenView(task);

		int index = observableList.getList().size();
		addTask(index, task);
		return true;
	}

	private void addTask(int index, Task task) {

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

	}

	/**
	 * Save tasks to file.
	 */
	public void save() {

		fileHandler.clear();
		fileHandler.writeToFile(new ArrayList<Task>(mainList.values()));
	}

	/**
	 * Check the task type
	 * 
	 * @param task
	 *            the task to be determined
	 * @return the task type as string
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

	}

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

	/**
	 * Get the list of the tasks that fall within the range.
	 * 
	 * @param startDate
	 *            start of the range
	 * @param endDate
	 *            end of the range
	 * @return list of the tasks that fall within the range
	 */
	public ArrayList<Task> getList(LocalDate startDate, LocalDate endDate) {
		ArrayList<Task> tmp = new ArrayList<Task>();

		while (startDate.isBefore(endDate) || startDate.equals(endDate)) {
			tmp.addAll(mainList.get(startDate));
			startDate = startDate.plusDays(1);
		}

		return tmp;
	}

	public boolean editTask(Task source, Task replacement)
			throws InvalidCommandException {

		int index;
		removeTask(source);
		addThenView(replacement);
		index = observableList.getList().size();
		addTask(index, replacement);

		return true;
	}

	public Task getTask(int index) {

		return observableList.get(index);
	}

	public boolean removeTask(Task task) {

		observableList.remove(task);
		mainList.remove(task.getEndDate(), task);
		save();
		return true;
	}

	/**
	 * change view after adding a task.
	 * 
	 * @param task
	 *            contains the info of the view to be changed.
	 * @throws InvalidCommandException
	 *             if the view is invalid.
	 */
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

	private ArrayList<Task> getDeadLineTasks(Task task) {
		ArrayList<Task> tmp = new ArrayList<Task>(mainList.get(task
				.getEndDate()));
		for (Task t : mainList.values()) {
			if (!tmp.contains(t) && determineTaskType(t).equals(TIMED)
					&& withinRange(t.getStartDate(), t.getEndDate(), task)) {
				tmp.add(t);
			}
		}
		return tmp;
	}

	/**
	 * Change the display list or view.
	 * 
	 * @param task
	 *            contains the info of the the view to be changed.
	 * @throws InvalidCommandException
	 *             if the view requested is invalid.
	 */
	public void view(Task task) throws InvalidCommandException {

		ArrayList<Task> tmp = new ArrayList<Task>();
		String type = determineTaskType(task);
		currentView.setDescription(task.getDescription());
		currentView.setStartDate(task.getStartDate());
		currentView.setEndDate(task.getEndDate());

		Command cmd = getCommand(task.getDescription());

		if (type.equals(DEADLINE)) {
			tmp.addAll(getDeadLineTasks(task));
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
			FileHandler.log(VIEW_ERROR);
			throw new InvalidCommandException(VIEW_ERROR);
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