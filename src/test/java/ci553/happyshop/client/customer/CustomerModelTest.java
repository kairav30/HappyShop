package ci553.happyshop.client.customer;

import javafx.application.Platform;
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


    }
}