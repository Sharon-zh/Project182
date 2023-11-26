package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import entity.User;
public interface Recipe {
    String getName();

    String getInstructions();

    String getCategory();

    HashMap<String, String> getIngredients();

    int getLikes();

    ArrayList<String> getComments();

    String getImageLink();

    String getYoutubeLink();
}
