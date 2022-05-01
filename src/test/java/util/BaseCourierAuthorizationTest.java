package util;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.ApiResponseWithMessage;
import model.CourierAuth;
import model.CourierAuthDetails;

import static io.restassured.RestAssured.given;

public class BaseCourierAuthorizationTest extends BaseTest{

    @Step("Authorize courier")
    protected static CourierAuthDetails authorizeCourier(CourierAuth courierAuthDetails) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(courierAuthDetails)
                .post(courierAuthEndpoint);

        return response.as(CourierAuthDetails.class);
    }

    @Step("Unsuccessful attempt at authorization")
    protected static ApiResponseWithMessage unsuccessfullyAuthorizeCourier(CourierAuth courierAuthDetails) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(courierAuthDetails)
                .post(courierAuthEndpoint);

        return response.as(ApiResponseWithMessage.class);
    }
}
