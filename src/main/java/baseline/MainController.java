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
    private Button addListButton;

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
        System.out.println(addListButton.getScene());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scene = newListInputField.getScene();

        newListInputField.setText("Something");
    }
}
