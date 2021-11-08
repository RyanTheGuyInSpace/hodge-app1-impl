package baseline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void setCompletion() {
        Task task = new Task();

        task.setDescription("Hello there");

        task.setCompletion(true);

        assertEquals(true, task.isComplete());

    }

    @Test
    void isComplete() {
        Task task = new Task();

        task.setDescription("Hello there");

        task.setCompletion(true);

        assertEquals(true, task.isComplete());
    }

    @Test
    void setDescription() {
        Task task = new Task();

        String description = "Hello there";

        task.setDescription(description);

        assertEquals(description, task.getDescription());
    }

    @Test
    void getDescription() {
        Task task = new Task();

        String description = "Hello there";

        task.setDescription(description);

        assertEquals(description, task.getDescription());
    }

    @Test
    void testToString() {
        Task task = new Task();

        String description = "Hello there";

        task.setDescription(description);

        assertTrue(task.toString().contains(task.getDescription()));
    }
}