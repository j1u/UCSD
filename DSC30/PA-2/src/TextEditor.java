/*
    Name: James Lu
 */

/**
 * Text editor that supports insertion, deletion, case swapping, undo, and redo operations
 * @author James Lu
 * @since  9 April 2021
 */
public class TextEditor {
    /* instance variables */
    private String text;
    private IntStack undo;
    private StringStack deletedText;
    private IntStack redo;
    private StringStack insertedText;
    private final int caseSwapID = 0;
    private final int insertID = 1;
    private final int deleteID = 2;

    /**
     * Empty constructor that initializes text to an empty string and initializes instance
     * variables to new stacks
     */
    public TextEditor(){
        this.text = "";
        this.undo = new IntStack();
        this.deletedText = new StringStack();
        this.redo = new IntStack();
        this.insertedText = new StringStack();
    }

    /**
     * Constructor that initializes text to the specified string and initializes instance
     * variables to new stacks
     * @param txt
     */
    public TextEditor(String txt){
        this.text = txt;
        this.undo = new IntStack();
        this.deletedText = new StringStack();
        this.redo = new IntStack();
        this.insertedText = new StringStack();
    }

    /**
     * Returns the current text in the text editor
     * @return all text in the editor
     */
    public String getText(){
        return this.text;
    }

    /**
     * Returns the length of String in the text editor
     * @return
     */
    public int length(){
        return this.text.length();
    }

    /**
     * Swaps uppercase letters with lowercase letters and vice versa
     * @param input String to be manipulated
     * @return New string with cases swapped
     */
    public static String swapCase(String input) {
        //Declare upper / lower ascii bounds and conversion constants
        final int LOWERCASELOWERBOUND = 96;
        final int LOWERCASEUPPERBOUND = 123;
        final int UPPERCASELOWERBOUND = 64;
        final int UPPERCASEUPPERBOUND = 91;
        final int CONVERSION = 32;

        //String builder to create new string
        StringBuilder newString = new StringBuilder();

        //loop through input string
        for (int i = 0; i < input.length(); i++) {
            //ascii number of the character
            int ascii = (int) input.charAt(i);
            //determine if the character is a uppercase or lowercase letter and converts it then
            //appends to string builder
            if (ascii > LOWERCASELOWERBOUND && ascii < LOWERCASEUPPERBOUND) {
                newString.append((char) (ascii - CONVERSION));
            } else if (ascii > UPPERCASELOWERBOUND && ascii < UPPERCASEUPPERBOUND) {
                newString.append((char) (ascii + CONVERSION));
            } else {
                newString.append(input.charAt(i));
            }
        }
        return newString.toString();
    }

    /**
     * Swaps cases of the text in the specified range
     * @param i the beginning index, inclusive
     * @param j the ending index, exclusive
     *
     * @throws IllegalArgumentException if i < 0, i > j, or if j > length of text
     */
    public void caseConvert(int i, int j){
        if (i > j || i < 0 || j >= this.length()){
            throw new IllegalArgumentException();
        }
        //use helper method swap case
        String tmp = swapCase(this.text.substring(i, j));
        int identifier = 0;
        //push undo info into undo stack
        this.undo.multiPush(new int[]{i, j, identifier});
        //clear redo stack
        this.redo.clear();
        //update text
        this.text = this.text.substring(0, i) + tmp + this.text.substring(j);
    }

    /**
     * Inserts text at the specified index
     * @param i beginning index, inclusive
     * @param input input text to be inserted
     *
     * @throws IllegalArgumentException if i < 0 or if i > length of text
     * @throws NullPointerException if input == null
     */
    public void insert(int i, String input){
        if (i < 0 || i >= this.length()){
            throw new IllegalArgumentException();
        }
        if (input == null){
            throw new NullPointerException();
        }
        int identifier = 1;
        //push undo info to undo stack
        this.undo.multiPush(new int[]{i, i + input.length(), identifier});
        //clear redo stack
        this.redo.clear();
        //update text
        this.text = this.text.substring(0, i) + input + this.text.substring(i);
    }

    /**
     * Deletes a region of the text
     * @param i beginning index, inclusive
     * @param j ending index, exclusive
     *
     * @throws IllegalArgumentException if i < 0, i > j, or if j > length of text
     */
    public void delete(int i, int j){
        if (i >= j || i < 0 || j >= this.length()){
            throw new IllegalArgumentException();
        }
        int identifier = 2;
        //push undo info to undo stack
        this.undo.multiPush(new int[]{i, j, identifier});
        //push deleted text to deletetext stack
        this.deletedText.push(this.text.substring(i, j));
        //clear redo stack
        this.redo.clear();
        //update text
        this.text = this.text.substring(0, i) + this.text.substring(j);
    }

    /**
     * Undoes the last operation, returns true if successful, false if not
     * @return Whether or not the undo was successful
     */
    public boolean undo(){
        try {
            //get id from undo stack, declare i, j
            int id = this.undo.pop();
            int i;
            int j;

            //for each operation, init i, j, push info to redo stack, update text
            if (id == caseSwapID){
                j = this.undo.pop();
                i = this.undo.pop();
                this.redo.multiPush(new int[]{i, j, id});
                this.text = this.text.substring(0, i) + swapCase(this.text.substring(i, j)) +
                        this.text.substring(j);
            }else if (id == insertID){
                j = this.undo.pop();
                i = this.undo.pop();
                this.redo.multiPush(new int[]{i, j, id});
                //update inserted text stack
                this.insertedText.push(this.text.substring(i, j));
                this.text = this.text.substring(0, i) + this.text.substring(j);
            }else if (id == deleteID){
                j = this.undo.pop();
                i = this.undo.pop();
                this.redo.multiPush(new int[]{i, j, id});
                //get deleted string from deletedtext stack
                String dltd = this.deletedText.pop();
                this.text = this.text.substring(0, i) + dltd + this.text.substring(i);
            }
        }catch (Exception EmptyStackException){
            return false;
        }
        return true;
    }

    /**
     * Redoes the last operation, returns true if successful and false if not
     * @return Whether or not the redo was successful
     */
    public boolean redo(){
        try {
            //init id, declare i, j
            int id = this.redo.pop();
            int i;
            int j;

            //for each operation, init i, j, push info to undo stack, update text
            if (id == caseSwapID){
                j = this.redo.pop();
                i = this.redo.pop();
                this.undo.multiPush(new int[]{i, j, id});
                this.text = this.text.substring(0, i) + swapCase(this.text.substring(i, j)) +
                        this.text.substring(j);
            }else if (id == insertID){
                j = this.redo.pop();
                i = this.redo.pop();
                this.undo.multiPush(new int[]{i, j, id});
                //get inserted text
                String inserted = this.insertedText.pop();
                this.text = this.text.substring(0, i) + inserted + this.text.substring(i);

            }else if (id == deleteID){
                j = this.redo.pop();
                i = this.redo.pop();
                this.undo.multiPush(new int[]{i, j, id});
                //push deleted text to deletedtext stack
                this.deletedText.push(this.text.substring(i, j));
                this.text = this.text.substring(0, i) + this.text.substring(j);
            }
        }catch (Exception EmptyStackException){
            return false;
        }
        return true;
    }
}
