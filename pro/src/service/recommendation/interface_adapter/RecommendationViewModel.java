package service.recommendation.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RecommendationViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Recommendation View";

    public static final String REFRESH_BUTTON_LABEL = "Refresh";

    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final String RECOMMEND_BUTTON_LABEL = "Recommend";

    private RecommendationState state = new RecommendationState();

    public RecommendationViewModel() {super("recommend");}
    public void setState(RecommendationState state) {
        this.state = state;
    }

    public RecommendationState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("recommendation", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
