package service.save.use_case;

import entity.User;

public class SaveInputData {
    final private String recipeName;
    final private User user;

    public SaveInputData(String recipeName, User user) {
        this.recipeName = recipeName;
        this.user = user;
    }
    public String getRecipeName() {
        return recipeName;
    }
    public User getUser() {
        return user;
    }

}
