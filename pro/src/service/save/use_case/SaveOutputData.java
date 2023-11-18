package service.save.use_case;

import java.util.Set;

public class SaveOutputData {
    private String recipeName;
    private boolean useCaseFailed;

    public SaveOutputData(String recipeName, boolean useCaseFailed) {
        this.recipeName = recipeName;
        this.useCaseFailed = useCaseFailed;
    }

    public String getRecipeName() {
        return recipeName;
    }

}
