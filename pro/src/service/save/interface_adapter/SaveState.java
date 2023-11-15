package service.save.interface_adapter;

public class SaveState {
    private String successMessage = "";


    public void ClearState(SaveState copy) {
        successMessage = copy.successMessage;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SaveState() {}

    public String getSuccessMessage() {
        return successMessage;
    }
    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

}
