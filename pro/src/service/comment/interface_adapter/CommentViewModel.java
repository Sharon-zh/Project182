package service.comment.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class CommentViewModel extends ViewModel {
    public CommentState state = new CommentState();
    public CommentViewModel() {
        super("comment view model");
    }

    public void setState(CommentState state) {
        this.state = state;
    }
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("comment", null, this.state);
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CommentState getState(){
        return state;
    }
}
