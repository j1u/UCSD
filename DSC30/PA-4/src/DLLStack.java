/*
 * NAME: James Lu
 */

/**
 * Stack implementation of DLL
 *
 * @param <T> generic container
 * @author James Lu
 * @since 4/26/2021
 */
public class DLLStack<T> {

    private DoublyLinkedList<T> stack;

    /**
     * Stack constructor creates new DLL
     */
    public DLLStack() {
        this.stack = new DoublyLinkedList<>();
    }

    /**
     * Returns size of stack
     *
     * @return size of stack
     */
    public int size() {
        return this.stack.size();
    }

    /**
     * Returns if stack is empty
     *
     * @return whether or not the stack is empty
     */
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    /**
     * Pushes an element into the stack
     *
     * @param data data to be pushed onto stack
     * @throws IllegalArgumentException if data == null
     */
    public void push(T data) {
        if (data == null){
            throw new IllegalArgumentException();
        }
        this.stack.add(data);
    }

    /**
     * Pops an item off the stack
     *
     * @return item that was removed
     */
    public T pop() {
        if (this.stack.isEmpty()){
            return null;
        }
        return this.stack.remove(this.stack.size() - 1);
    }

    /**
     * Returns item at top of stack
     *
     * @return element at top of stack
     */
    public T peek() {
        if (this.stack.isEmpty()){
            return null;
        }
        return this.stack.get(this.stack.size() - 1);
    }
}
