package view;

import interface_adapter.RecipeState;
import interface_adapter.RecipeViewModel;
import service.like.interface_adapter.LikeController;
import service.like.interface_adapter.LikeState;
import service.like.interface_adapter.LikeViewModel;
import service.login.interface_adapter.LoginState;
import service.save_favourite_recipe.interface_adapter.SaveRecipeController;
import service.save_favourite_recipe.interface_adapter.SaveRecipeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RecipeView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "recipe";
    private final RecipeViewModel recipeViewModel;
    private final LikeViewModel likeViewModel;
    private final LikeController likeController;
    private final SaveRecipeViewModel saveRecipeViewModel;
    private final SaveRecipeController saveRecipeController;

    JLabel name;
    JLabel category;
    JLabel instructions;
    JLabel ingredients;
    JLabel likes;
    JLabel comments;
    JLabel imageLink;
    JLabel youtubeLink;

    final JButton back;
    final JButton like;
    final JButton save;

    /**
     * A window with a title and three JButton.
     */
    public RecipeView(RecipeViewModel recipeViewModel, LikeViewModel likeViewModel, LikeController likeController, SaveRecipeViewModel saveRecipeViewModel, SaveRecipeController saveRecipeController) {
        this.recipeViewModel = recipeViewModel;
        this.likeViewModel = likeViewModel;
        this.likeController = likeController;
        this.saveRecipeViewModel = saveRecipeViewModel;
        this.saveRecipeController = saveRecipeController;

        this.recipeViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Recipe Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel commentInfo = new JLabel("Comments:");
        name = new JLabel();
        category = new JLabel();
        instructions = new JLabel();
        ingredients = new JLabel();
        likes = new JLabel();
        comments = new JLabel();
        imageLink = new JLabel();
        youtubeLink = new JLabel();

        JPanel buttons = new JPanel();
        back = new JButton(RecipeViewModel.BACK_BUTTON_LABEL);
        like = new JButton(RecipeViewModel.LIKE_BUTTON_LABEL);
        save = new JButton(RecipeViewModel.SAVE_BUTTON_LABEL);
        buttons.add(back);
        buttons.add(like);
        buttons.add(save);

        back.addActionListener(this);
        like.addAncestorListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(like)) {
                            LikeState currentState = likeViewModel.getState();
                            likeController.execute(
                                    currentState.get(),

                            );
                        }
                    }
                }
        );
        save.addAncestorListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(name);
        this.add(category);
        this.add(instructions);
        this.add(ingredients);
        this.add(likes);
        this.add(imageLink);
        this.add(youtubeLink);
        this.add(commentInfo);
        this.add(comments);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        RecipeState state = (RecipeState) evt.getNewValue();
        name.setText(state.getName());
        category.setText(state.getCategory());
        instructions.setText(state.getInstructions());
        ingredients.setText(state.getIngredients());
        likes.setText(state.getLikes());
        comments.setText(state.getComments());
        imageLink.setText(state.getImageLink());
        youtubeLink.setText(state.getYoutubeLink());
    }
}
