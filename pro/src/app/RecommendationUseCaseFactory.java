package app;

import interface_adapter.ViewManagerModel;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.recommendation.interface_adapter.RecommendationController;
import service.recommendation.interface_adapter.RecommendationPresenter;
import service.recommendation.interface_adapter.RecommendationViewModel;
import service.recommendation.use_case.RecommendationDataAccessInterface;
import service.recommendation.use_case.RecommendationInteractor;

import javax.swing.*;
import java.io.IOException;

public class RecommendationUseCaseFactory {
    private RecommendationUseCaseFactory(){}
    public static RecommendationView create(
            ViewManagerModel viewManagerModel, CheckRecipeViewModel checkRecipeViewModel, RecommendationViewModel recommendationViewModel, RecommendationDataAccessInterface recommendationDataAccessInterface) {

        try {
            RecommendationController recommendationController = createUserRecommendationUseCase(viewManagerModel, checkRecipeViewModel, recommendationViewModel, recommendationDataAccessInterface);
            return new RecommendationView(recommendationController, recommendationViewModel);
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
