package cinema.model;

public class ErrorResponseObject {

    private String error;

    public ErrorResponseObject(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
