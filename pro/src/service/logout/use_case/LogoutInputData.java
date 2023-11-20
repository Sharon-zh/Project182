package service.logout.use_case;

public class LogoutInputData {
    final private String username;


    public LogoutInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
