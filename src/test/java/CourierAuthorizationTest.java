import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import util.BaseTest;
import model.Courier;
import model.CourierAuth;
import model.CourierAuthDetails;

import java.util.UUID;

public class CourierAuthorizationTest extends BaseTest {
    private static Courier courier;
    private static CourierAuthDetails courierAuthDetails;

    @BeforeClass
    public static void setup() {
        courier = new Courier("enataeva" + UUID.randomUUID(),
                "password",
                "Elena");
        createCourier(courier,
                201,
                "ok",
                true);
    }

    @AfterClass
    public static void tearDown() {
        DELETECourier(courierAuthDetails);
    }

    @Test
    public void successfulAuth() {
        CourierAuth courierAuth = new CourierAuth(courier.getLogin(), courier.getPassword());
        courierAuthDetails = authorizeCourier(courierAuth, 200);
    }

//    Что значит "передать все обязательные поля"? С нуллами апишка не возвращает ответ вообще
    @Test
    public void unsuccessfulAuthWithoutAllFields() {
        CourierAuth courierAuth1 = new CourierAuth("", courier.getPassword());
        CourierAuth courierAuth2 = new CourierAuth(courier.getLogin(), "");

        authorizeCourier(courierAuth1,
                400,
                "message",
                "Недостаточно данных для входа");
        authorizeCourier(courierAuth2,
                400,
                "message",
                "Недостаточно данных для входа");
    }

    @Test
    public void unsuccessfulAuthForNonExistingCourier() {
        CourierAuth courierAuth1 = new CourierAuth(UUID.randomUUID().toString(), "myPassword");

        authorizeCourier(courierAuth1,
                404,
                "message",
                "Учетная запись не найдена");
    }
}
