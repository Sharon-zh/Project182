package service.save.interface_adapter;

import entity.User;
import service.save.use_case.SaveInputBoundary;
import service.save.use_case.SaveInputData;

public class SaveController {
    final SaveInputBoundary userSaveUseCaseInteractor;
    public SaveController(SaveInputBoundary userSaveUseCaseInteractor) {
        this.userSaveUseCaseInteractor = userSaveUseCaseInteractor;
    }

    public void execute(String recipeName, User user) {
        SaveInputData SaveInputData = new SaveInputData(recipeName, user);

        userSaveUseCaseInteractor.execute(SaveInputData);
    }
}
