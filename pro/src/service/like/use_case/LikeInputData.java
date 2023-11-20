package service.like.use_case;

public class LikeInputData {
    final private String recipe;
    final private String username;

    public LikeInputData(String recipe, String username) {
        this.recipe = recipe;
        this.username = username;
    }

    String getRecipe() {
        return recipe;
    }
    String getUsername() {
        return username;
    }
}
