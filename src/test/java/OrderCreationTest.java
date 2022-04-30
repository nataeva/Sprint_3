import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import util.BaseTest;
import model.Order;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderCreationTest extends BaseTest {

    @Parameterized.Parameter
    public String[] colors;

    @Parameterized.Parameters
    public static Collection params() {
        return Arrays.asList(new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[]{}}
        });
    }

    @Test
    public void successfulOrderCreationWithDifferentColors() {
        Order order = new Order("firstName",
                "lastName",
                "address",
                "metro",
                "phone",
                99,
                "2020-06-06",
                "comment",
                colors);

        createOrder(order, 201, "track");
    }
}
