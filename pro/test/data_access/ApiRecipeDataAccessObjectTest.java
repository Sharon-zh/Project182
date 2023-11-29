package data_access;

import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;
//import org.junit.Test;
import service.comment.use_case.CommentDataAccessInterface;
import service.like.use_case.LikeDataAccessInterface;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Objects;


public class ApiRecipeDataAccessObjectTest {

    @Test
    public void searchResultTest() {
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        CommentDataAccessInterface commentDataAccessObject = new InMemoryRecipeDataAccessObject();
        LikeDataAccessInterface likeDataAccessObject = new InMemoryLikeRecipeDataAccessObject();
        ApiRecipeDataAccessObject apiRecipeDataAccessObject = new ApiRecipeDataAccessObject(recipeFactory, commentDataAccessObject, likeDataAccessObject);
        Map<String, Recipe> recipes =apiRecipeDataAccessObject.searchResult("Beef");
        assert Objects.equals(recipes.get("Beef Asado").getName(), "Beef Asado");
    }

    @Test
    public void hasResultTest() {
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        CommentDataAccessInterface commentDataAccessObject = new InMemoryRecipeDataAccessObject();
        LikeDataAccessInterface likeDataAccessObject = new InMemoryLikeRecipeDataAccessObject();
        ApiRecipeDataAccessObject apiRecipeDataAccessObject = new ApiRecipeDataAccessObject(recipeFactory, commentDataAccessObject, likeDataAccessObject);
        Map<String, Recipe> recipes =apiRecipeDataAccessObject.searchResult("Beef");
        boolean a = apiRecipeDataAccessObject.hasResult(recipes);
        assert a;
    }
}