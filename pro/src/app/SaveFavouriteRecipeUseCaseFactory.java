package app;

import interface_adapter.ViewManagerModel;
import service.check_recipe.interface_adapter.CheckRecipeController;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.comment.interface_adapter.CommentController;
import service.comment.interface_adapter.CommentViewModel;
import service.comment.use_case.CommentDataAccessInterface;
import service.like.interface_adapter.LikeController;
import service.like.interface_adapter.LikeViewModel;
import service.like.use_case.LikeDataAccessInterface;
import service.logout.interface_adapter.LogoutViewModel;
import service.return_to_main.interface_adapter.ReturnToMainController;
import service.return_to_main.interface_adapter.ReturnToMainViewModel;
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
            CheckRecipeViewModel checkRecipeViewModel,
            LikeViewModel likeViewModel, LikeDataAccessInterface likeDataAccessObject, CommentViewModel commentViewModel, CommentDataAccessInterface commentDataAccessObject,
            ReturnToMainViewModel returnToMainViewModel, LogoutViewModel logoutViewModel, SaveRecipeViewModel saveRecipeViewModel, SaveRecipeDataAccessInterface saveRecipeDataAccessObject){
        try {
            CheckRecipeController checkRecipeController = CheckRecipeUseCaseFactory.createCheckRecipeUseCase(viewManagerModel, checkRecipeViewModel);
            LikeController likeController = LikeUseCaseFactory.createLikeUseCase(likeViewModel, likeDataAccessObject);
            CommentController commentController = CommentUseCaseFactory.createCommentUseCase(viewManagerModel, commentViewModel, commentDataAccessObject);
            ReturnToMainController returnToMainController = ReturnToMainUseCaseFactory.createReturnToMainUseCase(returnToMainViewModel, logoutViewModel, viewManagerModel);
            SaveRecipeController saveRecipeController = SaveFavouriteRecipeUseCaseFactory.createSaveRecipeUseCase(viewManagerModel, saveRecipeViewModel, saveRecipeDataAccessObject);
            return new RecipeView(checkRecipeViewModel, checkRecipeController, likeController, commentViewModel, commentController, returnToMainController, saveRecipeController);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open recipe data file.");
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
