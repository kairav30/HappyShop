package ci553.happyshop.catalogue;
import ci553.happyshop.catalogue.Product;
import ci553.happyshop.orderManagement.OrderState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    // Declaring variable
    private Order order;

    @BeforeEach
        // Create test data to be used in each test
    void setUp() {
        Product product = new Product("0004", "Watch","0004.jpg",29.99,2);
        // Set order quantity
        product.setOrderedQuantity(2);
        // Hold product in list
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        // Create new order
        order = new Order(
                500,
                OrderState.Ordered,
                "23/05/2026 11:11",
                products
        );
    }

    // Tests order id is correct
    @Test
    void getOrderId() {
        assertEquals(500, order.getOrderId());
    }

    // Tests that a timestamp is generated
    @Test
    void getOrderedDateTime() {
        assertNotNull(order.getOrderedDateTime());
    }

    // Tests that the products have been stored
    @Test
    void getProductList() {
        assertNotNull(order.getProductList());
        assertEquals(1, order.getProductList().size());
    }

    // Tests that order is stored and returns in test result
    @Test
    void orderDetails() {
        String details = order.orderDetails();
        System.out.println(details);
        assertTrue(details.contains("500"));
        assertTrue(details.contains("Ordered"));
        assertTrue(details.contains("23/05/2026 11:11"));
        //assertEquals(2,order.getProductList().getFirst().getOrderedQuantity());
    }
}