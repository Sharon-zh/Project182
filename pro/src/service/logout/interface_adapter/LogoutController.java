package service.logout.interface_adapter;


import service.logout.use_case.LogoutInputBoundary;

public class LogoutController {

    final LogoutInputBoundary userLogoutUseCaseInteractor;
    public LogoutController(LogoutInputBoundary userLogoutUseCaseInteractor) {
        this.userLogoutUseCaseInteractor = userLogoutUseCaseInteractor;
    }

    public void execute() {
        userLogoutUseCaseInteractor.execute();
    }
}
