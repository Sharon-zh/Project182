package service.load_favourite_recipes.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.load_favourite_recipes.use_case.LoadRecipesOutputBoundary;
import service.load_favourite_recipes.use_case.LoadRecipesOutputData;

public class LoadRecipesPresenter implements LoadRecipesOutputBoundary {
    private final LoadRecipesViewModel loadRecipesViewModel;
    private final ViewManagerModel viewManagerModel;


    public LoadRecipesPresenter(LoadRecipesViewModel loadRecipesViewModel, ViewManagerModel viewManagerModel) {
        this.loadRecipesViewModel = loadRecipesViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(LoadRecipesOutputData loadRecipesOutputData) {
        LoadRecipesState loadRecipesState = loadRecipesViewModel.getState();
        loadRecipesState.setFavouriteRecipes(loadRecipesOutputData.getFavouriteRecipes());
        loadRecipesViewModel.setState(loadRecipesState);
        loadRecipesViewModel.firePropertyChanged();

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
