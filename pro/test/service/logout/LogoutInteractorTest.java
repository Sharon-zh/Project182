package service.logout;

import org.junit.Test;
import service.logout.use_case.LogoutInputBoundary;
import service.logout.use_case.LogoutInteractor;
import service.logout.use_case.LogoutOutputBoundary;

import static org.junit.Assert.assertEquals;

public class LogoutInteractorTest {
    // need to add a user.

    @Test
    public void successTest() {
        LogoutOutputBoundary successPresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView(String logoutMessage) {
                assertEquals(logoutMessage, "Log out successfully!");
            }
        };

        LogoutInputBoundary interactor = new LogoutInteractor(successPresenter);
        interactor.execute();
    }
}
