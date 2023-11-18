package data_access;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import service.search.use_case.SearchDataAccessInterface;

public class ApiRecipeDataAccessObject implements SearchDataAccessInterface {
    private final String keyword;

    public ApiRecipeDataAccessObject(String keyword) {
        this.keyword = keyword;
    }

    private JSONObject content(String keyword) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String newKeyword = keyword.replace(" ", "_");
        String link = "https://www.themealdb.com/api/json/v1/1/search.php?lat=%7Blat%7D&s=" + newKeyword;
        Request request = (new Request.Builder()).url(link).get().build();
        Response response = client.newCall(request).execute();
        JSONObject responseBody = new JSONObject(response.body().string());
        return responseBody;
    }

    private HashMap<String, Object> filter(JSONObject recipe_json, int index) {
        JSONObject index_recipe = recipe_json.getJSONArray("meals").getJSONObject(index);
        String name = index_recipe.getString("strMeal");
        String category = index_recipe.getString("strCategory");
        String instructions = index_recipe.getString("strInstructions");
        String image_link = index_recipe.getString("strMealThumb");
        String youtube_link = index_recipe.getString("strYoutube");
        HashMap<String, String> ingredients = new HashMap();

        for(int i = 1; i < 21; ++i) {
            String key_ingre = "strIngredient" + Integer.toString(i);
            String key_mea = "strMeasure" + Integer.toString(i);
            if (recipe_json.isNull(key_mea) || index_recipe.getString(key_mea) == "") {
                break;
            }

            String var10000 = index_recipe.getString(key_mea);
            String contain = var10000 + " " + index_recipe.getString(key_ingre);
            ingredients.put(key_ingre, contain);
        }

        int likes = 0;
        HashMap<String, String> comments = new HashMap();
        HashMap<String, Object> recipe = new HashMap();
        recipe.put("name", name);
        recipe.put("category", category);
        recipe.put("instructions", instructions);
        recipe.put("ingredients", ingredients);
        recipe.put("likes", Integer.valueOf(likes));
        recipe.put("comments", comments);
        recipe.put("image_link", image_link);
        recipe.put("youtube_link", youtube_link);
        return recipe;
    }

    public Map<String, HashMap<String, Object>> searchResult(String searchWord) throws IOException {
        JSONObject recipe_json = this.content(this.keyword);
        Map<String, HashMap<String, Object>> recipeMap = new HashMap();
        if (recipe_json.has("meals") && !recipe_json.isNull("meals")) {
            int length = recipe_json.getJSONArray("meals").length();

            for(int i = 0; i < length; ++i) {
                HashMap<String, Object> recipe = this.filter(recipe_json, i);
                String name = (String)recipe.get("name");
                recipeMap.put(name, recipe);
            }
        }

        return recipeMap;
    }

    public boolean hasResult(Map result) {
        return result != null && !result.isEmpty();
    }
}
