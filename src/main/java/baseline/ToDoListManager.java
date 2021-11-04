package baseline;

import com.google.gson.Gson;

public class ToDoListManager {

    /**
     * Used to serialize and deserialize the Json of ToDoLists and Tasks
     */
    public static Gson serializer = new Gson();

    /**
     * Creates a new ToDoList.
     * @param title The title to give to the ToDoList.
     * @return To newly created ToDoList.
     */
    public static ToDoList createList(String title) {

        ToDoList list = new ToDoList();

        list.title = title;

        return new ToDoList();
    }

    /**
     * Generates a unique id to use for a new ToDoList.
     * @return An integer value that is current unused for a ToDoList.
     *
     * Get ALL ToDoLists and put their ids into an array
     * Sort the array
     * The last value +1 is the new ID
     * Check the list one more time to make sure that the ID isn't inside the list
     * If the id is in the array, add 1 to the ID and search again
     * When the id isn't found inside the array return it
     */
    public static int generateID() {
        return -1;
    }
}
