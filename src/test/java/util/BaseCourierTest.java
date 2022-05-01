package util;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.ApiResponseWithMessage;
import model.ApiResponse;
import model.Courier;
import model.CourierAuthDetails;

import static io.restassured.RestAssured.given;

public class BaseCourierTest extends BaseTest{

    @Step("Create courier")
    public static ApiResponse createCourier(Courier courier) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(courier)
                .post(courierEndpoint);

        return response.as(ApiResponse.class);
    }

    @Step("Create courier")
    public static ApiResponseWithMessage unsuccessfullyCreateCourier(Courier courier) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(courier)
                .post(courierEndpoint);

        return response.as(ApiResponseWithMessage.class);
    }

    @Step("Delete courier")
    public static void deleteCourier(CourierAuthDetails courierAuthDetails) {
        given()
                .header("Content-type", "application/json")
                .body(courierAuthDetails)
                .delete(courierEndpoint);
    }
}
