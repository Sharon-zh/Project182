package view;

import service.check_favourite_recipe.interface_adapter.CheckFavourRecipeController;
import service.check_favourite_recipe.interface_adapter.CheckFavourRecipeState;
import service.check_favourite_recipe.interface_adapter.CheckFavourRecipeViewModel;
import service.load_favourite_recipes.interface_adapter.LoadRecipesController;
import service.load_favourite_recipes.interface_adapter.LoadRecipesViewModel;
import service.logged_in.interface_adapter.LoggedInViewModel;
import service.remove_favourite_recipe.interface_adapter.RemoveRecipeController;
import service.remove_favourite_recipe.interface_adapter.RemoveRecipeViewModel;
import service.return_to_main.interface_adapter.ReturnToMainController;
import service.return_to_main.interface_adapter.ReturnToMainViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class FavouriteRecipesView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "sign up";

    private final LoadRecipesViewModel loadRecipesViewModel;
    private final ReturnToMainViewModel returnToMainViewModel;
    private final RemoveRecipeViewModel removeRecipeViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final CheckFavourRecipeViewModel checkFavourRecipeViewModel;
    private final ReturnToMainController returnToMainController;
    private final RemoveRecipeController removeRecipeController;
    private final CheckFavourRecipeController checkFavourRecipeController;


    private final JButton cancel;

    public FavouriteRecipesView(LoadRecipesViewModel loadRecipesViewModel, ReturnToMainViewModel returnToMainViewModel,
                                RemoveRecipeViewModel removeRecipeViewModel, LoggedInViewModel loggedInViewModel,
                                CheckFavourRecipeViewModel checkFavourRecipeViewModel,
                                ReturnToMainController returnToMainController,
                                RemoveRecipeController removeRecipeController,
                                CheckFavourRecipeController checkFavourRecipeController) {
        this.loadRecipesViewModel = loadRecipesViewModel;
        this.returnToMainViewModel = returnToMainViewModel;
        this.removeRecipeViewModel = removeRecipeViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.checkFavourRecipeViewModel = checkFavourRecipeViewModel;
        this.returnToMainController = returnToMainController;
        this.removeRecipeController = removeRecipeController;
        this.checkFavourRecipeController = checkFavourRecipeController;

        this.removeRecipeViewModel.addPropertyChangeListener(this);
        this.returnToMainViewModel.addPropertyChangeListener(this);
        this.checkFavourRecipeViewModel.addPropertyChangeListener(this);



        JLabel title = new JLabel(RemoveRecipeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel buttons = new JPanel();
        cancel = new JButton(RemoveRecipeViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(cancel)) {
                            returnToMainController.execute();
                        }
                    }
                }
        );

        ArrayList<String> recipeNames = loadRecipesViewModel.getState().getFavouriteRecipes();
        for (String recipeName: recipeNames){
            JButton recipeButton = new JButton(recipeName);
            buttons.add(recipeButton);
            recipeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource().equals(recipeButton)) {
                        checkFavourRecipeController.execute(recipeName);
                    }
                }
            });

            JButton removeRecipeButton = new JButton("remove " + recipeName);
            buttons.add(removeRecipeButton);
            removeRecipeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource().equals(removeRecipeButton)) {
                        String userName = loggedInViewModel.getState().getUsername();
                        removeRecipeController.execute(userName, recipeName);
                    }
                }
            });
        }
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }


    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("check favourite recipe")) {
            CheckFavourRecipeState state = (CheckFavourRecipeState) evt.getNewValue();
            if (state.getNoResultError() != null) {
                JOptionPane.showMessageDialog(this, state.getNoResultError());
            }
        }
    }
}
