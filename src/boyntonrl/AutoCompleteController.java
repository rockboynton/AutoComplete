/*
 * CS2852 - 021
 * Spring 2018
 * Lab 4 - AutoCompleter
 * Name: Rock Boynton
 * Created: 3/28/2018
 */

package boyntonrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static javafx.scene.input.KeyCode.BACK_SPACE;

/**
 * Controller class for the AutoComplete JavaFX application
 */
public class AutoCompleteController implements Initializable{

    Logger LOGGER = AutoComplete.LOGGER;

    private AutoCompleter autoCompleter;

    @FXML
    private TextField searchBox;

    @FXML
    private ScrollPane scroll;
    @FXML
    private TextArea matches;

    @FXML
    private Label timeRequired;
    @FXML
    private Label matchesFound;

    @FXML
    private ToggleGroup strategies;

    @FXML
    private void search(KeyEvent e) {
        matches.setText("");
        List<String> words = autoCompleter.allThatBeginWith(searchBox.getText());
        String time = formatTimeRequired(autoCompleter.getLastOperationTime());
//        for (String word : words) {
//            matches.appendText(word + "\n");
//        }
        words.forEach(word -> matches.appendText(word + "\n"));
        timeRequired.setText("Time Required: " + time);
        matchesFound.setText("Matches Found: " + words.size());
        matches.setScrollTop(matches.getScrollTop());
    }

    private String formatTimeRequired(long nsTime) {
        String formattedTime;
        if (nsTime > 10e9) {
            formattedTime = formatTimeInSeconds(nsTime);
        } else if (nsTime > 10e6) {
            formattedTime = formatTimeInMilliseconds(nsTime);
        } else {
            formattedTime = nsTime + " nanoseconds";
        }
        return formattedTime;
    }

    private String formatTimeInMilliseconds(long nsTime) {
        return TimeUnit.NANOSECONDS.toMillis(nsTime) + " milliseconds";
    }

    private String formatTimeInSeconds(long nsTime) {
        final long hr = TimeUnit.NANOSECONDS.toHours(nsTime);
        final long min = TimeUnit.NANOSECONDS.toMinutes(nsTime - TimeUnit.HOURS.toNanos(hr));
        final long sec = TimeUnit.NANOSECONDS.toSeconds(nsTime - TimeUnit.HOURS.toNanos(hr) -
                TimeUnit.MINUTES.toNanos(min));
        final long ms = TimeUnit.NANOSECONDS.toMillis(nsTime - TimeUnit.HOURS.toNanos(hr) -
                TimeUnit.MINUTES.toNanos(min) - TimeUnit.SECONDS.toNanos(sec));
        // hh:mm:ss.sss
        return String.format("%02d:%02d:%02d.%03d", hr, min, sec, ms);
    }

    @FXML
    private void setArrayIndex(ActionEvent e) {
        autoCompleter = new IndexAutoCompleter(new ArrayList<>());
    }

    @FXML
    private void setArrayIterator(ActionEvent e) {
        autoCompleter = new IteratorAutoCompleter(new ArrayList<>());
    }

    @FXML
    private void setLinkedIndex(ActionEvent e) {
        autoCompleter = new IndexAutoCompleter(new LinkedList<>());
    }

    @FXML
    private void setLinkedIterator(ActionEvent e) {
        autoCompleter = new IteratorAutoCompleter(new LinkedList<>());
    }

    @FXML
    private void open(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Dictionary Files", "*.txt",
                        "*.csv"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                autoCompleter.initialize(file.getPath());
                LOGGER.info("Successfully loaded dictionary");
            } catch (IOException ioe) {
                LOGGER.severe("File failed to open");
                showReadFailureAlert();
            }
        }
        timeRequired.setText("Time Required: " + autoCompleter.getLastOperationTime());
    }

    /**
     * Sets the default strategy for autocompletion to using ArrayList and Indexing
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        autoCompleter = new IndexAutoCompleter(new ArrayList<>());
    }

    private static void showReadFailureAlert() {
        Alert readFailureAlert = new Alert(Alert.AlertType.ERROR, "Error: Could not " +
                "read words from specified file. File may be corrupt.");
        readFailureAlert.setTitle("Error Dialog");
        readFailureAlert.setHeaderText("Read Failure");
        readFailureAlert.showAndWait();
    }
}
