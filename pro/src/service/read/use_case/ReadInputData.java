package service.read.use_case;


public class ReadInputData {
    final private String userName;
    public ReadInputData(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }
}
