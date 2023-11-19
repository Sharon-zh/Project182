package service.delete.interface_adapter;

public class DeleteState {
    private String successMessage = "";


    public DeleteState(DeleteState copy) {
        successMessage = copy.successMessage;
    }

    public DeleteState() {}

    public String getSuccessMessage() {
        return successMessage;
    }
    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

}
