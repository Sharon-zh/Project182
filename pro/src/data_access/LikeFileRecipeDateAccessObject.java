package data_access;

import service.like.use_case.LikeDataAccessInterface;

import java.io.*;
import java.util.*;

public class LikeFileRecipeDateAccessObject implements LikeDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, ArrayList<String>> like = new HashMap<>();

    public LikeFileRecipeDateAccessObject(String csvPath)  throws IOException {
        csvFile = new File(csvPath);
        headers.put("recipe", 0);
        headers.put("like", 1);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("recipe, like");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",\\s");
                    String recipe = String.valueOf(col[headers.get("recipe")]);
                    String likeUsername = String.valueOf(col[headers.get("like")]);
                    String[] splitArray = likeUsername.split(","); // Split the string by comma and optional space
                    ArrayList<String> likeList= new ArrayList<String>(Arrays.asList(splitArray));// Convert array to List
                    likeList.removeIf(people -> people.contains("LUN"));
                    like.put(recipe, likeList);
                }
            }
        }
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(", ", headers.keySet()));
            writer.newLine();

            for (String recipe : like.keySet()) {
                String likeUsername
                        = like.get(recipe).toString().replace("[", "")
                        .replace("]", "")
                        .replace(" ", "");
                String line = String.format("%s, %s",
                        recipe, "LUN," + likeUsername);
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void like(String recipe, String username) {
        if (like.keySet().stream().toList().contains(recipe)) {
            if (like.get(recipe).contains(username)){
                like.get(recipe).remove(username);
            } else {
                like.get(recipe).add(username);
            }
        } else {
            ArrayList<String> likeList = new ArrayList<>();
            likeList.add(username);
            like.put(recipe, likeList);
        }
        this.save();
    }

    @Override
    public Integer get(String recipe) {
        if (like.keySet().stream().toList().contains(recipe)) {
            return like.get(recipe).size();
        } else {
            return 0;
        }
    }
}
