import model.ApiResponseWithMessage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import util.BaseCourierAuthorizationTest;
import model.Courier;
import model.CourierAuth;
import model.CourierAuthDetails;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static util.BaseCourierTest.*;

public class CourierAuthorizationTest extends BaseCourierAuthorizationTest {
    private static final String AUTH_ATTEMPT_SHOULD_HAVE_FAILED = "Auth attempt should have failed!";
    private static final String INSUFFICIENT_DATA = "Недостаточно данных для входа";
    private static Courier courier;
    private static CourierAuthDetails courierAuthDetails;

    @BeforeClass
    public static void setup() {
        courier = new Courier("enataeva" + UUID.randomUUID(),
                "password",
                "Elena");
        createCourier(courier);
    }

    @AfterClass
    public static void tearDown() {
        deleteCourier(courierAuthDetails);
    }

    @Test
    public void successfulAuth() {
        CourierAuth courierAuth = new CourierAuth(courier.getLogin(), courier.getPassword());
        courierAuthDetails = authorizeCourier(courierAuth);
    }

    @Test
    public void unsuccessfulAuthWithoutAllFields() {
        CourierAuth courierAuth1 = new CourierAuth(null, courier.getPassword());
        CourierAuth courierAuth2 = new CourierAuth(courier.getLogin(), null);

        ApiResponseWithMessage response1 = unsuccessfullyAuthorizeCourier(courierAuth1);

        assertEquals("", INSUFFICIENT_DATA, response1.getMessage());

        ApiResponseWithMessage response2 = unsuccessfullyAuthorizeCourier(courierAuth2);

        assertEquals("", INSUFFICIENT_DATA, response2.getMessage());
    }

    @Test
    public void unsuccessfulAuthForNonExistingCourier() {
        CourierAuth courierAuth1 = new CourierAuth(UUID.randomUUID().toString(), "myPassword");

        ApiResponseWithMessage response = unsuccessfullyAuthorizeCourier(courierAuth1);

        assertEquals(AUTH_ATTEMPT_SHOULD_HAVE_FAILED, "Учетная запись не найдена", response.getMessage());
    }
}
