package service.load_favourite_recipes.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.load_favourite_recipes.use_case.LoadRecipesOutputBoundary;
import service.load_favourite_recipes.use_case.LoadRecipesOutputData;

import java.util.ArrayList;

public class LoadRecipesPresenter implements LoadRecipesOutputBoundary {
    private final LoadRecipesViewModel loadRecipesViewModel;
    private ViewManagerModel viewManagerModel;



    public LoadRecipesPresenter(LoadRecipesViewModel loadRecipesViewModel, ViewManagerModel viewManagerModel) {
        this.loadRecipesViewModel = loadRecipesViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(LoadRecipesOutputData loadRecipesOutputData) {
        LoadRecipesState loadRecipesState = loadRecipesViewModel.getState();
        ArrayList<String> favouriteRecipes = loadRecipesOutputData.getFavouriteRecipes();
        loadRecipesState.setFavouriteRecipes(favouriteRecipes);
        loadRecipesViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loadRecipesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String emptyMessage) {
        LoadRecipesState loadRecipesState = loadRecipesViewModel.getState();
        loadRecipesState.setEmptyMessage(emptyMessage);
        loadRecipesViewModel.firePropertyChanged();
    }

}
