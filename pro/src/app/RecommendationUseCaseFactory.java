package app;

import interface_adapter.ViewManagerModel;
import service.check_recipe.interface_adapter.CheckRecipeController;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.logged_in.interface_adapter.LoggedInViewModel;
import service.recommendation.interface_adapter.RecommendationController;
import service.recommendation.interface_adapter.RecommendationPresenter;
import service.recommendation.interface_adapter.RecommendationViewModel;
import service.recommendation.use_case.RecommendationDataAccessInterface;
import service.recommendation.use_case.RecommendationInteractor;
import service.return_to_main.interface_adapter.ReturnToMainController;
import service.return_to_main.interface_adapter.ReturnToMainViewModel;
import app.CheckRecipeUseCaseFactory;
import app.ReturnToMainUseCaseFactory;
import view.RecommendView;
import javax.swing.*;
import java.io.IOException;

public class RecommendationUseCaseFactory {
    private RecommendationUseCaseFactory(){}
    public static RecommendView create(
            ViewManagerModel viewManagerModel, CheckRecipeViewModel checkRecipeViewModel, RecommendationViewModel recommendationViewModel, RecommendationDataAccessInterface recommendationDataAccessObject, ReturnToMainViewModel returnToMainViewModel, LoggedInViewModel loggedInViewModel) {

        try {
            CheckRecipeController checkRecipeController = CheckRecipeUseCaseFactory.createCheckRecipeUseCase(viewManagerModel, checkRecipeViewModel);
            ReturnToMainController returnToMainController = ReturnToMainUseCaseFactory.createReturnToMainUseCase(returnToMainViewModel, loggedInViewModel, viewManagerModel);
            RecommendationController recommendationController = createUserRecommendationUseCase(viewManagerModel, checkRecipeViewModel, recommendationViewModel, recommendationDataAccessObject);
            return new RecommendView(recommendationController, checkRecipeController, returnToMainController, recommendationViewModel, checkRecipeViewModel, returnToMainViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static RecommendationController createUserRecommendationUseCase(ViewManagerModel viewManagerModel, CheckRecipeViewModel checkRecipeViewModel, RecommendationViewModel recommendationViewModel, RecommendationDataAccessInterface recommendationDataAccessInterface) throws IOException {
        RecommendationPresenter recommendationPresenter = new RecommendationPresenter(recommendationViewModel, checkRecipeViewModel, viewManagerModel);
        RecommendationInteractor recommendationInteractor = new RecommendationInteractor(recommendationDataAccessInterface, recommendationPresenter);
        RecommendationController recommendationController = new RecommendationController(recommendationInteractor);

        return recommendationController;
    }
}