/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Ryan Hodge
 */

package baseline;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public ToDoListManager manager = new ToDoListManager();

    @FXML
    public ListView toDoListingBox;

    @FXML
    public ListView toDoListItemsBox;

    @FXML
    public Scene scene;

    @FXML
    public TextField newListInputField;

    @FXML
    public TextField renameListField;

    @FXML
    private Button addTaskButton;

    /**
     * Creates a list using the text inside the adjacent text field.
     *
     * ---
     * Create a new ToDoList and name it whatever is inside newListInputField
     * Clear the text inside newListInputField
     * ---
     */
    @FXML
    public void createList() {
        String toDoListName = newListInputField.getText();
        newListInputField.clear();

        ToDoList list = ToDoListManager.createList(toDoListName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scene = newListInputField.getScene();
    }
}
