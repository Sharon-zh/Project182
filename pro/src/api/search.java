package api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

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
        System.out.println(response);
        JSONObject responseBody = new JSONObject(response.body().string());
        System.out.println(responseBody);
    }
}

