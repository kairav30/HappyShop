package ci553.happyshop.client.customer;

import ci553.happyshop.catalogue.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerModelTestCheckout {

    CustomerModel model;

    @BeforeEach
    void setUp() {
        // Disable UI but create customermodel
        model = new CustomerModel() {
            @Override
            void updateView() {
            }
        };

        // Stub data
        model.databaseRW = new StubDatabaseRW();

        model.cusView = null; // no view necessary
    }

    @Test
    void checkOut() throws Exception {

        // Arrange: add products to trolley
        Product p1 = new Product("0001", "Watch", "0001.jpg", 99.99, 10);
        p1.setOrderedQuantity(2);

        Product p2 = new Product("0002", "Phone", "0002.jpg", 499.99, 5);
        p2.setOrderedQuantity(1);

        model.getTrolley().add(p1);
        model.getTrolley().add(p2);

        assertEquals(2, model.getTrolley().size());

        // Act
        model.checkOut();

        // Assert
        assertTrue(model.getTrolley().isEmpty(), "Trolley should be empty after checkout");

    }
}