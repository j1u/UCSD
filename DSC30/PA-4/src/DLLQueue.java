/*
 * NAME: James Lu
 */

/**
 * Queue implementation of DLL
 *
 * @param <T> generic container
 * @author James Lu
 * @since 4/26/2021
 */
public class DLLQueue<T> {

    private DoublyLinkedList<T> queue;

    /**
     * Queue constructor creates new DLL
     */
    public DLLQueue() {
        this.queue = new DoublyLinkedList<>();
    }

    /**
     * Returns size of queue
     *
     * @return size of queue
     */
    public int size() {
        return this.queue.size();
    }

    /**
     * Returns if queue is empty
     *
     * @return whether or not the queue is empty
     */
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    /**
     * Enqueues an element into the queue
     *
     * @param data data to be enqueued into queue
     * @throws IllegalArgumentException if data == null
     */
    public void enqueue(T data) {
        if (data == null){
            throw new IllegalArgumentException();
        }
        this.queue.add(data);
    }

    /**
     * Dequeues an item out of the queue
     *
     * @return item that was removed
     */
    public T dequeue() {
        if (this.queue.isEmpty()){
            return null;
        }
        return this.queue.remove(0);
    }

    /**
     * Returns item in front of queue
     *
     * @return element at front of queue
     */
    public T peek() {
        if (this.queue.isEmpty()){
            return null;
        }
        return this.queue.get(0);
    }

}
