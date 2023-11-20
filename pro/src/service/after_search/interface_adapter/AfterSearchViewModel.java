package service.after_search.interface_adapter;

import interface_adapter.ViewModel;
import service.after_search.interface_adapter.AfterSearchState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AfterSearchViewModel extends ViewModel {
    private AfterSearchState state = new AfterSearchState();

    public static final String GOBACK_BUTTON_LABEL = "go back";

    public AfterSearchViewModel() {super("after search");}

    public void setState(AfterSearchState state) {
        this.state = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("new state", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public AfterSearchState getState() {
        return state;
    }
}
