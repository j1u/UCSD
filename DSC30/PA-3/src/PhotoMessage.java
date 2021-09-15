/*
  Name: James Lu
 */

import java.util.Locale;

/**
 * Photomessage that extends message class, supports photo messages
 * @author James Lu
 * @since  4-15-21
 */
public class PhotoMessage extends Message {

    /* Error message to use in OperationDeniedException */
    private static final String INVALID_INPUT =
            "The source path given cannot be parsed as photo.";

    private static final String[] VALID_EXTNS = new String[] {
            "jpg", "jpeg", "gif", "png", "tif", "tiff", "raw"
    };


    /* instance variable */
    private String extension;

    /**
     * constructor for photomessage, inits sender, contents, date, and checks if the input is valid
     * @param sender user representing the sender of this message
     * @param photoSource source of photo
     * @throws OperationDeniedException if user is not a premium user
     * @throws IllegalArgumentException if photosource is null
     */
    public PhotoMessage(User sender, String photoSource)
                        throws OperationDeniedException {
        super(sender);
        if (photoSource == null){
            throw new IllegalArgumentException();
        }
        if (!(sender instanceof PremiumUser)){
            throw new OperationDeniedException(DENIED_USER_GROUP);
        }
        boolean valid_extsn = false;
        String extsn = photoSource.substring(photoSource.lastIndexOf('.')).
                split("[.]")[1].toLowerCase(Locale.ROOT);
        //checks if extension is valid
        for (String i : VALID_EXTNS){
            if (i.equals(extsn)){
                valid_extsn = true;
                break;
            }
        }
        if (!valid_extsn){
            throw new OperationDeniedException(INVALID_INPUT);
        }
        this.contents = photoSource;
        this.extension = extsn;
    }

    /**
     * Gets contents of message
     * @return string containing the information of the message
     */
    public String getContents() {
        return this.getSender().displayName() + " [" + this.getDate().toString() +
                "]: Picture at " + this.contents;
    }

    /**
     * gets the extension of the photo
     * @return string representing the extension of the photo
     */
    public String getExtension() {
        return this.extension;
    }

}
