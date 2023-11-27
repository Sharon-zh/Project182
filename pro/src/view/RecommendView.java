package view;

import entity.Recipe;
import service.check_recipe.interface_adapter.CheckRecipeController;
import service.recommendation.interface_adapter.RecommendationController;
import service.recommendation.interface_adapter.RecommendationPresenter;
import service.recommendation.interface_adapter.RecommendationState;
import service.recommendation.interface_adapter.RecommendationViewModel;
import service.return_to_main.interface_adapter.ReturnToMainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.Set;

public class RecommendView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "recommend";

    private final RecommendationViewModel recommendationViewModel;

    private final JButton refresh;

    private final JButton cancel;

    public RecommendView(RecommendationController recommendationController, CheckRecipeController checkRecipeController, ReturnToMainController returnToMainController, RecommendationViewModel recommendationViewModel, RecommendationPresenter recommendationPresenter) {
        this.recommendationViewModel = recommendationViewModel;
        recommendationViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(RecommendationViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttons = new JPanel();
        refresh = new JButton(RecommendationViewModel.REFRESH_BUTTON_LABEL);
        buttons.add(refresh);
        cancel = new JButton(RecommendationViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        Set<String> recipe_name = recommendationViewModel.getState().get().keySet();
        Map<String, Recipe> recipe_map = recommendationViewModel.getState().get();
        for (String buttonName: recipe_name){
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
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(refresh)) {
                    recommendationController.execute();
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(cancel)) {
                    recommendationController.execute();
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

    }
}