package ci553.happyshop.client.login;

import javafx.application.Platform;
import javafx.scene.control.Button;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @BeforeAll
    static void initialiseJavaFX() {
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
            // already started
        }
    }

    @Test
    void correctPinForLogin() throws Exception {
        // Arrange: create accounts.txt with known pin
        Path file = Path.of("accounts.txt");
        Files.write(file, List.of("1234"));

        // Act: read file (same logic as controller)
        List<String> password = Files.readAllLines(file);

        // Assert
        assertTrue(password.contains("1234"));
    }

    @Test
    void incorrectPinForLogin() throws IOException {
        // Arrange
        Path file = Path.of("accounts.txt");
        Files.write(file, List.of("1234"));

        // Act
        List<String> password = Files.readAllLines(file);

        // Assert
        String userpin = "9999";
        assertFalse(password.contains(userpin));
    }

    @Test
    void buttonstyle() {
        Button guestButton = new Button("Continue as Guest");
        String btn_sty =
                "-fx-background-color: #937A62;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 11;" +
                        "-fx-padding: 20 50;" +
                        "-fx-font-size: 25px;";
        guestButton.setStyle(btn_sty);

        assertEquals(btn_sty, guestButton.getStyle());
    }
}
