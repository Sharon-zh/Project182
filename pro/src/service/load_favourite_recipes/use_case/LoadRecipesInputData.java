package service.load_favourite_recipes.use_case;


public class LoadRecipesInputData {
    final private String userName;
    public LoadRecipesInputData(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }
}
