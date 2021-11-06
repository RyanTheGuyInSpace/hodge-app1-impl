/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Ryan Hodge
 */

package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public ToDoListManager manager = new ToDoListManager();

    ObservableList<ToDoList> toDoLists = FXCollections.observableArrayList();

    ObservableList<Task> tasks = FXCollections.observableArrayList();

    @FXML
    public ListView toDoListingBox;

    @FXML
    public ListView toDoListItemsBox;

    @FXML
    public Scene scene;

    @FXML
    public TextField newListInputField;

    @FXML
    public Button newListButton;

    @FXML
    public TextField addTaskField;

    @FXML
    private Button addTaskButton;

    public long selectedItemIndex = 0;

    /**
     * Creates a list using the text inside the adjacent text field.
     *
     * ---
     * Create a new ToDoList and name it whatever is inside newListInputField
     * If newListInputField is blank, do nothing
     * Clear the text inside newListInputField
     * Open up FileChooser so the user can choose where to store their ToDoList
     * When they select the desired location, Add the list as an element to the left pane and load it
     * TODO initialize the file with json containing the name of the list
     * ---
     */
    @FXML
    public void createList() {
        String toDoListName = newListInputField.getText();

        if (toDoListName.isBlank() || toDoListName.isEmpty()) {
            return;
        }

        ToDoList list = ToDoListManager.createList(toDoListName);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select List Location");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showSaveDialog(newListButton.getScene().getWindow());

        try {
            selectedFile.createNewFile();
        } catch (IOException e) {

        }

        list.path = selectedFile.getPath();

        list.initialize();

        toDoLists.add(list);

        newListInputField.clear();
    }

    /**
     * Loads the ToDoList that was just clicked.
     */
    public void loadList() {

        if (toDoListingBox.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        // Only load the list if the index is different from the currently selected index
        if (toDoListingBox.getSelectionModel().getSelectedIndex() != selectedItemIndex) {
            this.tasks.setAll(toDoLists.get(toDoListingBox.getSelectionModel().getSelectedIndex()).tasks);
        } else {

        }

        System.out.println("Newly selected list " + toDoListingBox.getSelectionModel().getSelectedItem().toString());
    }

    /**
     * Adds a new Task to the currently selected ToDoList.
     *
     * ---
     * Get the currently selected ToDoList
     * If it's null, return and do nothing
     * Otherwise, call addTask on the currently selected ToDoList and set the description of the Task to
     *  the contents of addTaskField
     * ---
     */
    public void addTask() {
        String description = addTaskField.getText();

        if (description.isEmpty() || description.isBlank()) {
            return;
        }

        ToDoList currentList = (ToDoList) toDoListingBox.getSelectionModel().getSelectedItem();

        currentList.addTask(description);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toDoListingBox.setItems(toDoLists);
        toDoListItemsBox.setItems(tasks);
    }
}
