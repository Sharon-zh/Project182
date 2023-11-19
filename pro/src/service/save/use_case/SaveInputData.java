package service.save.use_case;


public class SaveInputData {
    final private String userName;
    final private String recipeName;

    public SaveInputData(String userName, String recipeName) {
        this.userName = userName;
        this.recipeName = recipeName;
    }
    public String getUserName() {
        return userName;
    }
    public String getRecipeName() {
        return recipeName;
    }
}
