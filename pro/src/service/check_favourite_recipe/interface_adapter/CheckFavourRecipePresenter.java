package service.check_favourite_recipe.interface_adapter;


import interface_adapter.ViewManagerModel;
import service.check_favourite_recipe.use_case.CheckFavourRecipeOutputBoundary;
import service.check_favourite_recipe.use_case.CheckFavourRecipeOutputData;


public class CheckFavourRecipePresenter implements CheckFavourRecipeOutputBoundary {

    private final CheckFavourRecipeViewModel checkFavourRecipeViewModel;
    private ViewManagerModel viewManagerModel;

    public CheckFavourRecipePresenter(ViewManagerModel viewManagerModel, CheckFavourRecipeViewModel checkFavourRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.checkFavourRecipeViewModel = checkFavourRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(CheckFavourRecipeOutputData checkFavourRecipeOutputData) {
        CheckFavourRecipeState checkFavourRecipeState = checkFavourRecipeViewModel.getState();
        checkFavourRecipeState.setRecipe(checkFavourRecipeOutputData.getRecipe());
        checkFavourRecipeState.setNoResultError(null);
        checkFavourRecipeViewModel.setState(checkFavourRecipeState);
        checkFavourRecipeViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(checkFavourRecipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        CheckFavourRecipeState checkFavourRecipeState = checkFavourRecipeViewModel.getState();
        checkFavourRecipeState.setNoResultError(error);
        checkFavourRecipeViewModel.firePropertyChanged();
    }
}
