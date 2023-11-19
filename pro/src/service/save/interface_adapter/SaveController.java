package service.save.interface_adapter;

import service.save.use_case.SaveInputBoundary;
import service.save.use_case.SaveInputData;

public class SaveController {
    final SaveInputBoundary saveUseCaseInteractor;
    public SaveController(SaveInputBoundary saveUseCaseInteractor) {
        this.saveUseCaseInteractor = saveUseCaseInteractor;
    }

    public void execute(String userName, String recipeName) {
        SaveInputData saveInputData = new SaveInputData(userName, recipeName);

        saveUseCaseInteractor.execute(saveInputData);
    }
}
