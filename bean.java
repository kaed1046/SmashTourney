import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class bean { //bean to wrap the observable
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) { //add an observer
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) { //remove an observer
        this.pcs.removePropertyChangeListener(listener);
    }

    private String event;

    public void makeEvent(String newEvent) { //for each event, send observer the message
        String oldEvent = this.event;
        this.event = newEvent;
        this.pcs.firePropertyChange("value", oldEvent, newEvent);
    }
}
