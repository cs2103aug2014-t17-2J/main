package dataStorage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * This class consist the observable list which update it's observers whenever a modification occurs
 * The possible modification includes : 
 * <li> add 
 * <li> delete
 * <li> edit
 * <li> replace list
 * <p><br>
 * 
 * @author Kuan Tien Long  
 * 
 * @param <T> the list type 
 */
public class ObservableList<T> extends Observable 
{
    ArrayList<T> observedList = new ArrayList<T>();
    public ObservableList(ArrayList<T> list)
    {
        observedList = list;
    }
    
    /**
     * This function add the argument to the list and update the observer(s) with the argument
     * @param argument to be added to the list
     */
    public void add(T argument)
    {
       observedList.add(argument);
       setChanged();
       notifyObservers(argument);
    }
    
    /**
     * This function delete the specified index of the list and update the observer(s) about with the deleted argument 
     * @param index to be deleted from the list
     */
    public void delete(int index)
    {
        T deleted = observedList.remove(index);
        setChanged();
        notifyObservers(deleted);
    }
    
    /**
     * This function delete the specified argument of the list and update the observer(s) about with the deleted argument
     * @param argument to be deleted from the list
     */
    public void delete(T argument)
    {
       observedList.remove(argument);
       setChanged();
       notifyObservers(argument);
    }
    
    /**
     * This function edit the specified index of the list with the argument.
     * <br> In addition, it also updates the observer(s) about with the edited argument
     * @param index that is to be selected for editing
     * @param argument that will be replacing the initial information
     */
    public void edit(int index, T argument)
    {
        observedList.set(index, argument);
        setChanged();
        notifyObservers(argument);
    }
    
    /**
     * This function replaces the entire list.
     * <br> In addition, it also updates the observer with the new list
     * @param newList which will be used to replace the initial list.
     */
    public void replaceList(ArrayList<T> newList)
    {
        observedList = newList;
        setChanged();
        notifyObservers(newList);
    }
    
    /**
     * This function retrieve the argument from the specified index of the list 
     * @param index of the argument to return
     * @return the argument at the specified index
     */
    public T get(int index)
    {
        return observedList.get(index);
    }
    
    /**
     * @return the stored list
     */
    public ArrayList<T> getList()
    {
        return observedList;
        
    }
    
    /**
     * This function add observer to the list. 
     * <br>The observer will be updated when modification to the list is made
     * @param observer that will be added to observed the modification in the list
     */
    public void addObserverList(Observer observer)
    {
        this.addObserver(observer);
    }
}
