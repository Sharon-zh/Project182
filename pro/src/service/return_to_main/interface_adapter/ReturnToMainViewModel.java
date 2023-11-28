package service.return_to_main.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ReturnToMainViewModel extends ViewModel {
    private ReturnToMainState state = new ReturnToMainState();
    public ReturnToMainViewModel() {
        super("recipe");
    }
    public void setState(ReturnToMainState state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("cancel", null, this.state);
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ReturnToMainState getState(){
        return state;
    }
}
