package model;

public class ApiResponse {
    private boolean ok;

    public ApiResponse() {
    }

    public ApiResponse(boolean ok) {
        this.ok = ok;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
