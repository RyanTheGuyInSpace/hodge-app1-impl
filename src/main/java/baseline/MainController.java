/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Ryan Hodge
 */

package baseline;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.security.auth.callback.Callback;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    ObservableList<ToDoList> toDoLists = FXCollections.observableArrayList();

    ObservableList<Task> tasks = FXCollections.observableArrayList();

    @FXML
    public ListView<ToDoList> toDoListingBox;

    @FXML
    public ListView<Task> toDoListItemsBox;

    @FXML
    public Scene scene;

    @FXML
    public Scene editTaskScene;

    @FXML
    public TextField editTaskField;

    @FXML
    public Button editTaskSet;

    @FXML
    public TextField newListInputField;

    @FXML
    public Button newListButton;

    @FXML
    public TextField addTaskField;

    @FXML
    private Button addTaskButton;

    @FXML
    private MenuItem toggleTaskCompleteButton;

    @FXML
    private MenuItem editTaskButton;

    @FXML
    private MenuItem deleteTaskButton;

    @FXML
    private MenuItem setTaskDueDateButton;

    @FXML
    private MenuItem deleteAllTasksButton;

    @FXML
    private MenuItem showCompleteButton;

    @FXML
    private MenuItem showIncompleteButton;

    @FXML
    private MenuItem showAllButton;

    @FXML
    private Button editTaskSaveButton;

    public long selectedItemIndex = -1;

    /**
     * Creates a list using the text inside the adjacent text field.
     *
     * ---
     * Create a new ToDoList and name it whatever is inside newListInputField
     * If newListInputField is blank, do nothing
     * Clear the text inside newListInputField
     * Open up FileChooser so the user can choose where to store their ToDoList
     * When they select the desired location, Add the list as an element to the left pane and load it
     * Save the ToDoList to write it to file
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
                new FileChooser.ExtensionFilter("Json Files", "*.json"));

        File selectedFile = fileChooser.showSaveDialog(newListButton.getScene().getWindow());

        try {
            selectedFile.createNewFile();
        } catch (IOException e) {

        }

        list.path = selectedFile.getPath();

        list.save();

        toDoLists.add(list);

        newListInputField.clear();
    }

    /**
     * Loads the ToDoList that was just clicked and
     * focuses the field to add a task and clears the editTaskField
     *
     * ---
     * Get the currently selected ToDoList
     * Set the list of Tasks in this controller to the
     * ---
     */
    public void loadList() {
        if (toDoListingBox.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        // Only load the list if the index is different from the currently selected index
        if (toDoListingBox.getSelectionModel().getSelectedIndex() != selectedItemIndex) {
            this.tasks.setAll(toDoLists.get(toDoListingBox.getSelectionModel().getSelectedIndex()).tasks);
        }

        addTaskField.requestFocus();
        editTaskField.clear();
    }

    /**
     * Loads only the completed Tasks from the currently selected ToDoList
     *
     * ---
     * Set the currently listed tasks to ONLY the tasks that are completed
     * ---
     */
    public void loadComplete() {
        if (toDoListingBox.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        // Only load the list if the index is different from the currently selected index
        if (toDoListingBox.getSelectionModel().getSelectedIndex() != selectedItemIndex) {
            this.tasks.setAll(toDoLists.get(toDoListingBox.getSelectionModel().getSelectedIndex())
                    .tasks
                    .stream()
                    .filter(a -> a.isComplete()).collect(Collectors.toList()));
        }

        editTaskField.clear();
    }

    /**
     * Loads only the uncompleted Tasks from the currently selected ToDoList
     *
     * ---
     * Set the currently listed tasks to ONLY the tasks that are incomplete
     * ---
     */
    public void loadIncomplete() {
        if (toDoListingBox.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        // Only load the list if the index is different from the currently selected index
        if (toDoListingBox.getSelectionModel().getSelectedIndex() != selectedItemIndex) {
            this.tasks.setAll(toDoLists.get(toDoListingBox.getSelectionModel().getSelectedIndex())
                    .tasks
                    .stream()
                    .filter(a -> !a.isComplete()).collect(Collectors.toList()));
        }

        editTaskField.clear();
    }

    /**
     * Loads the last clicked Task into the editTaskField to allow it to be edited
     *
     * ---
     * Get the currently selected Task
     * Set the editTaskField to the selected Task's description
     * Focus the editTaskField for quick access to editing
     * ---
     */
    public void loadTask() {
        if (toDoListingBox.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        if (toDoListItemsBox.getSelectionModel().getSelectedItem() != null) {
            Task task = toDoListItemsBox.getSelectionModel().getSelectedItem();
            this.editTaskField.setText(task.getDescription());
            this.editTaskField.requestFocus();
        }
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

        ToDoList currentList = toDoListingBox.getSelectionModel().getSelectedItem();

        currentList.addTask(description);

        this.loadList();

        addTaskField.clear();
        addTaskField.requestFocus();
    }

    /**
     * Toggles the currently selected Task to be complete or incomplete
     * based on the current state of its isComplete field
     *
     * ---
     * Get the currently selected Task
     * If the task is complete, set it to be incomplete
     * If the task is incomplete, set it to be complete
     * Reload the list
     * ---
     */
    public void toggleTaskComplete() {
        if (toDoListItemsBox.getSelectionModel().getSelectedItem() != null) {
            Task task = toDoListItemsBox.getSelectionModel().getSelectedItem();

            // Toggle the completion status
            task.setCompletion(!task.isComplete());

            // Save and reload
            toDoListingBox.getSelectionModel().getSelectedItem().save();
            loadList();
        }
    }

    /**
     * Edits currently selected Task
     *
     * ---
     * Get the currently selected ToDoList
     * Get the currently selected Task from that ToDoList
     * Get the text from the editTaskField
     * Set the description of the Task to the text that was inside the editTaskField
     * Save the ToDoList
     * Reload the ToDoList
     * Clear the editTaskField
     * ---
     */
    public void editTask() {
        if (toDoListItemsBox.getSelectionModel().getSelectedItem() != null) {
            ToDoList list = toDoListingBox.getSelectionModel().getSelectedItem();

            Task task = toDoListItemsBox.getSelectionModel().getSelectedItem();

            String description = editTaskField.getText();

            if (description.isBlank() || description.isEmpty()) {
                return;
            }

            task.setDescription(description);
            list.save();
            loadList();
            editTaskField.clear();
        }
    }

    /**
     * Deletes the currently selected Task.
     *
     * ---
     * Get the currently selected ToDoList
     * Get the currently selected Task
     * Remove the currently selected Task from the currently selected ToDoList
     * Reload the ToDoList
     * ---
     */
    public void deleteTask() {
        if (toDoListItemsBox.getSelectionModel().getSelectedItem() != null) {

            ToDoList list = toDoListingBox.getSelectionModel().getSelectedItem();

            list.removeTask(toDoListItemsBox.getSelectionModel().getSelectedItem());

            loadList();
        }
    }

    /**
     * Deletes all Tasks from the currently selected ToDoList.
     *
     * ---
     * Get the currently selected ToDoList
     * If the Tasks list isn't empty, clear it
     * Reload the ToDoList
     * ---
     */
    public void deleteAllTasks() {
        if (toDoListingBox.getSelectionModel().getSelectedItem() != null) {

            ToDoList list = toDoListingBox.getSelectionModel().getSelectedItem();

            if (!list.tasks.isEmpty()) {
                list.clearAllTasks();
                loadList();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toDoListingBox.setItems(toDoLists);
        toDoListItemsBox.setItems(tasks);

        addTaskField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                addTask();
            }
        });

        editTaskField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                editTask();
            }
        });

        newListInputField.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                createList();
            }
        });
    }
}
