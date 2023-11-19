package service.read.interface_adapter;

import interface_adapter.ViewManagerModel;
import service.read.use_case.ReadInputData;
import service.read.use_case.ReadOutputBoundary;
import service.read.use_case.ReadOutputData;

import java.util.ArrayList;

public class ReadPresenter implements ReadOutputBoundary {
    private final ReadViewModel readViewModel;
    private ViewManagerModel viewManagerModel;



    public ReadPresenter(ReadViewModel readViewModel, ViewManagerModel viewManagerModel) {
        this.readViewModel = readViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(ReadOutputData readOutputData) {
        ReadState readState = readViewModel.getState();
        ArrayList<String> favouriteRecipes = readOutputData.getFavouriteRecipes();
        readState.setFavouriteRecipes(favouriteRecipes);
        readViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(readViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String emptyMessage) {
        ReadState readState = readViewModel.getState();
        readState.setEmptyMessage(emptyMessage);
        readViewModel.firePropertyChanged();
    }

}
