package service.logout.interface_adapter;

public class LogoutState {

    private String username = "";

    public LogoutState(LogoutState copy) {
        username = copy.username;
    }

    public LogoutState() {
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
