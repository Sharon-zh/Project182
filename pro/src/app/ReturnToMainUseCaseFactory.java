package app;


import interface_adapter.ViewManagerModel;
import service.login.interface_adapter.LoginViewModel;
import service.logout.interface_adapter.LogoutViewModel;
import service.return_to_main.interface_adapter.ReturnToMainController;
import service.return_to_main.interface_adapter.ReturnToMainPresenter;
import service.return_to_main.interface_adapter.ReturnToMainViewModel;
import service.return_to_main.use_case.ReturnToMainInputBoundary;
import service.return_to_main.use_case.ReturnToMainInteractor;
import service.return_to_main.use_case.ReturnToMainOutputBoundary;

public class ReturnToMainUseCaseFactory {
    private ReturnToMainUseCaseFactory() {}
    public static ReturnToMainController createReturnToMainUseCase(ReturnToMainViewModel returnToMainViewModel, LogoutViewModel logoutViewModel, ViewManagerModel viewManagerModel){
        ReturnToMainOutputBoundary returnToMainOutputBoundary = new ReturnToMainPresenter(returnToMainViewModel, logoutViewModel, viewManagerModel);
        ReturnToMainInputBoundary returnToMainInteractor = new ReturnToMainInteractor(returnToMainOutputBoundary);
        return new ReturnToMainController(returnToMainInteractor);
    }
}
