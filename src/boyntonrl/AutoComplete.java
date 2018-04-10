/*
 * CS2852 - 021
 * Spring 2018
 * Lab 4 - AutoCompleter
 * Name: Rock Boynton
 * Created: 3/28/2018
 */

package boyntonrl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Main class for a JavaFX autocomplete application. This application implements four strategies
 * for looking up words that begin with the specified prefix and compare the performance of each
 * strategy.
 */
public class AutoComplete extends Application {

    /**
     * Logger to log information and errors to a log file
     */
    public static final Logger LOGGER = Logger.getLogger(AutoComplete.class.getName());

    /**
     * Width of Stage
     */
    public static final int WIDTH = 550;

    /**
     * Height of Stage
     */
    public static final int HEIGHT = 600;

    /**
     * Sets up the primary stage and a logger
     * @param primaryStage primary stage of the program
     * @throws Exception if anything goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("autocomplete.fxml"));
        setUpLogger();
        primaryStage.setTitle("AutoCompleter");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void setUpLogger() {
        LOGGER.setUseParentHandlers(false);
        try {
            FileHandler fh = new FileHandler(System.getProperty("user.dir") +
                    File.separator + "autocomplete.txt", true);
            LOGGER.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            LOGGER.severe("Cannot create log file");
            Alert logFileAlert = new Alert(Alert.AlertType.ERROR, "Error with Log" +
                    " file ");
            logFileAlert.setTitle("Error Dialog");
            logFileAlert.setHeaderText("Log file error");
            logFileAlert.showAndWait();
        }
    }
}
