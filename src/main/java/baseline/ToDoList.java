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
     * Create a new Task
     * Add the task to the ToDoList
     * Re-initialize the ToDoList
     */
    public void addTask(String description) {
        Task task = new Task();

        task.description = description;
        task.dueDate = null;
        task.isComplete = false;

        this.tasks.add(task);

        this.save();
    }

    /**
     * Renames this ToDoList.
     * @param newName The new name to set for this ToDoList.
     */
    public void rename(String newName) {
        this.title = newName;
    }

    /**
     * Deletes this ToDoList
     */
    public void delete() {

    }

    /**
     * Reads in all the ToDoList's Tasks
     *
     * Use Gson to open the ToDoList's file
     * Read the ToDoList's file into a list of Tasks
     * Iterate through the tasks and display them on the GUI, remembering to give each task an incremental id
     */
    public void load() {

        // Read the json file and get all the Tasks and store them inside the tasks list
        try {
            ToDoListManager.serializer.fromJson(new FileReader(this.path), tasks.getClass());
        } catch (IOException e) {

        }
    }

    /**
     * Initializes the ToDoList's file with json containing its basic properties.
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
     * Deletes all tasks inside the ToDoList.
     */
    public void clearTasks() {
        this.tasks.clear();
    }

    /**
     * Gets the name of this ToDoList.
     * @return The name of this ToDoList.
     */
    public String toString() {
        return this.title;
    }
}
