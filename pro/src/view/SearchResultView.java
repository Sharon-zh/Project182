package view;

import entity.Recipe;
import service.after_search.interface_adapter.AfterSearchController;
import service.after_search.interface_adapter.AfterSearchState;
import service.after_search.interface_adapter.AfterSearchViewModel;
import service.after_search.use_case.AfterSearchOutputData;
import service.search.use_case.SearchOutputBoundary;
import service.search.use_case.SearchOutputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

public class SearchResultView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "search result";

    private final AfterSearchViewModel afterSearchViewModel;
    private final AfterSearchController afterSearchController;
    private final SearchOutputData searchOutputData;
    private final JButton goback;
    public SearchResultView(AfterSearchController controller, AfterSearchViewModel afterSearchViewModel, SearchOutputData searchOutputData) {
        this.afterSearchController = controller;
        this.afterSearchViewModel = afterSearchViewModel;
        this.searchOutputData = searchOutputData;
        afterSearchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(AfterSearchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //List<Recipe> result = searchOutputData.getSearchResult()["meals"];


        JPanel buttons = new JPanel();
        goback = new JButton(AfterSearchViewModel.GOBACK_BUTTON_LABEL);
        buttons.add(goback);
    }
}
