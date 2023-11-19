package service.read.use_case;


import java.util.ArrayList;

public class ReadInteractor implements ReadInputBoundary {
    final ReadRecipeDataAccessInterface userDataAccessObject;
    final ReadOutputBoundary userPresenter;
    public ReadInteractor(ReadRecipeDataAccessInterface readDataAccessInterface,
                          ReadOutputBoundary readOutputBoundary) {
        this.userDataAccessObject = readDataAccessInterface;
        this.userPresenter = readOutputBoundary;
    }

    @Override
    public void execute(ReadInputData readInputData) {
            String userName = readInputData.getUserName();
            ArrayList<String> favouriteRecipes = userDataAccessObject.loadFavouriteRecipes(userName);

            if (favouriteRecipes.isEmpty()){
                userPresenter.prepareFailView("You haven't saved any recipe.");

            }else{
                ReadOutputData readOutputData = new ReadOutputData(favouriteRecipes, false);
                userPresenter.prepareSuccessView(readOutputData);
            }
        }
}
