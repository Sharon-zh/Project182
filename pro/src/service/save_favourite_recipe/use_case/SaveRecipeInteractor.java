package service.save_favourite_recipe.use_case;


public class SaveRecipeInteractor implements SaveRecipeInputBoundary {
    final SaveRecipeDataAccessInterface userDataAccessObject;
    final SaveRecipeOutputBoundary userPresenter;
    public SaveRecipeInteractor(SaveRecipeDataAccessInterface saveDataAccessInterface,
                                SaveRecipeOutputBoundary saveRecipeOutputBoundary) {
        this.userDataAccessObject = saveDataAccessInterface;
        this.userPresenter = saveRecipeOutputBoundary;
    }

    @Override
    public void execute(SaveRecipeInputData saveRecipeInputData) {
            String userName = saveRecipeInputData.getUserName();
            String recipeName = saveRecipeInputData.getRecipeName();
            userDataAccessObject.saveRecipe(userName, recipeName);
            SaveRecipeOutputData saveRecipeOutputData = new SaveRecipeOutputData(recipeName, false);

            userPresenter.prepareSuccessView(saveRecipeOutputData);
        }
}
