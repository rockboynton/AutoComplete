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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

/**
 * Controller class for the AutoComplete JavaFX application
 */
public class AutoCompleteController {

    AutoCompleter autoCompleter;

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
    private ScrollPane matches;

    @FXML
    private Label timeRequired;
    @FXML
    private Label matchesFound;

    @FXML
    private void search(ActionEvent e) {

    }

    @FXML
    private void setStrategy(ActionEvent e) {

    }

    @FXML
    private void open(ActionEvent e) {

    }
}
