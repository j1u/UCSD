/*
 * NAME: James Lu
 */

import java.util.Queue;

/**
 * Round Robin class that handles a simplified version of round robin.
 * Handles tasks when there are limited resources with a queue of users
 *
 * @author James Lu
 * @since 4/26/2021
 */
public class RoundRobin {

    /* constants */
    private static final String TASK_HANDLED = "All tasks are already handled.";

    /* instance variables */
    private DoublyLinkedList<Task> waitlist, finished;
    private int quantum, burstTime, waitTime;
    private static int defaultQuantum = 4;
    private static int delArrow = 4;

    /**
     * Constructor wrapper that defaults quantum to 4
     *
     * @param toHandle array of tasks to handle
     */
    public RoundRobin(Task[] toHandle) {
        this(defaultQuantum, toHandle);
    }

    /**
     * Constructor that initializes round robin object with given quantum and
     * array of tasks
     *
     * @param quantum limit for tasks
     * @param toHandle array of tasks to handle
     * @throws IllegalArgumentException if quantum < 1 or array of tasks is empty or null
     */
    public RoundRobin(int quantum, Task[] toHandle) {
        if (quantum < 1 || toHandle == null || toHandle.length == 0){
            throw new IllegalArgumentException();
        }
        this.quantum = quantum;
        this.burstTime = 0;
        this.waitTime = 0;
        this.waitlist = new DoublyLinkedList<>();
        this.finished = new DoublyLinkedList<>();
        for (Task t : toHandle) {
            this.waitlist.add(t);
        }
    }

    /**
     * It goes through the tasks in the waitlist, schedules them in order for one
     * quantum period, and then returns it to the queue or marks it completed as
     * necessary. It keeps track of the burst and wait times. It loops through the
     * waitlist until no more tasks need to be scheduled.
     *
     * @return task handled constant if all tasks are completed or a string with
     * the total burst and wait times and the order in which each task is completed
     */
    public String handleAllTasks() {
        if (this.waitlist.isEmpty()){
            return TASK_HANDLED;
        }
        //while waitlist is populated
        while (!this.waitlist.isEmpty()) {
            Task curr = this.waitlist.remove(0);
            //handle tasks for quantum time
            for (int i = 0; i < quantum; i++) {
                curr.handleTask();
                this.burstTime++;
                this.waitTime += this.waitlist.size();
                if (curr.isFinished()) {
                    this.finished.add(curr);
                    break;
                }
            }
            if (!curr.isFinished()){
                this.waitlist.add(curr);
            }
        }

        //build string
        StringBuilder sb = new StringBuilder();
        sb.append("All tasks are handled within ")
                .append(this.burstTime)
                .append(" units of burst time and ")
                .append(this.waitTime)
                .append(" units of wait time. The tasks are finished in this order:\n");
        for (int i = 0; i < this.finished.size(); i++) {
            sb.append(this.finished.get(i).toString());
            sb.append(" -> ");
        }
        return sb.substring(0, sb.length() - delArrow);
    }

    /**
     * Main method for testing.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Task[] test1 = {new Task("A", 3), new Task("B", 4),
                        new Task("C", 4), new Task("D", 12),
                        new Task("E", 6), new Task("F", 3)};
        RoundRobin rr1 = new RoundRobin(3, test1);     // Quantum: 3, ToHandle: test1
        System.out.println(rr1.handleAllTasks());   // Burst: 32, Wait: 86, Order: AFBCED
        System.out.println();
        System.out.println(rr1.handleAllTasks());   // TASK_HANDLED
        System.out.println();

        Task[] test2 = {new Task("A", 9), new Task("B", 8),
                        new Task("C", 6), new Task("D", 4),
                        new Task("E", 4), new Task("F", 3)};
        RoundRobin rr2 = new RoundRobin(test2);  // Quantum: 4, ToHandle: test2
        System.out.println(rr2.handleAllTasks());   // Burst: 34, Wait: 123, Order: DEFBCA
        System.out.println();
        System.out.println(rr2.handleAllTasks());   // TASK_HANDLED
        System.out.println();

        Task[] test3 = {new Task("A", 7), new Task("B", 5),
                        new Task("C", 3), new Task("D", 1),
                        new Task("E", 2), new Task("F", 4),
                        new Task("G", 6), new Task("H", 8)};
        RoundRobin rr3 = new RoundRobin(3, test3);     // Quantum: 3, ToHandle: test3
        System.out.println(rr3.handleAllTasks());   // Burst: 36, Wait: 148, Order: CDEBFGAH
        System.out.println();
        System.out.println(rr3.handleAllTasks());   // TASK_HANDLED
        System.out.println();
    }
}
