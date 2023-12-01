package app;


import interface_adapter.ViewManagerModel;
import service.check_favourite_recipe.interface_adapter.CheckFavourRecipeController;
import service.check_favourite_recipe.interface_adapter.CheckFavourRecipePresenter;
import service.check_favourite_recipe.interface_adapter.CheckFavourRecipeViewModel;
import service.check_favourite_recipe.use_case.CheckFavourRecipeDataAccessInterface;
import service.check_favourite_recipe.use_case.CheckFavourRecipeInputBoundary;
import service.check_favourite_recipe.use_case.CheckFavourRecipeInteractor;
import service.check_favourite_recipe.use_case.CheckFavourRecipeOutputBoundary;
import service.load_favourite_recipes.interface_adapter.LoadRecipesViewModel;
import service.login.interface_adapter.LoginViewModel;
import service.logout.interface_adapter.LogoutViewModel;
import service.remove_favourite_recipe.interface_adapter.RemoveRecipeController;
import service.remove_favourite_recipe.interface_adapter.RemoveRecipeViewModel;
import service.remove_favourite_recipe.use_case.RemoveRecipeDataAccessInterface;
import service.return_to_main.interface_adapter.ReturnToMainController;
import service.return_to_main.interface_adapter.ReturnToMainViewModel;
import view.FavouriteRecipesView;


import javax.swing.*;
import java.io.IOException;

public class CheckFavourRecipeUseCaseFactory {
    private CheckFavourRecipeUseCaseFactory() {}
    public static FavouriteRecipesView create(
            ViewManagerModel viewManagerModel, CheckFavourRecipeViewModel checkFavourRecipeViewModel,
            CheckFavourRecipeDataAccessInterface checkFavourRecipeDataAccessObject,
            LoadRecipesViewModel loadRecipesViewModel,
            ReturnToMainViewModel returnToMainViewModel,
            RemoveRecipeViewModel removeRecipeViewModel,
            LoginViewModel loginViewModel,
            RemoveRecipeDataAccessInterface removeRecipeDataAccessObject,
            LogoutViewModel logoutViewModel) {

        try {
            CheckFavourRecipeController checkFavourRecipeController = createCheckFavourRecipeUseCase(viewManagerModel,
                    checkFavourRecipeViewModel, checkFavourRecipeDataAccessObject);
            ReturnToMainController returnToMainController = ReturnToMainUseCaseFactory.createReturnToMainUseCase(
                    returnToMainViewModel, logoutViewModel, viewManagerModel);
            RemoveRecipeController removeRecipeController = RemoveUseCaseFactory.createRemoveRecipeUseCase(removeRecipeViewModel, removeRecipeDataAccessObject);
            return new FavouriteRecipesView(loadRecipesViewModel, returnToMainViewModel, removeRecipeViewModel,
                    loginViewModel, checkFavourRecipeViewModel, returnToMainController,  removeRecipeController,
                    checkFavourRecipeController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open recipe data file.");
        }

        return null;
    }
    public static CheckFavourRecipeController createCheckFavourRecipeUseCase(ViewManagerModel viewManagerModel,
                                                                             CheckFavourRecipeViewModel checkFavourRecipeViewModel,
                                                                             CheckFavourRecipeDataAccessInterface checkFavourRecipeDataAccessObject)
            throws IOException {
        CheckFavourRecipeOutputBoundary checkFavourRecipeOutputBoundary =
                new CheckFavourRecipePresenter(viewManagerModel, checkFavourRecipeViewModel);
        CheckFavourRecipeInputBoundary checkFavourRecipeInteractor =
                new CheckFavourRecipeInteractor(checkFavourRecipeDataAccessObject, checkFavourRecipeOutputBoundary);
        return new CheckFavourRecipeController(checkFavourRecipeInteractor);
    }

}
