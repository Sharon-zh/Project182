package view;

import service.check_favourite_recipe.interface_adapter.CheckFavourRecipeViewModel;
import service.check_recipe.interface_adapter.CheckRecipeController;
import service.check_recipe.interface_adapter.CheckRecipeState;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.comment.interface_adapter.CommentController;
import service.comment.interface_adapter.CommentState;
import service.comment.interface_adapter.CommentViewModel;
import service.like.interface_adapter.LikeController;
import service.login.interface_adapter.LoginState;
import service.return_to_main.interface_adapter.ReturnToMainViewModel;
import service.save_favourite_recipe.interface_adapter.SaveRecipeController;
import service.search.interface_adapter.SearchViewModel;
import service.return_to_main.interface_adapter.ReturnToMainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

public class RecipeView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "recipe";

    private final CheckRecipeViewModel checkRecipeViewModel;
    private final CheckRecipeController checkRecipeController;
    private final LikeController likeController;
    private final CommentViewModel commentViewModel;
    private final CommentController commentController;
    private final SaveRecipeController saveRecipeController;

    private final ReturnToMainController returnToMainController;
    private JButton cancel;
    private final JTextField commentInputField = new JTextField(15);

    public RecipeView(CheckRecipeViewModel checkRecipeViewModel, CheckRecipeController checkRecipeController, LikeController likeController,
                      CommentViewModel commentViewModel, CommentController commentController, ReturnToMainController returnToMainController,
                      SaveRecipeController saveRecipeController){
        this.checkRecipeViewModel = checkRecipeViewModel;
        this.checkRecipeController = checkRecipeController;
        this.likeController = likeController;
        this.commentViewModel = commentViewModel;
        this.commentController = commentController;
        this.returnToMainController = returnToMainController;
        this.saveRecipeController = saveRecipeController;

        commentViewModel.addPropertyChangeListener(this);
        checkRecipeViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(checkRecipeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(title);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("check recipe")){
            this.removeAll();
//            JPanel panel = new JPanel(new FlowLayout());
            CheckRecipeState checkRecipeState = (CheckRecipeState) evt.getNewValue();
            String username = checkRecipeState.getUsername();
            if (checkRecipeState.getRecipe() != null){
                // add recipe name
                String name = checkRecipeState.getRecipe().getName();
                JLabel recipe_name = new JLabel("Recipe Name: " + name);
//            recipe_name.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.add(recipe_name);
//            this.add(recipe_name);

                // add ingredient
                HashMap<String, String> ingredient = checkRecipeState.getRecipe().getIngredients();
                int i = 1;
                for (HashMap.Entry<String, String> entry : ingredient.entrySet()){

                    JLabel recipe_ingredient = new JLabel("Ingredient" + Integer.toString(i) + ": " + entry.getValue());
                    this.add(recipe_ingredient);
                    i++;
                }

                // add instruction
                String instruction = checkRecipeState.getRecipe().getInstructions();
                JLabel recipe_instruction = new JLabel("instruction: " + instruction);
                this.add(recipe_instruction);

                // add youtube
                String youtube = checkRecipeState.getRecipe().getYoutubeLink();
                JLabel recipe_youtube = new JLabel("YouTube Link: " + youtube);
                this.add(recipe_youtube);

                // add like
                int like = checkRecipeState.getRecipe().getLikes();
                JLabel recipe_like = new JLabel("Like: " + like);
                this.add(recipe_like);
                JButton like_button = new JButton("like");
                like_button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(like_button)) {
                            likeController.execute(name, username);
                        }
                    }
                });
                this.add(like_button);
                //comment
                LabelTextPanel commentInfo = new LabelTextPanel(
                        new JLabel("Comment"), commentInputField);
                commentInputField.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CommentState commentState = commentViewModel.getState();
                        String text = commentInputField.getText() + e.getKeyChar();
                        commentState.setComment(text);
                        commentViewModel.setState(commentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });
                JButton send = new JButton("send");
                send.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(send)) {
                            CommentState commentState = commentViewModel.getState();
                            commentController.execute(name, username, commentState.getComment());
                        }
                    }
                });
                this.add(commentInfo);
                this.add(send);
                // comment message
                ArrayList<String> comment_message = checkRecipeState.getRecipe().getComments();
                for (int a = 0; a < comment_message.size(); a++){
                    JLabel message_label = new JLabel(comment_message.get(a));
                    this.add(message_label);
                }
//                 cancel button
                cancel = new JButton(SearchViewModel.CANCEL_BUTTON_LABEL);
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(cancel)) {
                            returnToMainController.execute();
                        }
                    }
                });
                this.add(cancel);
//                 save favourite list button
                JButton save = new JButton("save");
                save.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(save)) {
                            saveRecipeController.execute(username, name);
                        }
                    }
                });
                this.add(save);
            }
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }

    }
}