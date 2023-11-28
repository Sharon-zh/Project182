package app;

import interface_adapter.ViewManagerModel;
import service.save_favourite_recipe.interface_adapter.SaveRecipeController;
import service.save_favourite_recipe.interface_adapter.SaveRecipePresenter;
import service.save_favourite_recipe.interface_adapter.SaveRecipeViewModel;
import service.save_favourite_recipe.use_case.SaveRecipeDataAccessInterface;
import service.save_favourite_recipe.use_case.SaveRecipeInputBoundary;
import service.save_favourite_recipe.use_case.SaveRecipeInteractor;
import service.save_favourite_recipe.use_case.SaveRecipeOutputBoundary;
import view.RecipeView;

import javax.swing.*;
import java.io.IOException;

public class SaveFavouriteRecipeUseCaseFactory {
    private SaveFavouriteRecipeUseCaseFactory() {}

    public static RecipeView create(
            ViewManagerModel viewManagerModel,
            SaveRecipeViewModel saveRecipeViewModel,
            SaveRecipeDataAccessInterface userDataAccessObject) {

        try {
            SaveRecipeController saveRecipeController = createSaveRecipeUseCase(viewManagerModel, saveRecipeViewModel, userDataAccessObject);
            return new RecipeView(saveRecipeViewModel, saveRecipeController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Action failed, please try again.");
        }

        return null;
    }

    private static SaveRecipeController createSaveRecipeUseCase(
            ViewManagerModel viewManagerModel,
            SaveRecipeViewModel saveRecipeViewModel,
            SaveRecipeDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SaveRecipeOutputBoundary saveRecipeOutputBoundary = new SaveRecipePresenter(saveRecipeViewModel);

        SaveRecipeInputBoundary saveRecipeInteractor = new SaveRecipeInteractor(
                userDataAccessObject, saveRecipeOutputBoundary);

        return new SaveRecipeController(saveRecipeInteractor);
    }
}
