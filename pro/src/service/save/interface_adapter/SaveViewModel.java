package service.save.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SaveViewModel extends ViewModel {

    private SaveState state = new SaveState();

    public SaveViewModel() {
        super("save the recipe");
    }

    public void setState(SaveState state) {
        this.state = state;
    }

    public SaveState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("saveState", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}