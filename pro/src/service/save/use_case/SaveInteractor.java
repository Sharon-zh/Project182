package service.save.use_case;


public class SaveInteractor implements SaveInputBoundary {
    final SaveRecipeDataAccessInterface userDataAccessObject;
    final SaveOutputBoundary userPresenter;
    public SaveInteractor(SaveRecipeDataAccessInterface saveDataAccessInterface,
                          SaveOutputBoundary saveOutputBoundary) {
        this.userDataAccessObject = saveDataAccessInterface;
        this.userPresenter = saveOutputBoundary;
    }

    @Override
    public void execute(SaveInputData saveInputData) {
            String userName = saveInputData.getUserName();
            String recipeName = saveInputData.getUserName();
            userDataAccessObject.saveRecipe(userName, recipeName);
            SaveOutputData saveOutputData = new SaveOutputData(recipeName, false);

            userPresenter.prepareSuccessView(saveOutputData);
        }
}
