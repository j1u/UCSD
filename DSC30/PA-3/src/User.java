/*
  Name: James Lu
 */

import java.util.ArrayList;

/**
 * abstract class that represents a generic user
 * @author James Lu
 * @since  4-15-21
 */
public abstract class User {

    /* Error message to use in OperationDeniedException */
    protected static final String JOIN_ROOM_FAILED =
            "Failed to join the chat room.";
    protected static final String INVALID_MSG_TYPE =
            "Cannot send this type of message to the specified room.";

    /* instance variables */
    protected String username;
    protected String bio;
    protected ArrayList<MessageExchange> rooms;

    /**
     * user constructor that inits username, bio, and rooms
     * @param username username of the user
     * @param bio bio of the user
     * @throws IllegalArgumentException if username or bio == null
     */
    public User(String username, String bio) {
        if (username == null || bio == null){
            throw new IllegalArgumentException();
        }
        this.username = username;
        this.bio = bio;
        this.rooms = new ArrayList<>();
    }

    /**
     * sets bio of user
     * @param newBio new bio to be set
     * @throws IllegalArgumentException if newbio == null
     */
    public void setBio(String newBio) {
        if (newBio == null){
            throw new IllegalArgumentException();
        }
        this.bio = newBio;
    }

    /**
     * displays bio of the user
     * @return bio of the user
     */
    public String displayBio() {
        return this.bio;
    }

    /**
     * joins a room for the user
     * @param me room to be joined
     * @throws OperationDeniedException if user already in room
     * @throws IllegalArgumentException if room is null
     */
    public void joinRoom(MessageExchange me) throws OperationDeniedException {
        if (me == null){
            throw new IllegalArgumentException();
        }
        if (rooms.contains(me) || !me.addUser(this)){
            throw new OperationDeniedException(JOIN_ROOM_FAILED);
        }

    }

    /**
     * quits a room for the user
     * @param me room to be left by the user
     * @throws IllegalArgumentException if room is null
     */
    public void quitRoom(MessageExchange me) {
        if (me == null){
            throw new IllegalArgumentException();
        }
        me.removeUser(this, this);
    }

    /**
     * creates a chatroom for the user
     * @param users users to be added to the chatroom
     * @return new chatroom containing all the users
     * @throws IllegalArgumentException if users == null
     */
    public MessageExchange createChatRoom(ArrayList<User> users) {
        if (users == null){
            throw new IllegalArgumentException();
        }
        ChatRoom cr = new ChatRoom();

        //add this user to chatroom
        try {
            this.joinRoom(cr);
        } catch (OperationDeniedException e) {
            System.out.println(e.getMessage());
        }

        //add each user to the chatroom
        for (User u : users) {
            try {
                u.joinRoom(cr);
            }catch (OperationDeniedException e){
                System.out.println(e.getMessage());
            }
        }

        return cr;
    }

    /**
     * sends message to a chatroom
     * @param me room to send message in
     * @param msgType type of message
     * @param contents contents of message
     * @throws IllegalArgumentException if room, msgtype, or contents are null
     */
    public void sendMessage(MessageExchange me, MessageType msgType, String contents) {
        if (me == null || msgType == null || contents == null){
            throw new IllegalArgumentException();
        }
        if (!this.rooms.contains(me)){
            throw new IllegalArgumentException();
        }
        //if msgtype is photo or text then try to send message else
        //throw operationdeniedexception
        if (msgType == MessageType.PHOTO){
            try {
                Message newMsg = new PhotoMessage(this, contents);
                me.recordMessage(newMsg);
            } catch (OperationDeniedException e) {
                System.out.println(e.getMessage());
            }
        }else if (msgType == MessageType.TEXT){
            try {
                Message newMsg = new TextMessage(this, contents);
                me.recordMessage(newMsg);
            } catch (OperationDeniedException e) {
                System.out.println(e.getMessage());
            }
        }else {
            try {
                throw new OperationDeniedException();
            } catch (OperationDeniedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * fetches a message from a room
     * @param me room to get messages from
     * @return messages from the room
     */
    public abstract String fetchMessage(MessageExchange me);

    /**
     * returns the displayname of the user
     * @return displayname of user
     */
    public abstract String displayName();
}
