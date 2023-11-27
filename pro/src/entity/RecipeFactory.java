package entity;

import java.util.ArrayList;
import java.util.HashMap;

public interface RecipeFactory {
    Recipe create(String name, String category, String instructions, HashMap<String, String> ingredients, int likes, ArrayList<String> comments, String imageLink, String youtubeLink);
}
