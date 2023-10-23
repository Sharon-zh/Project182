package entity;

import java.util.List;
import java.util.Map;

public class Recipe implements RecipeInterface{

    private final String name;
    private final String instructions;
    private final List<String> ingredients;
    private final int likes;
    private final Map<Account, String> comments;

    public Recipe(String name, String instructions, List<String> ingredients, int likes, Map<Account, String> comments) {
        this.name = name;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.likes = likes;
        this.comments = comments;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getInstructions() {
        return instructions;
    }

    @Override
    public List<String> getIngredients() {
        return ingredients;
    }

    @Override
    public int getLikes() {
        return likes;
    }

    @Override
    public Map<Account, String> getComments() {
        return comments;
    }
}
