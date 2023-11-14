package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface User {

    String getName();

    String getPassword();

    LocalDateTime getCreationTime();

    ArrayList<Recipe> getFavoriteRecipes();
}
