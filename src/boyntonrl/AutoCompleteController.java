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
import java.util.logging.Logger;

import static javafx.scene.input.KeyCode.BACK_SPACE;

/**
 * Controller class for the AutoComplete JavaFX application
 */
public class AutoCompleteController implements Initializable{

    Logger LOGGER = AutoComplete.LOGGER;

    private AutoCompleter autoCompleter;
    private List<String> words;


    @FXML
    private MenuItem open;
    @FXML
    private MenuItem arrayListIndex;
    @FXML
    private MenuItem arrayListIterator;
    @FXML
    private MenuItem linkedListIndex;
    @FXML
    private MenuItem linkedListIterator;

    @FXML
    private TextField searchBox;

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
        words = autoCompleter.allThatBeginWith(searchBox.getText());
        if (e.getCode() == BACK_SPACE) {
            matches.setText("NO");
        } else {
            for (String word : words) {
                matches.appendText(word + "\n");
            }
        }
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
    }

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
