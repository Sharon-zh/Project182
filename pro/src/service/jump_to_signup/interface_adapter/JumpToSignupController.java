package service.jump_to_signup.interface_adapter;

import service.jump_to_signup.use_case.JumpToSignupInputBoundary;

public class JumpToSignupController {
    final JumpToSignupInputBoundary jumpToSignupInteractor;

    public JumpToSignupController(JumpToSignupInputBoundary jumpToSignupInteractor) {
        this.jumpToSignupInteractor = jumpToSignupInteractor;
    }

    public void execute(){
        jumpToSignupInteractor.execute();
    }
}
