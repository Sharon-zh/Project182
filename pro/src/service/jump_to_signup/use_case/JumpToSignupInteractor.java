package service.jump_to_signup.use_case;

public class JumpToSignupInteractor implements JumpToSignupInputBoundary{
    final JumpToSignupOutputBoundary jumpToSignupPresenter;

    public JumpToSignupInteractor(JumpToSignupOutputBoundary jumpToSignupPresenter) {
        this.jumpToSignupPresenter = jumpToSignupPresenter;
    }

    @Override
    public void execute() {
        jumpToSignupPresenter.prepareSuccessView();
    }
}
