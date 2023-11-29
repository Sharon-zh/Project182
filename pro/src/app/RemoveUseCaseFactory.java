package app;

import service.remove_favourite_recipe.interface_adapter.RemoveRecipeController;
import service.remove_favourite_recipe.interface_adapter.RemoveRecipePresenter;
import service.remove_favourite_recipe.interface_adapter.RemoveRecipeViewModel;
import service.remove_favourite_recipe.use_case.RemoveRecipeDataAccessInterface;
import service.remove_favourite_recipe.use_case.RemoveRecipeInputBoundary;
import service.remove_favourite_recipe.use_case.RemoveRecipeInteractor;
import service.remove_favourite_recipe.use_case.RemoveRecipeOutputBoundary;

import java.io.IOException;

public class RemoveUseCaseFactory {
    private RemoveUseCaseFactory() {}

    public static RemoveRecipeController createRemoveRecipeUseCase(
            RemoveRecipeViewModel removeRecipeViewModel,
            RemoveRecipeDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        RemoveRecipeOutputBoundary removeRecipeOutputBoundary = new RemoveRecipePresenter(removeRecipeViewModel);
        RemoveRecipeInputBoundary removeRecipeInteractor = new RemoveRecipeInteractor(
                userDataAccessObject, removeRecipeOutputBoundary);

        return new RemoveRecipeController(removeRecipeInteractor);
    }
}
