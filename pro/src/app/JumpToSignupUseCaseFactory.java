package app;

import interface_adapter.ViewManagerModel;
import service.jump_to_signup.interface_adapter.JumpToSignupController;
import service.jump_to_signup.interface_adapter.JumpToSignupPresenter;
import service.jump_to_signup.interface_adapter.JumpToSignupViewModel;
import service.jump_to_signup.use_case.JumpToSignupInputBoundary;
import service.jump_to_signup.use_case.JumpToSignupInteractor;
import service.jump_to_signup.use_case.JumpToSignupOutputBoundary;
import service.signup.interface_adapter.SignupViewModel;

public class JumpToSignupUseCaseFactory {
    private JumpToSignupUseCaseFactory() {}
    public static JumpToSignupController creatJumpToSignupUseCase(JumpToSignupViewModel jumpToSignupViewModel, SignupViewModel signupViewModel, ViewManagerModel viewManagerModel) {
        JumpToSignupOutputBoundary jumpToSignupOutputBoundary = new JumpToSignupPresenter(jumpToSignupViewModel, viewManagerModel, signupViewModel);
        JumpToSignupInputBoundary jumpToSignupInteractor = new JumpToSignupInteractor(jumpToSignupOutputBoundary);
        return new JumpToSignupController(jumpToSignupInteractor);
    }
}
