package service.check_recipe.interface_adapter;


import interface_adapter.ViewManagerModel;
import service.check_recipe.use_case.CheckRecipeOutputBoundary;
import service.check_recipe.use_case.CheckRecipeOutputData;


public class CheckRecipePresenter implements CheckRecipeOutputBoundary {

    private final CheckRecipeViewModel checkRecipeViewModel;
    private ViewManagerModel viewManagerModel;

    public CheckRecipePresenter(ViewManagerModel viewManagerModel,CheckRecipeViewModel checkRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.checkRecipeViewModel = checkRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(CheckRecipeOutputData checkRecipeOutputData) {
        CheckRecipeState checkRecipeState = checkRecipeViewModel.getState();
        checkRecipeState.setRecipe(checkRecipeOutputData.getRecipe());
        checkRecipeViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(checkRecipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
