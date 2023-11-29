package api;

import data_access.ApiRecipeDataAccessObject;
import data_access.InMemoryRecipeDataAccessObject;
import data_access.LikeFileRecipeDateAccessObject;
import entity.CommonRecipeFactory;
import entity.Recipe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class search {


    public search() throws IOException {
    }


    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://www.themealdb.com/api/json/v1/1/search.php?lat=%7Blat%7D&s=Arrabiata")
                .get()
                .build();

        Response response = client.newCall(request).execute();
//        System.out.println(response);
        JSONObject responseBody = new JSONObject(response.body().string());
//        System.out.println(responseBody);
//        JSONArray meal = responseBody.getJSONArray("meals");
//        JSONObject first = meal.getJSONObject(0);
//        String id = first.getString("idMeal");
//        System.out.println(id);
        HashMap<String, String> recipe = new HashMap<String, String>();
        recipe.put("n", null);

        ApiRecipeDataAccessObject dao = new ApiRecipeDataAccessObject(new CommonRecipeFactory(),
                new InMemoryRecipeDataAccessObject(), new LikeFileRecipeDateAccessObject("xxxx"));
        Map<String, Recipe> recipes = dao.searchResult("Beetroot Soup");
        for(Recipe r: recipes.values()){
        System.out.println(r.getName());
        System.out.println(r.getCategory());
        System.out.println(r.getIngredients());
        System.out.println(r.getInstructions());
        System.out.println(r.getLikes());
        System.out.println(r.getComments());
        System.out.println(r.getImageLink());
        System.out.println(r.getYoutubeLink());
        }
    }
}

