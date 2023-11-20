package service.recommendation.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RecommendationViewModel extends ViewModel {

    private RecommendationState state = new RecommendationState();

    public RecommendationViewModel() {super("recommendation");}
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
