package app;

import interface_adapter.ViewManagerModel;
import service.load_favourite_recipes.interface_adapter.LoadRecipesController;
import service.load_favourite_recipes.interface_adapter.LoadRecipesPresenter;
import service.load_favourite_recipes.interface_adapter.LoadRecipesViewModel;
import service.load_favourite_recipes.use_case.LoadRecipesDataAccessInterface;
import service.load_favourite_recipes.use_case.LoadRecipesInputBoundary;
import service.load_favourite_recipes.use_case.LoadRecipesInteractor;
import service.load_favourite_recipes.use_case.LoadRecipesOutputBoundary;

import java.io.IOException;

public class LoadRecipesUseCaseFactory {
    private LoadRecipesUseCaseFactory() {}
    public static LoadRecipesController createLoadRecipesUseCase(ViewManagerModel viewManagerModel, LoadRecipesViewModel loadRecipesViewModel, LoadRecipesDataAccessInterface loadRecipesDataAccessObject) throws IOException{
        LoadRecipesOutputBoundary loadRecipesOutputBoundary = new LoadRecipesPresenter(loadRecipesViewModel);
        LoadRecipesInputBoundary loadRecipesInputBoundary = new LoadRecipesInteractor(loadRecipesDataAccessObject, loadRecipesOutputBoundary);
        return new LoadRecipesController(loadRecipesInputBoundary);
    }
}
