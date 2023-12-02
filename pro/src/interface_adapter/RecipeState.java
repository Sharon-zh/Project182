//package interface_adapter;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//
//public class RecipeState {
//    private String name = "";
////    private String category = "";
////    private String instructions = "";
////    private HashMap<String, String> ingredients = new LinkedHashMap<>();
////    private int likes = 0;
////    private ArrayList<String> comments = new ArrayList<>();
////    private String imageLink = "";
////    private String youtubeLink = "";
//
////    public RecipeState(RecipeState copy) {
////        name = copy.name;
////        category = copy.category;
////        instructions = copy.instructions;
////        ingredients = copy.ingredients;
////        likes = copy.likes;
////        comments = copy.comments;
////        imageLink = copy.imageLink;
////        youtubeLink = copy.youtubeLink;
////    }
//
//    // Because of the previous copy constructor, the default constructor must be explicit.
////    public RecipeState() {}
//
//    public String getName() {
//        return name;
//    }
//
////    public String getCategory() {
////        return category;
////    }
////
////    public String getInstructions() {
////        return instructions;
////    }
//
////    public String getIngredients() {
////        String result = ingredients.entrySet().stream()
////                .map(entry -> entry.getKey() + "=" + entry.getValue())
////                .reduce((s1, s2) -> s1 + ";" + s2)
////                .orElse("");
////        return result;
////    }
////
////    public String getLikes() {
////        return String.valueOf(likes);
////    }
////
////    public String getComments() {
////        return String.join(", ", comments);
////    }
////
////    public String getImageLink() {
////        return imageLink;
////    }
////
////    public String getYoutubeLink() {
////        return youtubeLink;
////    }
//}
