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

    static final String courierEndpoint = "/api/v1/courier";
    static final String courierAuthEndpoint = "/api/v1/courier/login";
    static final String orderEndpoint = "/api/v1/orders";
}
