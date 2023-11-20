package service.logged_in.interface_adapter;

import java.util.ArrayList;

public class LoggedInState {
    private String username = "";

    public LoggedInState(LoggedInState copy) {

        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
