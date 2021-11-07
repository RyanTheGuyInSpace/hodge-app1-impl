/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Ryan Hodge
 */

package baseline;

import com.google.gson.Gson;

public class ToDoListManager {

    /**
     * Used to serialize and deserialize the Json of ToDoLists and Tasks
     */
    public static final Gson serializer = new Gson();

    /**
     * Creates a new ToDoList.
     * @param title The title to give to the ToDoList.
     * @return To newly created ToDoList.
     */
    public static ToDoList createList(String title) {

        ToDoList list = new ToDoList();

        list.title = title;

        return list;
    }
}
