package service.load_favourite_recipes.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.load_favourite_recipes.use_case.LoadRecipesOutputBoundary;
import service.load_favourite_recipes.use_case.LoadRecipesOutputData;

import java.util.ArrayList;

public class LoadRecipesPresenter implements LoadRecipesOutputBoundary {
    private final LoadRecipesViewModel loadRecipesViewModel;

    public LoadRecipesPresenter(LoadRecipesViewModel loadRecipesViewModel) {
        this.loadRecipesViewModel = loadRecipesViewModel;
    }


    @Override
    public void prepareSuccessView(LoadRecipesOutputData loadRecipesOutputData) {
        LoadRecipesState loadRecipesState = loadRecipesViewModel.getState();
        loadRecipesState.setFavouriteRecipes(loadRecipesOutputData.getFavouriteRecipes());
        loadRecipesViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String emptyMessage) {
        LoadRecipesState loadRecipesState = loadRecipesViewModel.getState();
        loadRecipesState.setEmptyMessage(emptyMessage);
        loadRecipesViewModel.firePropertyChanged();
    }
}
