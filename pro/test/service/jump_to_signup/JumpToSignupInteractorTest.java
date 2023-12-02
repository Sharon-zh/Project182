package service.jump_to_signup;

import org.junit.jupiter.api.Test;
import service.jump_to_signup.use_case.JumpToSignupInputBoundary;
import service.jump_to_signup.use_case.JumpToSignupInputData;
import service.jump_to_signup.use_case.JumpToSignupInteractor;
import service.jump_to_signup.use_case.JumpToSignupOutputBoundary;

import java.io.IOException;

public class JumpToSignupInteractorTest {
    @Test
    void successTest() throws IOException {
        JumpToSignupInputData jumpToSignupInputData = new JumpToSignupInputData();
        JumpToSignupOutputBoundary jumpToSignupOutputBoundary = new JumpToSignupOutputBoundary() {
            @Override
            public void prepareSuccessView() {
            }
        };
        JumpToSignupInputBoundary jumpToSignupInteractor = new JumpToSignupInteractor(jumpToSignupOutputBoundary);
        jumpToSignupInteractor.execute();
    }
}
