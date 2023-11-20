package service.logout;

import org.junit.Test;
import service.logout.use_case.*;
import static org.junit.Assert.assertEquals;

public class LogoutInteractorTest {
    // need to add a user.

    @Test
    public void successTest() {
        LogoutInputData inputData = new LogoutInputData("Lisa");

        LogoutOutputBoundary successPresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView(LogoutOutputData output) {
                assertEquals(output.getUsername(), "Lisa");
            }
        };

        LogoutInputBoundary interactor = new LogoutInteractor(successPresenter);
        interactor.execute(inputData);
    }
}
