package util;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Order;
import model.OrderList;
import model.OrderResponse;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;

public class BaseOrderTest extends BaseTest{

    @Step("Create order")
    public OrderResponse createOrder(Order order) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(order)
                .post(orderEndpoint);

        return response.as(OrderResponse.class);
    }

    @Step("Get order list")
    public static OrderList getOrderList() {
        Response response = given()
                .header("Content-type", "application/json")
                .get(orderEndpoint);

        response.then()
                .assertThat()
                .statusCode(200);

        return response.as(OrderList.class);
    }
}
