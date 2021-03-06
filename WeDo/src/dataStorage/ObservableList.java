package dataStorage;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

//@author A0112887X
/**
 * 
 * This class consist the observable list which update it's observers whenever a
 * modification occurs The possible modification includes : <li>add <li>delete
 * <li>edit <li>replace list
 * <p>
 * <br>
 * 
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
     * @return whether if the list is null
     */
    private boolean isNull() {
        return observedList == null;
    }

    /**
     * @return whether if the list is empty
     */
    private boolean isEmpty() {
        return observedList.isEmpty();
    }

    /**
     * This function add the argument to the specified index of the list and
     * update the observer(s) with the argument
     * 
     * @param argument
     *            to be added to the list
     * @return boolean if the operation is successful
     */
    public boolean add(int index, T argument) {
        if (isNull()) {
            return false;
        }
        observedList.add(index, argument);
        setChanged();
        notifyObservers(argument);
        return true;
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
        if (isNull()) {
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
    public boolean remove(int index) {
        T deleted;
        if (isNull() || isEmpty()) {
            return false;
        }
        try {
            deleted = observedList.remove(index);
        } catch (IndexOutOfBoundsException exception) {
            return false;
        }
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
    public boolean remove(T argument) {

        boolean succeed;

        if (isNull() || isEmpty()) {
            return false;
        }
        try {
            succeed = observedList.remove(argument);
        } catch (IndexOutOfBoundsException exception) {
            return false;
        }

        setChanged();
        notifyObservers(argument);
        return succeed;
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
     * This function edit the task inside the list <br>
     * In addition, it also updates the observer(s) about with the edited
     * argument
     * 
     * @param argument
     *            that will be replacing the initial information
     * @return boolean if the operation is successful
     */
    public boolean edit(T argument) {
        try {
            int index = observedList.indexOf(argument);
            edit(index, argument);
        } catch (IndexOutOfBoundsException exception) {
            return false;
        }

        return true;
    }

    /**
     * @param argument
     * @return
     */
    public int indexOf(T argument) {
        assert (observedList != null);
        return observedList.indexOf(argument);
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
     * @return the argument at the specified index or null if there's no
     *         argument at the specified index
     */
    public T get(int index) {
        T result;
        try {
            result = observedList.get(index);
        } catch (IndexOutOfBoundsException | NullPointerException exception) {
            return null;
        }
        return result;
    }

    /**
     * Remove all elements in the list
     */
    public void clearList() {
        observedList.clear();
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ObservableList<?> other = (ObservableList<?>) obj;
        if (observedList == null) {
            if (other.observedList != null)
                return false;
        } else if (!observedList.equals(other.observedList))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ObservableList [observedList=" + observedList + "]";
    }
}
