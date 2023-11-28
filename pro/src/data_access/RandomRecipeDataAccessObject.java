package data_access;

import entity.Recipe;
import entity.RecipeFactory;
import entity.RecommendedRecipes;
import entity.RecommendedRecipesService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import service.comment.use_case.CommentDataAccessInterface;
import service.like.use_case.LikeDataAccessInterface;
import service.recommendation.use_case.RecommendationDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class RandomRecipeDataAccessObject implements RecommendationDataAccessInterface {
    private final RecipeFactory recipeFactory;
    private final CommentDataAccessInterface commentDataAccessInterface;
    private final LikeDataAccessInterface likeDataAccessInterface;

    public RandomRecipeDataAccessObject(RecipeFactory recipeFactory, CommentDataAccessInterface commentDataAccessInterface, LikeDataAccessInterface likeDataAccessInterface) {
        this.recipeFactory = recipeFactory;
        this.likeDataAccessInterface = likeDataAccessInterface;
        this.commentDataAccessInterface = commentDataAccessInterface;
    }

    private JSONObject random() {
        OkHttpClient client = new OkHttpClient();
        String link = "https://www.themealdb.com/api/json/v1/1/random.php";

        try {
            Request request = new Request.Builder().url(link).get().build();
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            return responseBody;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Recipe convertIntoRecipe(JSONObject recipeJson) {
        JSONObject indexRecipe = recipeJson.getJSONArray("meals").getJSONObject(0);
        String name = indexRecipe.getString("strMeal");
        String category = indexRecipe.getString("strCategory");
        String instructions = indexRecipe.getString("strInstructions");
        String imageLink = indexRecipe.getString("strMealThumb");
        String youtubeLink = indexRecipe.getString("strYoutube");
        HashMap<String, String> ingredients = new HashMap();

        for(int i = 1; i < 21; ++i) {
            String keyIngre = "strIngredient" + i;
            String keyMea = "strMeasure" + i;
            if (!indexRecipe.isNull(keyIngre)) {
                if (!indexRecipe.getString(keyIngre).isEmpty() && !indexRecipe.getString(keyIngre).trim().isEmpty()){
                    String var10000 = indexRecipe.getString(keyMea);
                    String contain = var10000 + " " + indexRecipe.getString(keyIngre);
                    ingredients.put(keyIngre, contain);
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        int likes = likeDataAccessInterface.get(name);
        ArrayList<String> comments = commentDataAccessInterface.getComments(name);
        Recipe recipe = recipeFactory.create(name, category, instructions, ingredients, likes, comments, imageLink, youtubeLink);
        return recipe;
    }


    @Override
    public RecommendedRecipes randomResult() {
        JSONObject recipeJson = this.random();
        Recipe recipe = this.convertIntoRecipe(recipeJson);
        ArrayList<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe);

        while (recipeList.size() < 10) {
            JSONObject recipeJsonNew = this.random();
            Recipe recipeNew = this.convertIntoRecipe(recipeJsonNew);
            if (recipeList.contains(recipeNew)) {
                break;
            } else {
                recipeList.add(recipeNew);
            }
        }

        return new RecommendedRecipesService(recipeList);
    }
}
