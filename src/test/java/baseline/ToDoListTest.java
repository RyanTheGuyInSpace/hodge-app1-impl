package baseline;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {

    @Test
    void addTask() {
    }

    @Test
    void rename() {
    }

    @Test
    void delete() {
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }

    @Test
    void initialize() {
        ToDoList list = new ToDoList();

        list.title = "Fresh ToDoList";
        list.path = "C:\\Users\\ryo_h\\Documents\\TestFiles\\testList.json";

        list.initialize();

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(list.path));

            ToDoList newList = ToDoListManager.serializer.fromJson(br, ToDoList.class);

            assertEquals(list.title, newList.title);
        } catch (IOException e) {

        }


    }
}