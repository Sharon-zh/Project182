package service.remove_favourite_recipe.use_case;


public class RemoveRecipeInteractor implements RemoveRecipeInputBoundary {
    final RemoveRecipeDataAccessInterface userDataAccessObject;
    final RemoveRecipeOutputBoundary userPresenter;
    public RemoveRecipeInteractor(RemoveRecipeDataAccessInterface removeRecipeDataAccessInterface,
                                  RemoveRecipeOutputBoundary removeRecipeOutputBoundary) {
        this.userDataAccessObject = removeRecipeDataAccessInterface;
        this.userPresenter = removeRecipeOutputBoundary;
    }

    @Override
    public void execute(RemoveRecipeInputData removeRecipeInputData) {
            String userName = removeRecipeInputData.getUserName();
            String recipeName = removeRecipeInputData.getRecipeName();
            userDataAccessObject.removeRecipe(userName, recipeName);
            RemoveRecipeOutputData removeRecipeOutputData = new RemoveRecipeOutputData(recipeName, false);

            userPresenter.prepareSuccessView(removeRecipeOutputData);
        }
}
