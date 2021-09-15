/*
  Name: James Lu
 */

import java.util.ArrayList;

/**
 * standard user that extends user class, supports chatroom methods
 * @author James Lu
 * @since  4-15-21
 */
public class StandardUser extends User {

    /* Message to append when fetching non-text message */
    private static final String FETCH_DENIED_MSG =
            "This message cannot be fetched because you are not a premium user.";

    /**
     * standard user constructor that inits username, bio
     * @param username
     * @param bio
     */
    public StandardUser(String username, String bio) {
        super(username, bio);
    }

    /**
     * fetches messages from a room
     * @param me the room to get messages from
     * @return returns a string containing all the message logs
     * @throws IllegalArgumentException if me == null or if user is already in the room
     */
    public String fetchMessage(MessageExchange me) {
        final int USER_FETCH_LIMIT = 100;
        if (me == null || !this.rooms.contains(me)){
            throw new IllegalArgumentException();
        }
        ArrayList<Message> log = me.getLog(this);
        StringBuilder str = new StringBuilder();
        //if log size > fetch limit return last FETCH_LIMIT messages
        //else return the whole log
        if (log.size() > USER_FETCH_LIMIT) {
            for (int i = log.size() - (USER_FETCH_LIMIT); i < log.size(); i++) {
                Message m = log.get(i);
                if (m instanceof TextMessage) {
                    str.append(m.getContents());
                } else {
                    str.append(FETCH_DENIED_MSG);
                }
                str.append("\n");
            }
        }else {
            for (int i = 0; i < log.size(); i++) {
                Message m = log.get(i);
                if (m instanceof TextMessage) {
                    str.append(m.getContents());
                } else {
                    str.append(FETCH_DENIED_MSG);
                }
                str.append("\n");
            }
        }
        return str.toString();
    }

    /**
     * returns displayname of user
     * @return displayname of user
     */
    public String displayName() {
        return this.username;
    }
}
