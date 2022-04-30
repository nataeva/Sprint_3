package util;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.*;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;

public class BaseTest {
    static {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    private static final String courierEndpoint = "/api/v1/courier";
    private static final String courierAuthEndpoint = "/api/v1/courier/login";
    private static final String orderEndpoint = "/api/v1/orders";

    public static void createCourier(Courier courier,
                                     int expectedCode,
                                     String bodyField,
                                     Object expectedBodyFieldValue) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(courier)
                .post(courierEndpoint);

        response.then()
                .assertThat()
                .statusCode(expectedCode)
                .and()
                .body(bodyField, Matchers.is(expectedBodyFieldValue));
    }

    public static CourierAuthDetails authorizeCourier(CourierAuth courierAuthDetails,
                                                      int expectedCode) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(courierAuthDetails)
                .post(courierAuthEndpoint);

        response.then()
                .assertThat()
                .statusCode(expectedCode);

        return response.as(CourierAuthDetails.class);
    }

    public static void authorizeCourier(CourierAuth courierAuthDetails,
                                        int expectedCode,
                                        String bodyField,
                                        Object expectedBodyFieldValue) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(courierAuthDetails)
                .post(courierAuthEndpoint);

        response.then()
                .assertThat()
                .statusCode(expectedCode)
                .and()
                .body(bodyField, Matchers.is(expectedBodyFieldValue));
    }

    public static void DELETECourier(CourierAuthDetails courierAuthDetails) {
        given()
                .header("Content-type", "application/json")
                .body(courierAuthDetails)
                .delete(courierEndpoint);
    }

    public void createOrder(Order order,
                            int expectedCode,
                            String bodyField) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(order)
                .post(orderEndpoint);

        response.then()
                .statusCode(expectedCode)
                .and()
                .body(bodyField, Matchers.is(Matchers.notNullValue()));
    }

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
