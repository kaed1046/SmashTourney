import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class observer extends PropertyChangeSupport implements PropertyChangeListener{
    public observer (bean sourcebean) {
        super(sourcebean);
    }


    public void propertyChange(PropertyChangeEvent event) {
    }
}