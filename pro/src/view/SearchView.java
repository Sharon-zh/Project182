package view;

import service.load_favourite_recipes.interface_adapter.LoadRecipesController;
import service.load_favourite_recipes.interface_adapter.LoadRecipesState;
import service.load_favourite_recipes.interface_adapter.LoadRecipesViewModel;
import service.logged_in.interface_adapter.LoggedInState;
import service.logged_in.interface_adapter.LoggedInViewModel;
import service.logout.interface_adapter.LogoutController;
import service.logout.interface_adapter.LogoutViewModel;
import service.recommendation.interface_adapter.RecommendationController;
import service.recommendation.interface_adapter.RecommendationViewModel;
import service.search.interface_adapter.SearchController;
import service.search.interface_adapter.SearchState;
import service.search.interface_adapter.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "main";

    private final SearchViewModel searchViewModel;
    private final LogoutViewModel logoutViewModel;
    private final RecommendationViewModel recommendationViewModel;
    private final LoadRecipesViewModel loadRecipesViewModel;
    private final LoggedInViewModel loggedInViewModel;

    private final JTextField keywordInputField = new JTextField(15);
    private final SearchController searchController;
    private final LogoutController logoutController;
    private final RecommendationController recommendationController;

    private final LoadRecipesController loadRecipesController;

    private final JButton search;
    private final JButton logout;
    private final JButton recommend;
    private final JButton favouriteRecipes;


    public SearchView(SearchViewModel searchViewModel, LogoutViewModel logoutViewModel,
                      RecommendationViewModel recommendationViewModel, LoadRecipesViewModel loadRecipesViewModel,
                      LoggedInViewModel loggedInViewModel, SearchController searchController,
                      LogoutController logoutController, RecommendationController recommendationController,
                      LoadRecipesController loadRecipesController) {
        this.searchViewModel = searchViewModel;
        this.logoutViewModel = logoutViewModel;
        this.recommendationViewModel = recommendationViewModel;
        this.loadRecipesViewModel = loadRecipesViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.searchController = searchController;
        this.logoutController = logoutController;
        this.recommendationController = recommendationController;
        this.loadRecipesController = loadRecipesController;

        searchViewModel.addPropertyChangeListener(this);
        logoutViewModel.addPropertyChangeListener(this);
        recommendationViewModel.addPropertyChangeListener(this);
        loadRecipesViewModel.addPropertyChangeListener(this);


        JLabel title = new JLabel(SearchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SearchViewModel.KEYWORD_LABEL), keywordInputField);

        JPanel buttons = new JPanel();
        search = new JButton(SearchViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        logout = new JButton(LogoutViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logout);
        recommend = new JButton(RecommendationViewModel.RECOMMEND_BUTTON_LABEL);
        buttons.add(recommend);
        favouriteRecipes = new JButton(LoadRecipesViewModel.FAVOURITE_RECIPES_BUTTON_LABEL);
        buttons.add(favouriteRecipes);

        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            SearchState currentState = searchViewModel.getState();
                            searchController.execute(currentState.getSearchWord());
                        }
                    }
                }
        );

        logout.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(logout)) {
                            LoggedInState currentState = loggedInViewModel.getState();
                            logoutController.execute(currentState.getUsername());
                        }
                    }
                }
        );

        recommend.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(recommend)) {
                            recommendationController.execute();
                        }
                    }
                }
        );

        favouriteRecipes.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(favouriteRecipes)) {
                            LoggedInState currentState = loggedInViewModel.getState();
                            loadRecipesController.execute(currentState.getUsername());
                        }
                    }
                }
        );


        keywordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState currentState = searchViewModel.getState();
                        String text = keywordInputField.getText() + e.getKeyChar();
                        currentState.setSearchWord(text);
                        searchViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("search")) {
            SearchState searchstate = (SearchState) evt.getNewValue();
            if (searchstate.getNoResultError() != null) {
                JOptionPane.showMessageDialog(this, searchstate.getNoResultError());
            }
        } else if (evt.getPropertyName().equals("load recipes")) {
            LoadRecipesState loadRecipesState = (LoadRecipesState) evt.getNewValue();
            if (loadRecipesState.getEmptyMessage() != null) {
                JOptionPane.showMessageDialog(this, loadRecipesState.getEmptyMessage());
            }
        }
    }
}
