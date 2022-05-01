import model.ApiResponse;
import model.ApiResponseWithMessage;
import org.junit.Test;
import util.BaseCourierTest;
import model.Courier;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourierTest extends BaseCourierTest {

    private static final String TEST_STRING = "test";
    private static final String MESSAGE_BODY_FIELD = "message";
    private static final String INSUFFICIENT_DATA = "Недостаточно данных для создания учетной записи";

    @Test
    public void happyPass() {
        Courier courier = new Courier(getCourierLogin(), TEST_STRING, TEST_STRING);

        ApiResponse response = createCourier(courier);
        assertTrue("Courier should have been created!", response.isOk());
    }

    @Test
    public void shouldNotBePossibleToCreateTwoIdenticalCouriers() {
        Courier courier = new Courier(getCourierLogin(), TEST_STRING, TEST_STRING);

        ApiResponse response1 = createCourier(courier);
        assertTrue("Courier should have been created!", response1.isOk());

        ApiResponseWithMessage response2 = unsuccessfullyCreateCourier(courier);
        assertEquals("Courier should have been created!",
                "Этот логин уже используется. Попробуйте другой.",
                response2.getMessage());
    }

    @Test
    public void courierCannotBeCreatedWithoutAllMandatoryFields() {
        Courier courier1 = new Courier(null, TEST_STRING, TEST_STRING);
        Courier courier2 = new Courier(getCourierLogin(), null, TEST_STRING);

        ApiResponseWithMessage response1 = unsuccessfullyCreateCourier(courier1);
        assertEquals("Message should have been: " + INSUFFICIENT_DATA,
                INSUFFICIENT_DATA,
                response1.getMessage());

        ApiResponseWithMessage response2 = unsuccessfullyCreateCourier(courier2);
        assertEquals("Message should have been: " + INSUFFICIENT_DATA,
                INSUFFICIENT_DATA,
                response2.getMessage());
    }

    private String getCourierLogin() {
        return TEST_STRING + UUID.randomUUID();
    }
}
