/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Ryan Hodge
 */

package baseline;

import java.util.Date;

public class Task {
    String description;

    /**
     * Formatted as YYYY-MM-DD
     */
    Date dueDate;
    boolean isComplete;

    /**
     * Deletes the Task.
     */
    public void delete() {

    }

    public String toString() {
        return this.description;
    }
}
