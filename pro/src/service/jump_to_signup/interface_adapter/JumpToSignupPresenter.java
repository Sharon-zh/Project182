package service.jump_to_signup.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.jump_to_signup.use_case.JumpToSignupOutputBoundary;
import service.signup.interface_adapter.SignupViewModel;

public class JumpToSignupPresenter implements JumpToSignupOutputBoundary {
    private final JumpToSignupViewModel jumpToSignupViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signupViewModel;

    public JumpToSignupPresenter(JumpToSignupViewModel jumpToSignupViewModel, ViewManagerModel viewManagerModel, SignupViewModel signupViewModel) {
        this.jumpToSignupViewModel = jumpToSignupViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView() {
        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
