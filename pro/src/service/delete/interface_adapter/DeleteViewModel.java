package service.delete.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeleteViewModel extends ViewModel {

    private DeleteState state = new DeleteState();

    public DeleteViewModel() {
        super("save the recipe");
    }

    public void setState(DeleteState state) {
        this.state = state;
    }

    public DeleteState getState() {
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