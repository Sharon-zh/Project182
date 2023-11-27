package service.return_to_main.interface_adapter;

import service.recommendation.interface_adapter.RecommendationController;
import service.return_to_main.use_case.ReturnToMainInputBoundary;

public class ReturnToMainController {
    ReturnToMainInputBoundary returnToMainInteractor;
    public ReturnToMainController(ReturnToMainInputBoundary returnToMainInputBoundary){
        this.returnToMainInteractor = returnToMainInputBoundary;
    }
    public void execute(){returnToMainInteractor.execute();}
}
