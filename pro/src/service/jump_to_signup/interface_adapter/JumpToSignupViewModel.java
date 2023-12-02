package service.jump_to_signup.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class JumpToSignupViewModel extends ViewModel {
    private JumpToSignupState state = new JumpToSignupState();
    public JumpToSignupViewModel() {super("log in");}
    public void setState(JumpToSignupState state) {
        this.state = state;
    }

    public JumpToSignupState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("jump to signup", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
