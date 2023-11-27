package service.check_recipe.use_case;



public class CheckRecipeInteractor implements CheckRecipeInputBoundary {
    final CheckRecipeOutputBoundary checkRecipePresenter;

    public CheckRecipeInteractor(CheckRecipeOutputBoundary checkRecipePresenter) {
        this.checkRecipePresenter = checkRecipePresenter;
    }

    @Override
    public void execute(CheckRecipeInputData checkRecipeInputData) {
        CheckRecipeOutputData checkRecipeOutputData = new CheckRecipeOutputData(checkRecipeInputData.getRecipe());
        checkRecipePresenter.prepareSuccessView(checkRecipeOutputData);
    }
}