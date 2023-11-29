package data_access;

import entity.CommonRecipeFactory;
import data_access.FileRecipeDataAccessObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FileRecipeDataAccessObjectTest {
    @Test
    void FileRecipeDAOTest() throws IOException {
        FileRecipeDataAccessObject fileRecipeDataAccessObject = new FileRecipeDataAccessObject("./comment.csv", new CommonRecipeFactory());

    }
}
