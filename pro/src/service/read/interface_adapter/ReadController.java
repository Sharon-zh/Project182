package service.read.interface_adapter;

import service.read.use_case.ReadInputBoundary;
import service.read.use_case.ReadInputData;

public class ReadController {
    final ReadInputBoundary readUseCaseInteractor;
    public ReadController(ReadInputBoundary readUseCaseInteractor) {
        this.readUseCaseInteractor = readUseCaseInteractor;
    }

    public void execute(String userName) {
        ReadInputData readInputData = new ReadInputData(userName);
        readUseCaseInteractor.execute(readInputData);
    }
}
