package service.search.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {
    public static final String TITLE_LABEL = "search result";
    public static final String CANCEL_BUTTON_LABEL = "cancel";

    private SearchState state = new SearchState();
    public SearchViewModel() {super("search result");}
    public void setState(SearchState state) {this.state = state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {support.firePropertyChange("search", null, this.state);}
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public SearchState getState() {return state;}
}
