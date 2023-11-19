package service.logout.interface_adapter;

public class LogoutState {

    private String logoutMessage = "";

    public LogoutState(LogoutState copy) {
        logoutMessage = copy.logoutMessage;
    }

    public LogoutState() {
    }

    public String getLogoutMessage() {
        return logoutMessage;
    }
    public void setLogoutMessage(String logoutMessage) {
        this.logoutMessage = logoutMessage;
    }
}
