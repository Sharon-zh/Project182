package service.load_favourite_recipes.use_case;

public interface LoadRecipesOutputBoundary {
    void prepareSuccessView(LoadRecipesOutputData loadRecipesOutputData);

    void prepareFailView(String emptyMessage);
}
