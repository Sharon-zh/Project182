package service.check_favourite_recipe.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CheckFavourRecipeViewModel extends ViewModel {
    private CheckFavourRecipeState state = new CheckFavourRecipeState();

    public CheckFavourRecipeViewModel() {
        super("recipe");
    }

    public void setState(CheckFavourRecipeState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("check favourite recipe", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CheckFavourRecipeState getState() {
        return state;
    }
}
