package service.save_favourite_recipe.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SaveRecipeViewModel extends ViewModel {

    private SaveRecipeState state = new SaveRecipeState();

    public SaveRecipeViewModel() {
        super("recipe");
    }

    public void setState(SaveRecipeState state) {
        this.state = state;
    }

    public SaveRecipeState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("save recipe", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}