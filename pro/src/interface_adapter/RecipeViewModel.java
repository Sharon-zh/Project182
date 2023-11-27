package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RecipeViewModel extends ViewModel {
    public final String TITLE_LABEL = "Recipe In View";

    private RecipeState state = new RecipeState();

    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String LIKE_BUTTON_LABEL = "Like";
    public static final String SAVE_BUTTON_LABEL = "Save";

    public RecipeViewModel() {
        super("recipe");
    }

    public void setState(RecipeState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public RecipeState getState() {
        return state;
    }
}
