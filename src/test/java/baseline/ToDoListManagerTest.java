package baseline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListManagerTest {

    @Test
    void createList() {
        ToDoList list = ToDoListManager.createList("New List");

        assertEquals("New List", list.title);
    }
}