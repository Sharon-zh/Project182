package view;

import service.check_favourite_recipe.interface_adapter.CheckFavourRecipeController;
import service.check_favourite_recipe.interface_adapter.CheckFavourRecipeViewModel;
import service.load_favourite_recipes.interface_adapter.LoadRecipesState;
import service.load_favourite_recipes.interface_adapter.LoadRecipesViewModel;
import service.login.interface_adapter.LoginViewModel;
import service.remove_favourite_recipe.interface_adapter.RemoveRecipeController;
import service.remove_favourite_recipe.interface_adapter.RemoveRecipeViewModel;
import service.return_to_main.interface_adapter.ReturnToMainController;
import service.return_to_main.interface_adapter.ReturnToMainViewModel;
import service.search.interface_adapter.SearchViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;


public class FavouriteRecipesView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "favourite recipes";

    private final LoadRecipesViewModel loadRecipesViewModel;
    private final ReturnToMainViewModel returnToMainViewModel;
    private final RemoveRecipeViewModel removeRecipeViewModel;
    private final LoginViewModel loginViewModel;
    private final CheckFavourRecipeViewModel checkFavourRecipeViewModel;
    private final ReturnToMainController returnToMainController;
    private final RemoveRecipeController removeRecipeController;
    private final CheckFavourRecipeController checkFavourRecipeController;
    private JButton cancel;
    private final JPanel buttons;


    public FavouriteRecipesView(LoadRecipesViewModel loadRecipesViewModel, ReturnToMainViewModel returnToMainViewModel,
                                RemoveRecipeViewModel removeRecipeViewModel,
                                LoginViewModel loginViewModel, CheckFavourRecipeViewModel checkFavourRecipeViewModel,
                                ReturnToMainController returnToMainController,
                                RemoveRecipeController removeRecipeController,
                                CheckFavourRecipeController checkFavourRecipeController) {
        this.loadRecipesViewModel = loadRecipesViewModel;
        this.returnToMainViewModel = returnToMainViewModel;
        this.removeRecipeViewModel = removeRecipeViewModel;
        this.loginViewModel = loginViewModel;
        this.checkFavourRecipeViewModel = checkFavourRecipeViewModel;
        this.returnToMainController = returnToMainController;
        this.removeRecipeController = removeRecipeController;
        this.checkFavourRecipeController = checkFavourRecipeController;
        this.buttons = new JPanel();
        this.removeRecipeViewModel.addPropertyChangeListener(this);
        this.returnToMainViewModel.addPropertyChangeListener(this);
        this.checkFavourRecipeViewModel.addPropertyChangeListener(this);
        this.loadRecipesViewModel.addPropertyChangeListener(this);



        JLabel title = new JLabel(RemoveRecipeViewModel.TITLE_LABEL);
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


    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("load recipes")) {
            buttons.removeAll();
            LoadRecipesState loadRecipesState = (LoadRecipesState) evt.getNewValue();
            if (loadRecipesState.getFavouriteRecipes() != null) {
                ArrayList<String> recipes = loadRecipesState.getFavouriteRecipes();
                for (String buttonName : recipes) {
                    JButton recipeButton = new JButton(buttonName);
                    buttons.add(recipeButton);
                    recipeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(recipeButton)) {
                                checkFavourRecipeController.execute(buttonName);
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

