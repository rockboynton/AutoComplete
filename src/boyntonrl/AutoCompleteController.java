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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Controller class for the AutoComplete JavaFX application
 */
public class AutoCompleteController implements Initializable {

    private static final Logger LOGGER = AutoComplete.LOGGER;

    private AutoCompleter autoCompleter;
    private File dictionary;

    @FXML
    private TextField searchBox;
    @FXML
    private TextArea matches;
    @FXML
    private Label timeRequired;
    @FXML
    private Label matchesFound;

    @FXML
    private void search(KeyEvent e) {
        timeRequired.setText("Time Required: ");
        matches.setText("");
        List<String> words;
        if (searchBox.getText().length() > 0) {
            words = autoCompleter.allThatBeginWith(searchBox.getText());
        } else {
            words = autoCompleter.allThatBeginWith("");
        }
        String time = formatTimeRequired(autoCompleter.getLastOperationTime());
        StringBuilder res = new StringBuilder();
        for (String word : words) {
            res.append(word).append("\n");
        }
        matches.setText(res.toString());
        matchesFound.setText("Matches Found: " + words.size());
        timeRequired.setText("Time Required: " + time);
        matches.setScrollTop(matches.getScrollTop());
    }

    private static String formatTimeRequired(long nsTime) {
        String formattedTime;
        if (nsTime > TimeUnit.SECONDS.toNanos(1)) {
            formattedTime = formatTimeInSeconds(nsTime);
        } else if (nsTime > TimeUnit.MILLISECONDS.toNanos(1)) {
            formattedTime = formatTimeInMilliseconds(nsTime);
        } else if (nsTime > TimeUnit.MICROSECONDS.toNanos(1)){
            formattedTime = formatTimeInMicroseconds(nsTime);
        } else {
            formattedTime = nsTime + " nanoseconds";
        }
        return formattedTime;
    }

    private static String formatTimeInMicroseconds(long nsTime) {
        return TimeUnit.NANOSECONDS.toMicros(nsTime) + " microseconds";
    }

    private static String formatTimeInMilliseconds(long nsTime) {
        return TimeUnit.NANOSECONDS.toMillis(nsTime) + " milliseconds";
    }

    private static String formatTimeInSeconds(long nsTime) {
        final long min = TimeUnit.NANOSECONDS.toMinutes(nsTime);
        final long sec = TimeUnit.NANOSECONDS.toSeconds(nsTime -
                TimeUnit.MINUTES.toNanos(min));
        final long ms = TimeUnit.NANOSECONDS.toMillis(nsTime -
                TimeUnit.MINUTES.toNanos(min) - TimeUnit.SECONDS.toNanos(sec));
        // mm:ss.sss
        return String.format("%02d:%02d.%03d", min, sec, ms);
    }

    @FXML
    private void setArrayIndex(ActionEvent e) {
        autoCompleter = new IndexAutoCompleter(new ArrayList<>());
        initializeAutoCompleter(dictionary);
    }

    @FXML
    private void setArrayIterator(ActionEvent e) {
        autoCompleter = new IteratorAutoCompleter(new ArrayList<>());
        initializeAutoCompleter(dictionary);
    }

    @FXML
    private void setLinkedIndex(ActionEvent e) {
        autoCompleter = new IndexAutoCompleter(new LinkedList<>());
        initializeAutoCompleter(dictionary);
    }

    @FXML
    private void setLinkedIterator(ActionEvent e) {
        autoCompleter = new IteratorAutoCompleter(new LinkedList<>());
        initializeAutoCompleter(dictionary);
    }

    @FXML
    private void setTrieMap(ActionEvent e) {
        autoCompleter = new TrieMapAutoCompleter(new Trie());
        initializeAutoCompleter(dictionary);
    }

    @FXML
    private void setArrayListBinarySearch(ActionEvent e) {
        autoCompleter = new IteratorAutoCompleter(new LinkedList<>());
        initializeAutoCompleter(dictionary);
    }

    @FXML
    private void open(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Dictionary Files", "*.txt",
                        "*.csv"));
        dictionary = fileChooser.showOpenDialog(null);
        initializeAutoCompleter(dictionary);
        timeRequired.setText("Time Required: " + formatTimeRequired(
                autoCompleter.getLastOperationTime()));
        searchBox.setDisable(false);
        searchBox.setText("");
        matches.setText("");
        searchBox.setPromptText("What would you like to search?");
    }

    private void initializeAutoCompleter(File file) {
        if (file != null) {
            try {
                autoCompleter.initialize(dictionary.getPath());
                LOGGER.info("Successfully loaded dictionary");
            } catch (IOException ioe) {
                LOGGER.severe("File failed to open");
                showReadFailureAlert();
            }
        }
    }

    /**
     * Sets the default strategy for autocompletion to using ArrayList and Indexing
     * @param location URL location
     * @param resources ResourceBundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        autoCompleter = new IndexAutoCompleter(new ArrayList<>());
        searchBox.setText("Please select a dictionary file");
        searchBox.setDisable(true);
    }

    private static void showReadFailureAlert() {
        Alert readFailureAlert = new Alert(Alert.AlertType.ERROR, "Error: Could not " +
                "read words from specified file. File may be corrupt.");
        readFailureAlert.setTitle("Error Dialog");
        readFailureAlert.setHeaderText("Read Failure");
        readFailureAlert.showAndWait();
    }
}
