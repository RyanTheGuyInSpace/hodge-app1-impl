/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Ryan Hodge
 */

package baseline;

import java.util.Date;

public class Task {
    private String description;

    /**
     * Formatted as YYYY-MM-DD
     */
    Date dueDate;
    private boolean isComplete;

    /**
     * Sets whether this Task is complete
     * @param complete The new completion status of this Task
     */
    public void setCompletion(boolean complete) {
        this.isComplete = complete;
    }

    /**
     * Gets whether this Task is complete
     * @return Whether this task is complete
     */
    public boolean isComplete() {
        return this.isComplete;
    }

    /**
     * Sets the description of this Task
     * @param description The description to set for this Task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets this Task's description.
     * @return This Task's description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets a String with the Task's completion status and description
     * @return A String with the Task's completion status and description
     */
    public String toString() {
        if (this.isComplete()) {
            return String.format("(Complete) --- %s", this.description);
        } else {
            return String.format("(Incomplete) --- %s", this.description);
        }
    }
}
