package view;

import entity.Recipe;
import service.check_recipe.interface_adapter.CheckRecipeController;
import service.check_recipe.interface_adapter.CheckRecipeViewModel;
import service.recommendation.interface_adapter.RecommendationController;
import service.recommendation.interface_adapter.RecommendationState;
import service.recommendation.interface_adapter.RecommendationViewModel;
import service.return_to_main.interface_adapter.ReturnToMainController;
import service.return_to_main.interface_adapter.ReturnToMainViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class RecommendView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "recommend";

    private final RecommendationViewModel recommendationViewModel;
    private final RecommendationController recommendationController;
    private final CheckRecipeController checkRecipeController;
    private final ReturnToMainController returnToMainController;
    private final CheckRecipeViewModel checkRecipeViewModel;

    private final ReturnToMainViewModel returnToMainViewModel;

    private final JPanel buttons;
    private final JButton cancel;

    public RecommendView(RecommendationController recommendationController, CheckRecipeController checkRecipeController, ReturnToMainController returnToMainController, RecommendationViewModel recommendationViewModel, CheckRecipeViewModel checkRecipeViewModel, ReturnToMainViewModel returnToMainViewModel) {
        this.recommendationController = recommendationController;
        this.recommendationViewModel = recommendationViewModel;
        this.checkRecipeController = checkRecipeController;
        this.checkRecipeViewModel = checkRecipeViewModel;
        this.returnToMainController = returnToMainController;
        this.returnToMainViewModel = returnToMainViewModel;
        this.buttons = new JPanel();
        recommendationViewModel.addPropertyChangeListener(this);
        checkRecipeViewModel.addPropertyChangeListener(this);
        returnToMainViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(RecommendationViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttons = new JPanel();
//        refresh = new JButton(RecommendationViewModel.REFRESH_BUTTON_LABEL);
//        buttons.add(refresh);
        cancel = new JButton(RecommendationViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
//        if (recommendationViewModel.getState().get() != null){
//            ArrayList<Recipe> recipeList = recommendationViewModel.getState().get().getRecommendedRecipes();
//            for (Recipe everyRecipe: recipeList){
//                JButton recipeButton = new JButton(everyRecipe.getName());
//                buttons.add(recipeButton);
//                recipeButton.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(recipeButton)) {
//                            checkRecipeController.execute(everyRecipe);
//                        }
//                    }
//                });
//
//            }
//        }
//        refresh.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                if (evt.getSource().equals(refresh)) {
//                    recommendationController.execute();
//                }
//            }
//        });

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
        if (evt.getPropertyName().equals("recommendation")){
            buttons.removeAll();
            RecommendationState recommendationState = (RecommendationState) evt.getNewValue();
            if (recommendationState.get() != null) {
                ArrayList<Recipe> recipeList = recommendationState.get().getRecommendedRecipes();
                System.out.println(recipeList.size());
                for (Recipe everyRecipe: recipeList){
                    JButton recipeButton = new JButton(everyRecipe.getName());
                    buttons.add(recipeButton);
                    recipeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(recipeButton)) {
                                checkRecipeController.execute(everyRecipe);
                            }
                        }
                    });

                }
            }
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(buttons);
        }
    }
}