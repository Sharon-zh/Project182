package entity;

import java.util.List;
import java.util.Map;

public interface RecipeInterface {
    String getName();

    String getInstructions();

    List<String> getIngredients();

    int getLikes();

    Map<Account, String> getComments();
}
