package service.return_to_main.use_case;

public class ReturnToMainInteractor implements ReturnToMainInputBoundary{
    final ReturnToMainOutputBoundary returnToMainPresenter;

    public ReturnToMainInteractor(ReturnToMainOutputBoundary returnToMainPresenter) {
        this.returnToMainPresenter = returnToMainPresenter;
    }

    @Override
    public void execute() {
        returnToMainPresenter.prepareSuccessView();
    }
}
