package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entity.Recipe;
import entity.RecipeFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import service.check_favourite_recipe.use_case.CheckFavourRecipeDataAccessInterface;
import service.comment.use_case.CommentDataAccessInterface;
import service.like.use_case.LikeDataAccessInterface;
import service.search.use_case.SearchDataAccessInterface;

public class ApiRecipeDataAccessObject implements SearchDataAccessInterface, CheckFavourRecipeDataAccessInterface {

    private final RecipeFactory recipeFactory;

    private final CommentDataAccessInterface commentDataAccessInterface;

    private final LikeDataAccessInterface likeDataAccessInterface;

    public ApiRecipeDataAccessObject(RecipeFactory recipeFactory, CommentDataAccessInterface commentDataAccessInterface, LikeDataAccessInterface likeDataAccessInterface) {
        this.recipeFactory = recipeFactory;
        this.commentDataAccessInterface = commentDataAccessInterface;
        this.likeDataAccessInterface = likeDataAccessInterface;
    }

    private JSONObject searchInMealDB(String keyword) {
        OkHttpClient client = new OkHttpClient();
        String newKeyword = keyword.replace(" ", "_");
        String link = "https://www.themealdb.com/api/json/v1/1/search.php?lat=%7Blat%7D&s=" + newKeyword;

        try {
            Request request = new Request.Builder().url(link).get().build();
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            return responseBody;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private Recipe convertIntoRecipe(JSONObject recipe_json, int index) {
        JSONObject index_recipe = recipe_json.getJSONArray("meals").getJSONObject(index);
        String name = index_recipe.getString("strMeal");
        String category = index_recipe.getString("strCategory");
        String instructions = index_recipe.getString("strInstructions");
        String image_link = index_recipe.getString("strMealThumb");
        String youtube_link = index_recipe.getString("strYoutube");
        HashMap<String, String> ingredients = new HashMap();

        for(int i = 1; i < 21; ++i) {
            String key_ingre = "strIngredient" + i;
            String key_mea = "strMeasure" + i;
            if (!index_recipe.isNull(key_ingre)) {
                if (!index_recipe.getString(key_ingre).isEmpty() && !index_recipe.getString(key_ingre).trim().isEmpty()){
                    String var10000 = index_recipe.getString(key_mea);
                    String contain = var10000 + " " + index_recipe.getString(key_ingre);
                    ingredients.put(key_ingre, contain);
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        int likes = likeDataAccessInterface.get(name);
        ArrayList<String> comments = commentDataAccessInterface.getComments(name);
        Recipe recipe = recipeFactory.create(name, category, instructions, ingredients, likes, comments, image_link, youtube_link);
        return recipe;
    }

    public Map<String, Recipe> searchResult(String searchWord){
        JSONObject recipe_json = this.searchInMealDB(searchWord);
        Map<String, Recipe> recipeMap = new HashMap();
        if (recipe_json.has("meals") && !recipe_json.isNull("meals")) {
            int length = recipe_json.getJSONArray("meals").length();

            for(int i = 0; i < length; ++i) {
                Recipe recipe = this.convertIntoRecipe(recipe_json, i);
                String name = (String)recipe.getName();
                recipeMap.put(name, recipe);
            }
        }

        return recipeMap;
    }

    public boolean hasResult(Map result) {
        return result != null && !result.isEmpty();
    }
}
