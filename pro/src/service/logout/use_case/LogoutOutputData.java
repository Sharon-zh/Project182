package service.logout.use_case;

public class LogoutOutputData {
    final private String username;


    public LogoutOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
