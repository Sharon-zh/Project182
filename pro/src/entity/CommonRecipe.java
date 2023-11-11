package entity;

import java.util.HashMap;
import entity.Account;

public class CommonRecipe implements Recipe {

    private final String name;

    private final String category;
    private final String instructions;
    private final HashMap<String, String> ingredients;
    private final int likes;
    private final HashMap<Account, String> comments;

    private final String image_link;

    private final String youtube_link;


    public CommonRecipe(String name, String category, String instructions, HashMap<String, String> ingredients, int likes, HashMap<Account, String> comments, String image_link, String youtube_link) {
        this.name = name;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.category = category;
        this.likes = likes;
        this.comments = comments;
        this.image_link = image_link;
        this.youtube_link = youtube_link;
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
    public HashMap<Account, String> getComments() {
        return comments;
    }

    @Override
    public String getImageLink() {
        return null;
    }

    @Override
    public String getYoutubeLink() {
        return null;
    }

    public String getCategory(){
        return category;
    }

    public String getImage_link(){
        return image_link;
    }

    public String getYoutube_link(){
        return youtube_link;
    }
}
