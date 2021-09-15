/*
  Name: James Lu
 */

import java.util.ArrayList;

/**
 * Premiumuser that extends user class, supports moderated room methods
 * @author James Lu
 * @since  4-15-21
 */
public class PremiumUser extends User {

    /* instance variable */
    private String customTitle;

    /**
     * premiumuser constructor that inits username, bio, and titles
     * @param username username of user
     * @param bio bio of user
     */
    public PremiumUser(String username, String bio) {
        super(username, bio);
        this.customTitle = "Premium";
    }

    /**
     * fetches messages from a room
     * @param me the room to get messages from
     * @return returns a string containing all the message logs
     * @throws IllegalArgumentException if me == null or if user is already in the room
     */
    public String fetchMessage(MessageExchange me) {
        if (me == null || !this.rooms.contains(me)){
            throw new IllegalArgumentException();
        }
        ArrayList<Message> log = me.getLog(this);
        StringBuilder str = new StringBuilder();
        //appends messages to string builder
        for (int i = 0; i < log.size(); i++) {
            Message m = log.get(i);
            str.append(m.getContents());
            str.append("\n");
        }
        return str.toString();
    }

    /**
     * returns the displayname of the user
     * @return displayname of user
     */
    public String displayName() {
        return "<" + this.customTitle + "> " + this.username;
    }

    /**
     * sets customtitle to a new title
     * @param newTitle new title to be set
     */
    public void setCustomTitle(String newTitle) {
        this.customTitle = newTitle;
    }

    /**
     * creates a moderated room with a list of users
     * @param users list of users to be added to the room
     * @return new moderated room
     */
    public MessageExchange createModeratedRoom(ArrayList<User> users) {
        if (users == null){
            throw new IllegalArgumentException();
        }
        MessageExchange mr = new ModeratedRoom(this);

        //for each user in the list attempt to join them to the room
        for (User u : users) {
            try {
                u.joinRoom(mr);
            }catch (OperationDeniedException e){
                System.out.println(e.getMessage());
            }
        }

        return mr;
    }

    /**
     * bans a user from the room
     * @param room room from which the user is banned
     * @param u user to be banned
     * @return whether or not the operation was successful
     */
    public boolean banUser(ModeratedRoom room, User u) {
        return room.banUser(this, u);
    }

    /**
     * unbans a user from the room
     * @param room room from which the user is unbanned
     * @param u user to be unbanned
     * @return whether or not the operation was successful
     */
    public boolean unbanUser(ModeratedRoom room, User u) {
        return room.unbanUser(this, u);
    }

    /**
     * sets the number of visible log for a moderated room
     * @param room moderated room to be adjusted
     * @param newNum new number of visible logs
     * @return whether or not the operation was successful
     */
    public boolean setNumVisibleLog(ModeratedRoom room, int newNum) {
        return room.setNumVisibleLog(this, newNum);
    }
}
