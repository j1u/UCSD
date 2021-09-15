/*
  Name: James Lu
 */

import java.time.LocalDate;

/**
 * Abstract class message representing generic messages
 * @author James Lu
 * @since  4-15-21
 */
public abstract class Message {

    /* Error message to use in OperationDeniedException */
    protected static final String DENIED_USER_GROUP =
            "This operation is disabled in your user group.";

    /* instance variables */
    private LocalDate date;
    private User sender;
    protected String contents;

    /**
     * Message constructor that inits date and sender
     * @param sender sender of the message
     */
    public Message(User sender) {
        if (sender == null){
            throw new IllegalArgumentException();
        }
        this.date = LocalDate.now();
        this.sender = sender;
    }

    /**
     * returns date of message
     * @return date object at time of message
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * returns sender of message
     * @return user object of the sender
     */
    public User getSender() {
        return this.sender;
    }

    /**
     * get contents of message
     * @return string representing info about message
     */
    public abstract String getContents();

}
