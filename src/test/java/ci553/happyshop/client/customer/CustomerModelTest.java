package ci553.happyshop.client.customer;

import ci553.happyshop.catalogue.Product;
import javafx.application.Platform;
import javafx.scene.control.ComboBox;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerModelTest {

    @BeforeAll
    static void initJavaFX() {
        Platform.startup(() -> {});
    }
    @Test
    void addToTrolley() {
        // Setup
        CustomerModel model = new CustomerModel() {
            @Override
            void updateView() {
                // Disable UI update for test
            }
        };
        CustomerView view = new CustomerView();

        model.cusView = view;

        // Simulating data
        view.cbQuantity_Levels = new ComboBox<>();
        view.cbQuantity_Levels.getItems().addAll(1, 2, 3, 4, 5);
        view.cbQuantity_Levels.setValue(3);

        // Searched for specific product
        Product product = new Product(
                "0001",
                "Watch",
                "0001.jpg",
                99.99,
                10
        );

        model.setTheProduct(product);

        model.addToTrolley();

        // Assertions
        assertEquals(1, model.getTrolley().size());
        assertEquals(3, model.getTrolley().get(0).getOrderedQuantity());
    }
}