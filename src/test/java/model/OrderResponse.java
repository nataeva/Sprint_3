package model;

public class OrderResponse {
    private String track;

    public OrderResponse() {
    }

    public OrderResponse(String track) {
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }
}
