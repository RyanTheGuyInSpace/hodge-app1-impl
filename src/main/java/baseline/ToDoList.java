/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Ryan Hodge
 */

package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ToDoList {
    String path;
    String title;
    ObservableList<Task> tasks = FXCollections.observableArrayList();

    /**
     * Adds a Task to this ToDoList
     * @param description The description of the Task to add.
     *
     * Create a new Task using the provided description
     * Add the task to this ToDoList
     * Save the ToDoList
     */
    public void addTask(String description) {
        Task task = new Task();

        task.setDescription(description);
        task.dueDate = null;
        task.setCompletion(false);

        this.tasks.add(task);

        this.save();
    }

    /**
     * Removes the provided Task from this ToDoList
     * @param task The Task to remove from this ToDoList
     */
    public void removeTask(Task task) {

        try {
            this.tasks.remove(task);
            this.save();
        } catch (Exception e) {

        }

    }

    /**
     * Deletes all Tasks from this ToDoList
     *
     * ---
     * Remove all Tasks from this ToDoList's list of Tasks
     * Save the ToDoList
     * ---
     */
    public void clearAllTasks() {
        this.tasks.clear();
        this.save();
    }

    /**
     * Initializes the ToDoList's file with json containing its basic properties.
     *
     * ---
     * Create a new FileWriter
     * Use Gson to convert this ToDoList to json format
     * Use the FileWriter to write the json string to the file
     * Flush the file
     * Close the file
     * ---
     */
    public void save() {

        try {
            FileWriter writer = new FileWriter(this.path);

            writer.write(ToDoListManager.serializer.toJson(this));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error initializing list");
        }
    }

    /**
     * Gets the name of this ToDoList.
     * @return The name of this ToDoList.
     */
    public String toString() {
        return this.title;
    }
}
