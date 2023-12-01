package service.recommendation.interface_adapter;

import entity.RecommendedRecipes;

public class RecommendationState {
    private RecommendedRecipes random;
    private String username = "";

    public RecommendationState(){}

    public RecommendedRecipes get(){
        return random;
    }

    public void set(RecommendedRecipes random) {
        this.random = random;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }
}
