package service.load_favourite_recipes.use_case;


import java.util.ArrayList;

public class LoadRecipesInteractor implements LoadRecipesInputBoundary {
    final LoadRecipesDataAccessInterface userDataAccessObject;
    final LoadRecipesOutputBoundary userPresenter;
    public LoadRecipesInteractor(LoadRecipesDataAccessInterface loadRecipesDataAccessInterface,
                                 LoadRecipesOutputBoundary loadRecipesOutputBoundary) {
        this.userDataAccessObject = loadRecipesDataAccessInterface;
        this.userPresenter = loadRecipesOutputBoundary;
    }

    @Override
    public void execute(LoadRecipesInputData loadRecipesInputData) {
            String userName = loadRecipesInputData.getUserName();
            ArrayList<String> favouriteRecipes = userDataAccessObject.loadFavouriteRecipes(userName);

            if (favouriteRecipes.isEmpty()){
                userPresenter.prepareFailView("You haven't saved any recipe.");

            }else{
                LoadRecipesOutputData loadRecipesOutputData = new LoadRecipesOutputData(favouriteRecipes, false);
                userPresenter.prepareSuccessView(loadRecipesOutputData);
            }
        }
}
