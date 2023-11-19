package service.delete.use_case;

public class DeleteOutputData {
    private String recipeName;
    private boolean useCaseFailed;

    public DeleteOutputData(String recipeName, boolean useCaseFailed) {
        this.recipeName = recipeName;
        this.useCaseFailed = useCaseFailed;
    }

    public String getRecipeName() {
        return recipeName;
    }

}
