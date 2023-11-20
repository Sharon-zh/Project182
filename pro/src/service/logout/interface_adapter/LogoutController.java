package service.logout.interface_adapter;

import service.logout.use_case.LogoutInputBoundary;
import service.logout.use_case.LogoutInputData;

public class LogoutController {

    final LogoutInputBoundary userLogoutUseCaseInteractor;
    public LogoutController(LogoutInputBoundary userLogoutUseCaseInteractor) {
        this.userLogoutUseCaseInteractor = userLogoutUseCaseInteractor;
    }

    public void execute(String username) {
        LogoutInputData logoutInputData = new LogoutInputData(username);
        userLogoutUseCaseInteractor.execute(logoutInputData);
    }
}
