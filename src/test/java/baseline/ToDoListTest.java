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
    void removeTask() {
        ToDoList list = new ToDoList();

        list.title = "Fresh ToDoList";
        list.path = "C:\\Users\\ryo_h\\Documents\\TestFiles\\testList3.json";

        list.save();

        list.addTask("Something something");

        list.save();

        int num1 = list.tasks.size();

        Task task = list.tasks.get(0);

        list.removeTask(task);

        int num2 = list.tasks.size();

        assertNotEquals(num1, num2);
    }

    @Test
    void clearAllTasks() {
        ToDoList list = new ToDoList();

        list.title = "Fresh ToDoList";
        list.path = "C:\\Users\\ryo_h\\Documents\\TestFiles\\testList5.json";

        list.save();

        list.addTask("Something something");

        list.save();

        int num1 = list.tasks.size();

        Task task = list.tasks.get(0);

        list.clearAllTasks();

        int num2 = list.tasks.size();

        assertNotEquals(num1, num2);
    }

    @Test
    void save() {
        ToDoList list = new ToDoList();

        list.title = "Fresh ToDoList";
        list.path = "C:\\Users\\ryo_h\\Documents\\TestFiles\\testList4.json";

        list.addTask("Test item");

        list.save();

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(list.path));

            assertNotNull(br.readLine());
        } catch (IOException e) {

        }
    }

    @Test
    void ToString() {
        ToDoList list = new ToDoList();

        list.title = "Fresh ToDoList";
        list.path = "C:\\Users\\ryo_h\\Documents\\TestFiles\\testList6.json";

        list.addTask("Test item");

        list.save();

        assertEquals(list.title, list.toString());
    }
}