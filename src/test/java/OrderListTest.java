import org.junit.Test;
import util.BaseTest;
import model.OrderList;

import static org.junit.Assert.assertTrue;

public class OrderListTest extends BaseTest {

    @Test
    public void successfulOrderListRequest() {
        OrderList orderList = getOrderList();

        assertTrue(orderList.getOrders().size() > 0);
    }
}
