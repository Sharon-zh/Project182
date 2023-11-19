package service.read.use_case;

public interface ReadOutputBoundary {
    void prepareSuccessView(ReadOutputData readOutputData);

    void prepareFailView(String emptyMessage);
}
