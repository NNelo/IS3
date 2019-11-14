package payroll;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OrderTest {

    @Test
    public void TestBasic() {
    	Order order = new Order("muy cara", null);
    	assertEquals(order.getDescription(), "muy cara");
    }
}
