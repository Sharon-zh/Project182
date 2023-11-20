package service.after_search.interface_adapter;


import service.after_search.use_case.AfterSearchInputBoundary;
import service.logout.use_case.LogoutInputBoundary;

public class AfterSearchController {

    final AfterSearchInputBoundary afterSearchUseCaseInteractor;
    public AfterSearchController(AfterSearchInputBoundary afterSearchUseCaseInteractor) {
        this.afterSearchUseCaseInteractor = afterSearchUseCaseInteractor;
    }

    public void execute() {
        afterSearchUseCaseInteractor.execute();
    }
}
