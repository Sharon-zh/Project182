package service.delete.interface_adapter;

import service.delete.use_case.DeleteInputBoundary;
import service.delete.use_case.DeleteInputData;

public class DeleteController {
    final DeleteInputBoundary deleteUseCaseInteractor;
    public DeleteController(DeleteInputBoundary deleteUseCaseInteractor) {
        this.deleteUseCaseInteractor = deleteUseCaseInteractor;
    }

    public void execute(String userName, String recipeName) {
        DeleteInputData SaveInputData = new DeleteInputData(userName, recipeName);

        deleteUseCaseInteractor.execute(SaveInputData);
    }
}
