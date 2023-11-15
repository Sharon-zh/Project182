package service.save.interface_adapter;

import entity.User;
import service.save.use_case.SaveInputBoundary;
import service.save.use_case.SaveInputData;

public class SaveController {
    final SaveInputBoundary saveUseCaseInteractor;
    public SaveController(SaveInputBoundary saveUseCaseInteractor) {
        this.saveUseCaseInteractor = saveUseCaseInteractor;
    }

    public void execute(String recipeName, User user) {
        SaveInputData SaveInputData = new SaveInputData(recipeName, user);

        saveUseCaseInteractor.execute(SaveInputData);
    }
}
