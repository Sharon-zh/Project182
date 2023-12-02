package data_access;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LikeRecipeDataAccessObjectTest {

    private ArrayList<String> readLines() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./like_num.csv"));
        ArrayList<String> lines = new ArrayList<String>();
        reader.readLine();
        String line = reader.readLine();
        while (line != null) {
            lines.add(line);
            line = reader.readLine();
        }
        return lines;
    }
    @Test
    public void like() throws IOException {
        LikeFileRecipeDateAccessObject likeFileRecipeDateAccessObject = new LikeFileRecipeDateAccessObject("./like_num.csv");
        likeFileRecipeDateAccessObject.like("Spicy Arrabiata Penne", "zth");
        ArrayList<String> lines1 = readLines();
        assertTrue(lines1.get(0).contains("zth"));
        assertEquals(1, likeFileRecipeDateAccessObject.get("Spicy Arrabiata Penne"));
        likeFileRecipeDateAccessObject.like("Spicy Arrabiata Penne", "zth");
        ArrayList<String> lines2 = readLines();
        assertFalse(lines2.get(0).contains("zth"));
        assertEquals(0, likeFileRecipeDateAccessObject.get("Spicy Arrabiata Penne"));
        likeFileRecipeDateAccessObject.like("Beef Asado", "zlt");
        assertEquals(1, likeFileRecipeDateAccessObject.get("Beef Asado"));
        likeFileRecipeDateAccessObject.like("Beef Asado", "zlt");
    }

    @Test
    public void get() throws IOException {
        LikeFileRecipeDateAccessObject likeFileRecipeDateAccessObject = new LikeFileRecipeDateAccessObject("./like_num.csv");
        assertEquals(0, likeFileRecipeDateAccessObject.get("abc"));
    }
}
