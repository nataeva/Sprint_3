package model;


import java.util.List;

public class OrderList {
    private List<Order> orders;
    private PageInfo pageInfo;
    private List<Station> availableStations;

    public OrderList() {
    }

    public OrderList(List<Order> orders, PageInfo pageInfo, List<Station> availableStations) {
        this.orders = orders;
        this.pageInfo = pageInfo;
        this.availableStations = availableStations;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public List<Station> getAvailableStations() {
        return availableStations;
    }
}
