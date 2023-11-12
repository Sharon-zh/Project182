package search.data_access;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;


public class InMemoryRecipeDataAccessObject {
    private final String keyword;

    public InMemoryRecipeDataAccessObject(String keyword){
        this.keyword = keyword;
    }

    public JSONObject content(String keyword) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String newKeyword = keyword.replace(" ", "_");
        String link ="https://www.themealdb.com/api/json/v1/1/search.php?lat=%7Blat%7D&s=" + newKeyword;

        Request request = new Request.Builder()
                .url(link)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        JSONObject responseBody = new JSONObject(response.body().string());
        return responseBody;
    }

    public HashMap<String, Object> filter(JSONObject recipe_json, int index){
        JSONObject index_recipe = recipe_json.getJSONArray("meals").getJSONObject(index);
        String name = index_recipe.getString("strMeal");
        String category = index_recipe.getString("strCategory");
        String instructions = index_recipe.getString("strInstructions");
        String image_link = index_recipe.getString("strMealThumb");
        String youtube_link = index_recipe.getString("strYoutube");
        HashMap<String, String> ingredients = new HashMap<String, String>();
        for (int i = 1; i < 21; i++){
            String key_ingre = "strIngredient" + Integer.toString(i);
            String key_mea = "strMeasure" + Integer.toString(i);
            if (index_recipe.getString(key_ingre) != ""){
                String contain = index_recipe.getString(key_mea) + " " + index_recipe.getString(key_ingre);
                ingredients.put(key_ingre, contain);
            } else {
                break;
            }
        }
        int likes = 0;
        HashMap<String, String> comments = new HashMap<String, String>();
        HashMap<String, Object> recipe = new HashMap<>();
        recipe.put("name", name);
        recipe.put("category", category);
        recipe.put("instructions", instructions);
        recipe.put("ingredients", ingredients);
        recipe.put("likes", likes);
        recipe.put("comments", comments);
        recipe.put("image_link", image_link);
        recipe.put("youtube_link", youtube_link);
        return recipe;
    }


}
