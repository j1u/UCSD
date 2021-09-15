/*
  Name: James Lu
 */

/**
 * text message class that extends message class, supports text messages
 * @author James Lu
 * @since  4-15-21
 */
public class TextMessage extends Message {

    /* Error message to use in OperationDeniedException */
    private static final String EXCEED_MAX_LENGTH =
            "Your input exceeded the maximum length limit.";

    /**
     * text message constructor that inits sender, contents
     * @param sender user representing the sender of this message
     * @param text content of the message
     * @throws OperationDeniedException if message contents > 500 characters
     * @throws IllegalArgumentException if sender or text == null
     */
    public TextMessage(User sender, String text)
            throws OperationDeniedException {
        super(sender);
        if (text == null){
            throw new IllegalArgumentException();
        }
        if (text.length() > 500){
            throw new OperationDeniedException(EXCEED_MAX_LENGTH);
        }
        super.contents = text;
    }

    /**
     * returns the information regarding this message
     * @return contents of this message
     */
    public String getContents() {
        return this.getSender().displayName() + " [" + this.getDate().toString() + "]: " +
                this.contents;
    }

}
