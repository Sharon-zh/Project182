package service.delete.use_case;


public class DeleteInteractor implements DeleteInputBoundary {
    final DeleteRecipeDataAccessInterface userDataAccessObject;
    final DeleteOutputBoundary userPresenter;
    public DeleteInteractor(DeleteRecipeDataAccessInterface deleteDataAccessInterface,
                            DeleteOutputBoundary deleteOutputBoundary) {
        this.userDataAccessObject = deleteDataAccessInterface;
        this.userPresenter = deleteOutputBoundary;
    }

    @Override
    public void execute(DeleteInputData deleteInputData) {
            String userName = deleteInputData.getUserName();
            String recipeName = deleteInputData.getUserName();
            userDataAccessObject.deleteRecipe(userName, recipeName);
            DeleteOutputData deleteOutputData = new DeleteOutputData(recipeName, false);

            userPresenter.prepareSuccessView(deleteOutputData);
        }
}
