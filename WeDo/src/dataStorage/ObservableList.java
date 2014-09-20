package dataStorage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * @author kinosker
 *
 * @param <T>
 */
public class ObservableList<T> extends Observable 
{
    ArrayList<T> observedList = new ArrayList<T>();
    public ObservableList(ArrayList<T> list)
    {
        observedList = list;
    }
    
    public void add(T value)
    {
       observedList.add(value);
       setChanged();
       notifyObservers(value);
    }
    
    public void delete(int index)
    {
        T deleted = observedList.remove(index);
        setChanged();
        notifyObservers(deleted);
    }
    
    public void delete(T value)
    {
       observedList.remove(value);
       setChanged();
       notifyObservers(value);
    }
    
    public void replaceList(ArrayList<T> newList)
    {
        observedList = newList;
        setChanged();
        notifyObservers(newList);
    }
    
    public void setObservedList(Observer observer)
    {
        this.addObserver(observer);
    }
}
