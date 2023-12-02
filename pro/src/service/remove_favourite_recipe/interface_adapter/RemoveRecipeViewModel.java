package service.remove_favourite_recipe.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RemoveRecipeViewModel extends ViewModel {

//    public static final String REMOVE_BUTTON_LABEL = "remove";
//    public static final String CANCEL_BUTTON_LABEL = "cancel";
    public static final String TITLE_LABEL = "favourite recipes";
    private RemoveRecipeState state = new RemoveRecipeState();

    public RemoveRecipeViewModel() {
        super("favourite recipes");
    }

    public void setState(RemoveRecipeState state) {
        this.state = state;
    }

    public RemoveRecipeState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("remove recipe", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}