package service.login.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.load_favourite_recipes.interface_adapter.LoadRecipesState;
import service.load_favourite_recipes.interface_adapter.LoadRecipesViewModel;
import service.login.use_case.LoginOutputBoundary;
import service.login.use_case.LoginOutputData;
import service.logout.interface_adapter.LogoutViewModel;
import service.recommendation.interface_adapter.RecommendationState;
import service.recommendation.interface_adapter.RecommendationViewModel;
import service.search.interface_adapter.SearchState;
import service.search.interface_adapter.SearchViewModel;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LogoutViewModel logoutViewModel;
    private final SearchViewModel searchViewModel;
    private final RecommendationViewModel recommendationViewModel;
    private final LoadRecipesViewModel loadRecipesViewModel;
    private ViewManagerModel viewManagerModel;


    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel,LogoutViewModel logoutViewModel, SearchViewModel searchViewModel,
                          RecommendationViewModel recommendationViewModel, LoadRecipesViewModel loadRecipesViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.logoutViewModel = logoutViewModel;
        this.searchViewModel = searchViewModel;
        this.recommendationViewModel = recommendationViewModel;
        this.loadRecipesViewModel = loadRecipesViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        SearchState searchState = searchViewModel.getState();
        searchState.setUsername(response.getUsername());
        this.searchViewModel.setState(searchState);
        this.searchViewModel.firePropertyChanged();

        RecommendationState recommendationState = recommendationViewModel.getState();
        recommendationState.setUsername(response.getUsername());
        this.recommendationViewModel.setState(recommendationState);
        this.recommendationViewModel.firePropertyChanged();

        LoadRecipesState loadRecipesState = loadRecipesViewModel.getState();
        loadRecipesState.setUsername(response.getUsername());
        this.loadRecipesViewModel.setState(loadRecipesState);
        this.loadRecipesViewModel.firePropertyChanged();

        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        this.loginViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(logoutViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
