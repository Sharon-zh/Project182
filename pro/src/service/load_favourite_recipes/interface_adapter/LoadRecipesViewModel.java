package service.load_favourite_recipes.interface_adapter;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoadRecipesViewModel extends ViewModel {

    private LoadRecipesState state = new LoadRecipesState();

    public LoadRecipesViewModel() {
        super("main");
    }

    public void setState(LoadRecipesState state) {
        this.state = state;
    }

    public LoadRecipesState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("load recipes", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}