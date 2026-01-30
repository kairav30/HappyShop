package ci553.happyshop.client.customer;

import ci553.happyshop.catalogue.Product;
import ci553.happyshop.storageAccess.DatabaseRW;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CustomerModelTestCheckout {

    CustomerModel model;

    @BeforeEach
    void setUp() {
        model = new CustomerModel() {

            @Override
            void updateView() {
                // Disable UI updates
            }

            @Override
            void checkOut() throws SQLException {
                // Simulate checkout logic WITHOUT OrderHub/file writing
                if (!getTrolley().isEmpty()) {
                    ArrayList<Product> grouped = getTrolley();
                    ArrayList<Product> insufficient = databaseRW.purchaseStocks(grouped);

                    if (insufficient.isEmpty()) {
                        getTrolley().clear(); // simulate successful checkout
                    }
                }
            }
        };

        model.databaseRW = new StubDatabaseRW();
        model.cusView = null;
    }

    @Test
    void checkOut() throws Exception {

        Product p1 = new Product("0001", "Watch", "0001.jpg", 99.99, 10);
        p1.setOrderedQuantity(2);

        Product p2 = new Product("0002", "Phone", "0002.jpg", 499.99, 5);
        p2.setOrderedQuantity(1);

        model.getTrolley().add(p1);
        model.getTrolley().add(p2);

        assertEquals(2, model.getTrolley().size());

        model.checkOut();

        assertTrue(model.getTrolley().isEmpty(), "Trolley should be empty after checkout");
    }

    // ---------------- STUB DATABASE ----------------

    class StubDatabaseRW implements DatabaseRW {

        @Override
        public ArrayList<Product> purchaseStocks(ArrayList<Product> products) throws SQLException {
            return new ArrayList<>(); // success
        }

        @Override
        public Product searchByProductId(String id) {
            return null;
        }

        @Override
        public ArrayList<Product> searchProduct(String keyword) {
            return new ArrayList<>();
        }

        @Override
        public void updateProduct(String id, String description, double price, String image, int stock) {
        }

        @Override
        public void deleteProduct(String productId) {
        }

        @Override
        public void insertNewProduct(String id, String des, double price, String image, int stock) throws SQLException {
        }

        @Override
        public boolean isProIdAvailable(String productId) throws SQLException {
            return false;
        }
    }
}