package view;

import service.check_favourite_recipe.interface_adapter.CheckFavourRecipeState;
import service.check_favourite_recipe.interface_adapter.CheckFavourRecipeViewModel;
import service.check_recipe.interface_adapter.CheckRecipeController;
import service.check_recipe.interface_adapter.CheckRecipeState;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.comment.interface_adapter.CommentController;
import service.comment.interface_adapter.CommentState;
import service.comment.interface_adapter.CommentViewModel;
import service.like.interface_adapter.LikeController;
import service.remove_favourite_recipe.interface_adapter.RemoveRecipeController;
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
    private final CheckFavourRecipeViewModel checkFavourRecipeViewModel;
    private final LikeController likeController;
    private final CommentViewModel commentViewModel;
    private final CommentController commentController;
    private final SaveRecipeController saveRecipeController;
    private final RemoveRecipeController removeRecipeController;

    private final ReturnToMainController returnToMainController;
    private JButton cancel;
    private final JTextField commentInputField = new JTextField(15);

    public RecipeView(CheckRecipeViewModel checkRecipeViewModel, CheckRecipeController checkRecipeController, CheckFavourRecipeViewModel checkFavourRecipeViewModel, LikeController likeController,
                      CommentViewModel commentViewModel, CommentController commentController, ReturnToMainController returnToMainController,
                      SaveRecipeController saveRecipeController, RemoveRecipeController removeRecipeController){
        this.checkRecipeViewModel = checkRecipeViewModel;
        this.checkRecipeController = checkRecipeController;
        this.checkFavourRecipeViewModel = checkFavourRecipeViewModel;
        this.likeController = likeController;
        this.commentViewModel = commentViewModel;
        this.commentController = commentController;
        this.returnToMainController = returnToMainController;
        this.saveRecipeController = saveRecipeController;
        this.removeRecipeController = removeRecipeController;

        commentViewModel.addPropertyChangeListener(this);
        checkRecipeViewModel.addPropertyChangeListener(this);
        checkFavourRecipeViewModel.addPropertyChangeListener(this);
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
                StringBuilder new_instruction = new StringBuilder();
                for (int b = 0; b < instruction.length(); b += 100) {
                    int endIndex = Math.min(b + 100, instruction.length());
                    new_instruction.append("<br>").append(instruction, b, endIndex);
                }
                JLabel recipe_instruction = new JLabel("<html>Instruction: " + new_instruction +"</html>");
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
        } else if (evt.getPropertyName().equals("comment")) {
            CommentState state = (CommentState) evt.getNewValue();
            setFields(state);
        } else if (evt.getPropertyName().equals("check favourite recipe")) {
            this.removeAll();
//            JPanel panel = new JPanel(new FlowLayout());
            CheckFavourRecipeState checkFavourRecipeState = (CheckFavourRecipeState) evt.getNewValue();
            String username = checkFavourRecipeState.getUsername();
            if (checkFavourRecipeState.getRecipe() != null){
                // add recipe name
                String name = checkFavourRecipeState.getRecipe().getName();
                JLabel recipeName = new JLabel("Recipe Name: " + name);
//            recipe_name.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.add(recipeName);
//            this.add(recipe_name);

                // add ingredient
                HashMap<String, String> ingredient = checkFavourRecipeState.getRecipe().getIngredients();
                int i = 1;
                for (HashMap.Entry<String, String> entry : ingredient.entrySet()){

                    JLabel recipeIngredient = new JLabel("Ingredient" + Integer.toString(i) + ": " + entry.getValue());
                    this.add(recipeIngredient);
                    i++;
                }

                // add instruction
                String instruction = checkFavourRecipeState.getRecipe().getInstructions();
                StringBuilder new_instruction = new StringBuilder();
                for (int b = 0; b < instruction.length(); b += 100) {
                    int endIndex = Math.min(b + 100, instruction.length());
                    new_instruction.append("<br>").append(instruction, b, endIndex);
                }
                JLabel recipe_instruction = new JLabel("<html>Instruction: " + new_instruction +"</html>");
                this.add(recipe_instruction);

                // add youtube
                String youtube = checkFavourRecipeState.getRecipe().getYoutubeLink();
                JLabel recipeYoutube = new JLabel("YouTube Link: " + youtube);
                this.add(recipeYoutube);

                // add like
                int like = checkFavourRecipeState.getRecipe().getLikes();
                JLabel recipeLike = new JLabel("Like: " + like);
                this.add(recipeLike);
                JButton likeButton = new JButton("like");
                likeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(likeButton)) {
                            likeController.execute(name, username);
                        }
                    }
                });
                this.add(likeButton);
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
                ArrayList<String> commentMessage = checkFavourRecipeState.getRecipe().getComments();
                for (int a = 0; a < commentMessage.size(); a++){
                    JLabel messageLabel = new JLabel(commentMessage.get(a));
                    this.add(messageLabel);
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
//                 remove favourite list button
                JButton remove = new JButton("remove");
                remove.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(remove)) {
                            removeRecipeController.execute(username, name);
                        }
                    }
                });
                this.add(remove);
            }
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }

    }

    private void setFields(CommentState state) {
        commentInputField.setText(null);
    }
}