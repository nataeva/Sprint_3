import org.junit.Test;
import util.BaseTest;
import model.Courier;

import java.util.UUID;

public class CourierCreationTest extends BaseTest {

    @Test
    public void happyPass() {
        Courier courier = new Courier(getCourierLogin(), "test", "test");

        createCourier(courier, 201, "ok", true);
    }

    @Test
    public void shouldNotBePossibleToCreateTwoIdenticalCouriers() {
        Courier courier = new Courier(getCourierLogin(), "test", "test");

        createCourier(courier, 201, "ok", true);
        createCourier(courier, 409, "message", "Этот логин уже используется. Попробуйте другой.");
    }

    @Test
    public void courierCannotBeCreatedWithoutAllMandatoryFields() {
        Courier courier1 = new Courier(null, "test", "test");
        Courier courier2 = new Courier(getCourierLogin(), null, "test");

        createCourier(courier1, 400, "message", "Недостаточно данных для создания учетной записи");
        createCourier(courier2, 400, "message", "Недостаточно данных для создания учетной записи");
    }

    private String getCourierLogin() {
        return "test" + UUID.randomUUID();
    }
}
