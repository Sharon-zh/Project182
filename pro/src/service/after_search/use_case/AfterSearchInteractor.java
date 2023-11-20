package service.after_search.use_case;


public class AfterSearchInteractor implements AfterSearchInputBoundary {
    final AfterSearchOutputBoundary userPresenter;

    public AfterSearchInteractor(AfterSearchOutputBoundary afterSearchOutputBoundary) {
        this.userPresenter = afterSearchOutputBoundary;
    }

    @Override
    public void execute() {
            userPresenter.prepareSuccessView();
        }
}
