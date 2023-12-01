package service.load_favourite_recipes.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.check_favourite_recipe.interface_adapter.CheckFavourRecipeState;
import service.check_favourite_recipe.interface_adapter.CheckFavourRecipeViewModel;
import service.load_favourite_recipes.use_case.LoadRecipesOutputBoundary;
import service.load_favourite_recipes.use_case.LoadRecipesOutputData;

public class LoadRecipesPresenter implements LoadRecipesOutputBoundary {
    private final LoadRecipesViewModel loadRecipesViewModel;
    private final ViewManagerModel viewManagerModel;
    private final CheckFavourRecipeViewModel checkFavourRecipeViewModel;


    public LoadRecipesPresenter(LoadRecipesViewModel loadRecipesViewModel, ViewManagerModel viewManagerModel, CheckFavourRecipeViewModel checkFavourRecipeViewModel) {
        this.loadRecipesViewModel = loadRecipesViewModel;
        this.viewManagerModel = viewManagerModel;
        this.checkFavourRecipeViewModel = checkFavourRecipeViewModel;
    }


    @Override
    public void prepareSuccessView(LoadRecipesOutputData loadRecipesOutputData) {
        LoadRecipesState loadRecipesState = loadRecipesViewModel.getState();
        loadRecipesState.setFavouriteRecipes(loadRecipesOutputData.getFavouriteRecipes());
        loadRecipesViewModel.setState(loadRecipesState);
        loadRecipesViewModel.firePropertyChanged();

        CheckFavourRecipeState checkFavourRecipeState = checkFavourRecipeViewModel.getState();
        checkFavourRecipeState.setUsername(loadRecipesState.getUsername());
        this.checkFavourRecipeViewModel.setState(checkFavourRecipeState);
        this.checkFavourRecipeViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loadRecipesViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String emptyMessage) {
        LoadRecipesState loadRecipesState = loadRecipesViewModel.getState();
        loadRecipesState.setEmptyMessage(emptyMessage);
        loadRecipesViewModel.firePropertyChanged();
    }
}
