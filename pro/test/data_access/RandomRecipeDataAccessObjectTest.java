package data_access;

import entity.CommonRecipeFactory;
import entity.RecipeFactory;
import entity.RecommendedRecipes;
import org.junit.jupiter.api.Test;
import service.comment.use_case.CommentDataAccessInterface;
import service.like.use_case.LikeDataAccessInterface;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RandomRecipeDataAccessObjectTest {

    @Test
    public void randomResultTest() {
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        CommentDataAccessInterface commentDataAccessObject = new InMemoryRecipeDataAccessObject();
        LikeDataAccessInterface likeDataAccessObject = new InMemoryLikeRecipeDataAccessObject();
        RandomRecipeDataAccessObject randomRecipeDataAccessObject = new RandomRecipeDataAccessObject(recipeFactory, commentDataAccessObject, likeDataAccessObject);
        RecommendedRecipes recipes = randomRecipeDataAccessObject.randomResult();
        assertNotNull(recipes);
    }
}
