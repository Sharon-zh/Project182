package service.save.interface_adapter;

public class SaveState {
    private String successMessage = "";


    public SaveState(SaveState copy) {
        successMessage = copy.successMessage;
    }

    public SaveState() {}

    public String getSuccessMessage() {
        return successMessage;
    }
    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

}
