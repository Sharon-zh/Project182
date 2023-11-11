package entity;

import java.util.List;
import java.util.HashMap;

public interface Recipe {
    String getName();

    String getInstructions();

    String getCategory();

    HashMap<String, String> getIngredients();

    int getLikes();

    HashMap<Account, String> getComments();

    String getImageLink();

    String getYoutubeLink();
}
