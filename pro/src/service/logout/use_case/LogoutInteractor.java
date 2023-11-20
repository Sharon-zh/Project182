package service.logout.use_case;

public class LogoutInteractor implements LogoutInputBoundary {
    final LogoutOutputBoundary userPresenter;

    public LogoutInteractor(LogoutOutputBoundary logoutOutputBoundary) {
        this.userPresenter = logoutOutputBoundary;
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {
        LogoutOutputData logoutOutputData = new LogoutOutputData(logoutInputData.getUsername());

        userPresenter.prepareSuccessView(logoutOutputData);
        }
}
