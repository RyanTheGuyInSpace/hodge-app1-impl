package baseline;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {

    @Test
    void addTask() {
        ToDoList list = new ToDoList();

        list.title = "New List";
        list.path = "C:\\Users\\ryo_h\\Documents\\TestFiles\\testList2.json";

        list.save();

        int num = list.tasks.size();

        list.addTask("Do stuff");

        int num2 = list.tasks.size();

        assertNotEquals(num, num2);
    }

    @Test
    void rename() {
        ToDoList list = new ToDoList();

        list.title = "List One";

        String name1 = list.title;

        list.rename("List Two");

        String name2 = list.title;

        assertNotEquals(name1, name2);
    }

    @Test
    void delete() {
    }

    @Test
    void load() {
    }

    @Test
    void save() {
        ToDoList list = new ToDoList();

        list.title = "Fresh ToDoList";
        list.path = "C:\\Users\\ryo_h\\Documents\\TestFiles\\testList.json";

        list.save();

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(list.path));

            ToDoList newList = ToDoListManager.serializer.fromJson(br, ToDoList.class);

            assertEquals(list.title, newList.title);
        } catch (IOException e) {

        }
    }
}