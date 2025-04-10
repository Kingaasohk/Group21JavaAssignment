package com.example.pharmacyinventorysystem;

// Import necessary JavaFX classes
import javafx.application.Application; // Base class for JavaFX applications
import javafx.fxml.FXMLLoader; // Loads FXML files (UI)
import javafx.scene.Scene; // Represents a window in JavaFX
import javafx.stage.Stage; // Represents the primary application window
import java.io.IOException; // Handles input/output errors

/**
 * This class serves as the main entry point for the Car Rental System application.
 * It sets up the primary window (Stage) and handles scene switching.
 */
public class HelloApplication extends Application {

    private static Stage primaryStage; // Store reference to the primary window (Stage)

    /**
     * This method is called when the application starts.
     * It initializes the primary stage and loads the first scene (main-view.fxml).
     *
     * @param stage The main window of the application.
     * @throws IOException If the FXML file is not found.
     */
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage; // Store the primary stage reference
        switchScene("main-view.fxml"); // Load the main page first
        primaryStage.setTitle("Pharmacy Inventory Manager"); // Set the window title
        primaryStage.show(); // Display the window
    }

    /**
     * Switches the scene to a new FXML file.
     * This method allows dynamic navigation between different screens.
     *
     * @param fxmlFile The FXML file to load.
     * @throws IOException If the FXML file is not found.
     */
    public static void switchScene(String fxmlFile) throws IOException {
        // Load the requested FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
        // Create a new scene with the loaded FXML content
        Scene scene = new Scene(fxmlLoader.load());
        // Set the new scene in the primary stage
        primaryStage.setScene(scene);
    }

    /**
     * The main method of the application.
     * Calls launch(), which starts the JavaFX application lifecycle.
     *
     * @param args Command-line arguments (not used here).
     */
    public static void main(String[] args) {
        launch(args); // Start the JavaFX application
    }
}
