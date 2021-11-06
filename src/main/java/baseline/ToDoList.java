/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Ryan Hodge
 */

package baseline;

import com.sun.tools.javac.Main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class ToDoList {
    String path;
    String title;
    LinkedList<Task> tasks;

    /**
     * Adds a Task to this ToDoList
     * @param description The description of the Task to add.
     * @return The newly created Task.
     *
     * Create a Task object using the provided description
     *
     */
    public Task addTask(String description) {

        Task task = new Task();

        task.description = description;
        task.dueDate = null;
        task.isComplete = false;

        try {
            ToDoListManager.serializer.toJson(task, new FileWriter(this.path));
        } catch (IOException e) {

        }

        return task;
    }

    /**
     * Renames this ToDoList.
     * @param newName The new name to set for this ToDoList.
     */
    public void rename(String newName) {

    }

    /**
     * Deletes this ToDoList
     */
    public void delete() {

    }

    /**
     * Create the Json file for this list and save it
     */
    public void save() {

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
    public void initialize() {

        try {
            ToDoListManager.serializer.toJson(this, new FileWriter(this.path));
        } catch (IOException e) {
            System.out.println("Error initializing list");
        }
    }

    public String toString() {
        return this.title;
    }
}
