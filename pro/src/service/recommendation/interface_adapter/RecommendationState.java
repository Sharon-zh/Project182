package service.recommendation.interface_adapter;

import entity.Recipe;

import java.util.HashMap;
import java.util.Map;

public class RecommendationState {
    private Map<String, Recipe> random = new HashMap<>();

    public RecommendationState(){}

    public Map<String, Recipe> get(){
        return random;
    }

    public void set(Map<String, Recipe> random) {
        this.random = random;
    }
}
