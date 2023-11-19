package entity;

import java.util.HashMap;

public class CommonRecipeFactory implements RecipeFactory{
    @Override
    public Recipe create(String name, String category, String instructions, HashMap<String, String> ingredients, int likes, HashMap<User, String> comments, String imageLink, String youtubeLink) {
        return new CommonRecipe(name, category, instructions, ingredients, likes, comments, imageLink, youtubeLink);
    }
}
