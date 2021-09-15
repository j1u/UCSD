/*
  Name: James Lu
 */

import java.util.ArrayList;
import java.util.List;

/**
 * ModeratedRoom that implements message exchange interface, supports moderating
 * ie. banning unbanning etc.
 * @author James Lu
 * @since  4-15-21
 */
public class ModeratedRoom implements MessageExchange {
    /* instance variables */
    private ArrayList<User> users, banned;
    private ArrayList<Message> log;
    private User moderator;
    private int numVisibleLog;

    /**
     * Moderatedroom constructor that inits users, banned, and log arraylists as well as moderator
     * and number of visible logs
     * @param moderator user with moderator privileges
     */
    public ModeratedRoom(PremiumUser moderator) {
        this.users = new ArrayList<>();
        this.users.add(moderator);
        this.banned = new ArrayList<>();
        this.log = new ArrayList<>();
        this.moderator = moderator;
        this.numVisibleLog = Integer.MAX_VALUE;
    }

    /**
     * Gets log of moderated room depending on user
     * @param requester The user that requests this operation.
     * @return returns the log depending on user privileges
     */
    public ArrayList<Message> getLog(User requester) {
        if (requester == moderator){
            return this.log;
        }else {
            //if log size is less than permitted numvisible then return whole log
            //else return last numvisible messages from the log
            if (this.log.size() < this.numVisibleLog){
                return this.log;
            }else {
                return (ArrayList<Message>) this.log.subList(this.log.size() -
                        numVisibleLog, this.log.size());
            }
        }
    }

    /**
     * returns banned arraylist
     * @return arraylist containing all users that are banned form this chatroom
     */
    public ArrayList<User> getBanned() {
        return this.banned;
    }

    /**
     * Adds user to this chatroom
     * @param u User to add.
     * @return Whether or not the operation was successful
     */
    public boolean addUser(User u) {
        if (this.users.contains(u) || this.banned.contains(u)){
            return false;
        }else {
            this.users.add(u);
            u.rooms.add(this);
            return true;
        }
    }

    /**
     * Removes a user from this chatroom
     * @param requester The user that requests this operation.
     * @param u User to remove.
     * @return Whether or not the operation was successful
     */
    public boolean removeUser(User requester, User u) {
        if (requester == this.moderator && u != moderator) {
            if (this.users.remove(u)) {
                u.rooms.remove(this);
                return true;
            }
        }
        return false;
    }

    /**
     * returns arraylist of users
     * @return arraylist containing all users in this room
     */
    public ArrayList<User> getUsers() {
        return this.users;
    }

    /**
     * records a message to the log
     * @param m Message to add.
     * @return whether or not the add was successful
     */
    public boolean recordMessage(Message m) {
        return this.log.add(m);
    }

    /**
     * Bans user from this room
     * @param requester user requesting the operation
     * @param u the user to be banned
     * @return whether or not the operation was successful
     */
    public boolean banUser(User requester, User u) {
        if (requester == this.moderator){
            if (this.users.remove(u)) {
                this.banned.add(u);
                u.quitRoom(this);
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * unbans a user from this chatroom
     * @param requester user requesting to unban
     * @param u user to be unbanned
     * @return whether or not the operation was successful
     */
    public boolean unbanUser(User requester, User u) {
        if (requester == this.moderator){
            this.banned.remove(u);
            return true;
        }else {
            return false;
        }
    }

    /**
     * sets the number of visible log for normal users
     * @param requester user requesting to change the variable
     * @param newNum new number of logs to be visible
     * @return whether or not the operation is successful
     */
    public boolean setNumVisibleLog(User requester, int newNum) {
        if (requester == this.moderator){
            this.numVisibleLog = newNum;
            return true;
        }else {
            return false;
        }
    }

    /**
     * gets the number of visible logs for normal users
     * @return number of visible logs
     */
    public int getNumVisibleLog(){
        return this.numVisibleLog;
    }
}
