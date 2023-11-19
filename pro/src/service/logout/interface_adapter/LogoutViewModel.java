package service.logout.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LogoutViewModel extends ViewModel {

    private LogoutState state = new LogoutState();

    public LogoutViewModel() {
        super("log out");
    }

    public void setState(LogoutState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("logout state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LogoutState getState() {
        return state;
    }
}
