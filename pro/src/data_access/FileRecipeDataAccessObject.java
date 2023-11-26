package data_access;

import entity.Recipe;
import entity.RecipeFactory;
import entity.User;
import entity.UserFactory;
import kotlin.collections.ArraysKt;
import service.comment.use_case.CommentDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FileRecipeDataAccessObject implements CommentDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, ArrayList<String>> comments = new HashMap<>();


    public FileRecipeDataAccessObject(String csvPath, RecipeFactory recipeFactory) throws IOException{
        csvFile = new File(csvPath);
        headers.put("recipename", 0);
        headers.put("comment", 1);
        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("recipename,username,comment");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String recipename = String.valueOf(col[headers.get("recipename")]);
                    String comment = String.valueOf(col[headers.get("comment")]);
                    if (comments.containsKey(recipename)) {
                        comments.get(recipename).add(comment);
                    } else {
                        ArrayList<String> a = new ArrayList<String>();
                        a.add(comment);
                        comments.put(recipename, a);
                    }
                }
            }
        }
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Map.Entry<String, ArrayList<String>> recipe_comments : comments.entrySet()) {
                String recipe = recipe_comments.getKey();
                for (String innercomment : recipe_comments.getValue()){
                    String line = String.format("%s,%s",
                            recipe, innercomment);
                    writer.write(line);
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(String recipe, String comment){
        if (comments.containsKey(recipe)) {
            comments.get(recipe).add(comment);
        } else {
            ArrayList<String> a = new ArrayList<String>();
            a.add(comment);
            comments.put(recipe, a);
        }
        this.save();
    }

    @Override
    public ArrayList<String> getComments(String recipeName) {
        if (comments.containsKey(recipeName)) {
            return comments.get(recipeName);
        } else {
            return new ArrayList<>();
        }
    }


}
