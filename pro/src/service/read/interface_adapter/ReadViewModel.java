package service.read.interface_adapter;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ReadViewModel extends ViewModel {

    private ReadState state = new ReadState();

    public ReadViewModel() {
        super("read favourite recipes");
    }

    public void setState(ReadState state) {
        this.state = state;
    }

    public ReadState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("readState", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}