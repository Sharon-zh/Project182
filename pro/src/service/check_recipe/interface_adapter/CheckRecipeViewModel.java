package service.check_recipe.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CheckRecipeViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Recipe";
    private CheckRecipeState state = new CheckRecipeState();

    public CheckRecipeViewModel() {
        super("recipe");
    }

    public void setState(CheckRecipeState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("check recipe", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CheckRecipeState getState() {
        return state;
    }
}
