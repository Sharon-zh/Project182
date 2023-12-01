package data_access;

import entity.CommonRecipeFactory;
import entity.RecipeFactory;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FileRecipeDataAccessObjectTest {

    @Test
    public void save() throws IOException {
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        FileRecipeDataAccessObject fileRecipeDataAccessObject = new FileRecipeDataAccessObject("./comment.csv", recipeFactory);
        fileRecipeDataAccessObject.save("beef", "h: good");
        fileRecipeDataAccessObject.save("beef", "a: hi");
        int lineCount = 0;
        BufferedReader reader = new BufferedReader(new FileReader("./comment.csv"));
        while (reader.readLine() != null) {
            lineCount++;
        }
        assert lineCount >= 2;
    }

    @Test
    public void getComments() throws IOException {
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        FileRecipeDataAccessObject fileRecipeDataAccessObject = new FileRecipeDataAccessObject("./comment.csv", recipeFactory);
        fileRecipeDataAccessObject.save("egg", "a: good!");
        ArrayList<String> comment = fileRecipeDataAccessObject.getComments("egg");
        assert comment.contains("a: good!");
    }
}