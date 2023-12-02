package view;

import entity.Recipe;
import service.check_recipe.interface_adapter.CheckRecipeController;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.return_to_main.interface_adapter.ReturnToMainController;
import service.return_to_main.interface_adapter.ReturnToMainViewModel;
import service.search.interface_adapter.SearchController;
import service.search.interface_adapter.SearchState;
import service.search.interface_adapter.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.Set;

public class SearchResultView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "search result";

    private final SearchViewModel searchViewModel;
    private final SearchController searchController;
    private final CheckRecipeController checkRecipeController;
    private final ReturnToMainController returnToMainController;
    private final CheckRecipeViewModel checkRecipeViewModel;

    private final ReturnToMainViewModel returnToMainViewModel;

    private JButton cancel;

    private final JPanel buttons;

    public SearchResultView(SearchViewModel searchViewModel, CheckRecipeController checkRecipeController, ReturnToMainController returnToMainController, SearchController searchController, CheckRecipeViewModel checkRecipeViewModel, ReturnToMainViewModel returnToMainViewModel) {
        this.searchController = searchController;
        this.searchViewModel = searchViewModel;
        this.checkRecipeController = checkRecipeController;
        this.checkRecipeViewModel = checkRecipeViewModel;
        this.returnToMainController = returnToMainController;
        this.returnToMainViewModel = returnToMainViewModel;
        this.buttons = new JPanel();
        searchViewModel.addPropertyChangeListener(this);
        checkRecipeViewModel.addPropertyChangeListener(this);
        returnToMainViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Search Result");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        cancel = new JButton(SearchViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(cancel)) {
                    returnToMainController.execute();
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("search")) {
            buttons.removeAll();
            SearchState searchstate = (SearchState) evt.getNewValue();
            if (searchstate.getSearchResult() != null){
                Set<String> recipe_name = searchstate.getSearchResult().keySet();
                Map<String, Recipe> recipe_map = searchstate.getSearchResult();
                for (String buttonName : recipe_name) {
                    JButton recipeButton = new JButton(buttonName);
                    buttons.add(recipeButton);
                    recipeButton.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(recipeButton)) {
                                checkRecipeController.execute(recipe_map.get(buttonName));
                            }
                        }
                    });
                }

                cancel = new JButton(SearchViewModel.CANCEL_BUTTON_LABEL);
                buttons.add(cancel);
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(cancel)) {
                            returnToMainController.execute();
                        }
                    }
                });
            }
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(buttons);
        }
    }
}