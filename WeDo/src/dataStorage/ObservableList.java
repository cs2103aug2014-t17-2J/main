package dataStorage;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * This class consist the observable list which update it's observers whenever a
 * modification occurs The possible modification includes : <li>add <li>delete
 * <li>edit <li>replace list
 * <p>
 * <br>
 * 
 * @author Kuan Tien Long
 * 
 * @param <T>
 *            the list type
 */
public class ObservableList<T> extends Observable {
    private ArrayList<T> observedList;

    public ObservableList(ArrayList<T> list) {
        observedList = list;
    }

    /**
     * @return whether if the list is empty or null
     */
    private boolean isEmpty() {
        return observedList == null || observedList.isEmpty();
    }

    /**
     * This function add the argument to the list and update the observer(s)
     * with the argument
     * 
     * @param argument
     *            to be added to the list
     * @return boolean if the operation is successful
     */
    public boolean add(T argument) {
        if (isEmpty()) {
            return false;
        }
        observedList.add(argument);
        setChanged();
        notifyObservers(argument);
        return true;
    }

    /**
     * This function delete the specified index of the list and update the
     * observer(s) about with the deleted argument
     * 
     * @param index
     *            to be deleted from the list
     * @return boolean if the operation is successful
     */
    public boolean delete(int index) {
        if (isEmpty()) {
            return false;
        }
        T deleted = observedList.remove(index);
        setChanged();
        notifyObservers(deleted);
        return true;
    }

    /**
     * This function delete the specified argument of the list and update the
     * observer(s) about with the deleted argument
     * 
     * @param argument
     *            to be deleted from the list
     * @return boolean if the operation is successful
     */
    public boolean delete(T argument) {
        if (isEmpty()) {
            return false;
        }
        observedList.remove(argument);
        setChanged();
        notifyObservers(argument);
        return true;
    }

    /**
     * This function edit the specified index of the list with the argument. <br>
     * In addition, it also updates the observer(s) about with the edited
     * argument
     * 
     * @param index
     *            that is to be selected for editing
     * @param argument
     *            that will be replacing the initial information
     * @return boolean if the operation is successful
     */
    public boolean edit(int index, T argument) {
        try {
            observedList.set(index, argument);
        } catch (IndexOutOfBoundsException exception) {
            return false;
        }
        setChanged();
        notifyObservers(argument);
        return true;
    }

    /**
     * This function replaces the entire list. <br>
     * In addition, it also updates the observer with the new list
     * 
     * @param newList
     *            which will be used to replace the initial list.
     */
    public void replaceList(ArrayList<T> newList) {
        observedList = newList;
        setChanged();
        notifyObservers(newList);
    }

    /**
     * This function retrieve the argument from the specified index of the list
     * 
     * @param index
     *            of the argument to return
     * @return the argument at the specified index
     */
    public T get(int index) {
        return observedList.get(index);
    }

    /**
     * @return the stored list
     */
    public ArrayList<T> getList() {
        return observedList;

    }

    /**
     * This function add observer to the list. <br>
     * The observer will be updated when modification to the list is made
     * 
     * @param observer
     *            that will be added to observed the modification in the list
     */
    public void addObserverList(Observer observer) {
        this.addObserver(observer);
    }
}
