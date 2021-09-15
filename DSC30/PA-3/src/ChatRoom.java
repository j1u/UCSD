/*
  Name: James Lu
 */

import java.util.ArrayList;

/**
 * Chatroom class that implements message exchange interface
 * @author James Lu
 * @since  4-15-21
 */
public class ChatRoom implements MessageExchange {

    /* instance variables */
    private ArrayList<User> users;
    private ArrayList<Message> log;

    /**
     * Chatroom constructor, inits user and log arraylists
     */
    public ChatRoom() {
        this.users = new ArrayList<>();
        this.log = new ArrayList<>();
    }

    /**
     * Returns log arraylist
     * @param requester The user that requests this operation.
     * @return arraylist containing logs
     */
    public ArrayList<Message> getLog(User requester) {
        return this.log;
    }

    /**
     * Adds user to chatroom
     * @param u User to add.
     * @return Whether or not the operation was successful
     */
    public boolean addUser(User u) {
        if (this.users.contains(u)){
            return false;
        }else {
            this.users.add(u);
            u.rooms.add(this);
            return true;
        }
    }

    /**
     * Removes user from chatroom
     * @param requester The user that requests this operation.
     * @param u User to remove.
     * @return Whether or not the operation was successful
     */
    public boolean removeUser(User requester, User u) {
        if (this.users.remove(u)) {
            u.rooms.remove(this);
            return true;
        }
        return false;
    }

    /**
     * Get user arraylist
     * @return arraylist containing all users in the chatroom
     */
    public ArrayList<User> getUsers() {
        return this.users;
    }

    /**
     * Records message to log
     * @param m Message to add.
     * @return true no matter what
     */
    public boolean recordMessage(Message m) {
        this.log.add(m);
        return true;
    }

}
