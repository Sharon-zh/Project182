package data_access;

import entity.User;
import entity.UserFactory;
import service.load_favourite_recipes.use_case.LoadRecipesDataAccessInterface;
import service.login.use_case.LoginUserDataAccessInterface;
import service.remove_favourite_recipe.use_case.RemoveRecipeDataAccessInterface;
import service.save_favourite_recipe.use_case.SaveRecipeDataAccessInterface;
import service.signup.use_case.SignupUserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FileUserDataAccessObject implements SaveRecipeDataAccessInterface, RemoveRecipeDataAccessInterface,
        LoadRecipesDataAccessInterface, SignupUserDataAccessInterface, LoginUserDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("creation_time", 2);
        headers.put("favourite_recipes", 3);


        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("username, password, creation_time, favourite_recipes");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",\\s");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    String recipeNames = String.valueOf(col[headers.get("favourite_recipes")]);
                    String[] splitArray = recipeNames.split(",");
                    ArrayList<String> favouriteRecipeList = new ArrayList<String>(Arrays.asList(splitArray));
                    favouriteRecipeList.removeIf(recipe -> recipe.contains("FR"));
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    User user = userFactory.create(username, password, ldt);
                    user.setFavoriteRecipeList(favouriteRecipeList);
                    accounts.put(username, user);
                }
            }
        }
    }

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(", ", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String favouriteRecipes
                        = user.getFavoriteRecipes().toString().replace("[", "")
                        .replace("]", "")
                        .replace(" ", "");
                String line = String.format("%s, %s, %s, %s",
                        user.getName(), user.getPassword(), user.getCreationTime(), "FR," + favouriteRecipes);
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<String> loadFavouriteRecipes(String userName) {
        ArrayList<String> recipes = accounts.get(userName).getFavoriteRecipes();
        recipes.removeIf(recipe -> recipe.contains("FR"));
        return recipes;
    }

    @Override
    public void removeRecipe(String userName, String recipeName) {
        accounts.get(userName).removeFavoriteRecipes(recipeName);
        this.save();
    }

    @Override
    public void saveRecipe(String userName, String recipeName) {
        if (!accounts.get(userName).getFavoriteRecipes().contains(recipeName)) {
            accounts.get(userName).setFavoriteRecipes(recipeName);
            this.save();
        }
    }
}
