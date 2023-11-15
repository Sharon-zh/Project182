package service.save.use_case;

import entity.User;
import entity.UserFactory;

public class SaveInteractor implements SaveInputBoundary {
    final SaveRecipeDataAccessInterface userDataAccessObject;
    final SaveOutputBoundary userPresenter;
    final UserFactory userFactory;
    public SaveInteractor(SaveRecipeDataAccessInterface saveDataAccessInterface,
                          SaveOutputBoundary saveOutputBoundary,
                          UserFactory userFactory) {
        this.userDataAccessObject = saveDataAccessInterface;
        this.userPresenter = saveOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SaveInputData saveInputData) {
            User user = saveInputData.getUser();
            user.setFavoriteRecipes(saveInputData.getRecipeName());
            userDataAccessObject.saveRecipe(user);

            userPresenter.prepareSuccessView("Save successfully!");
        }
}
