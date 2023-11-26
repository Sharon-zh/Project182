package entity;

import java.util.ArrayList;
import java.util.HashMap;
import entity.User;

public class CommonRecipe implements Recipe {

    private final String name;

    private final String category;
    private final String instructions;
    private final HashMap<String, String> ingredients;
    private final int likes;
    private final ArrayList<String> comments;

    private final String imageLink;

    private final String youtubeLink;


    public CommonRecipe(String name, String category, String instructions, HashMap<String, String> ingredients,
                        int likes, ArrayList<String> comments, String imageLink, String youtubeLink) {
        this.name = name;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.category = category;
        this.likes = likes;
        this.comments = comments;
        this.imageLink = imageLink;
        this.youtubeLink = youtubeLink;
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
    public HashMap<String, String> getIngredients() {
        return ingredients;
    }

    @Override
    public int getLikes() {
        return likes;
    }

    @Override
    public ArrayList<String> getComments() {
        return comments;
    }

    @Override
    public String getImageLink() {
        return imageLink;
    }

    @Override
    public String getYoutubeLink() {
        return youtubeLink;
    }

    public String getCategory() {
        return category;
    }

    public void setComments(){}
}
