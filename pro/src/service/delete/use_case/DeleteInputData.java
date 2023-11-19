package service.delete.use_case;


public class DeleteInputData {
    final private String userName;
    final private String recipeName;

    public DeleteInputData(String userName, String recipeName) {
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
