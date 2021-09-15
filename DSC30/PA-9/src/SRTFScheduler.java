/*
 * NAME: James Lu
 */

import java.util.*;

/**
 * Shortest remaining time scheduler implemented using java.util's Priority Queue,
 * takes in Tasks and finishes those with highest priority first (shortest time remaining)
 */
public class SRTFScheduler {

    /**
     * Task class that represents a task for the scheduler
     */
    private class Task implements Comparable {

        private String name;
        private int length, remainingTime;

        /**
         * Task constructor that inits instance variables (name, length, remaining time)
         *
         * @param name name of task
         * @param length initial length of task
         */
        public Task(String name, int length) {
            if (name == null || length < 1) throw new IllegalArgumentException();
            this.name = name;
            this.length = length;
            this.remainingTime = length;
        }

        /**
         * Runs a task for one time unit
         *
         * @return whether or not the task is run
         */
        public boolean run() {
            if (this.remainingTime <= 0) return false;
            this.remainingTime--;
            return true;
        }

        /**
         * String representation of task
         *
         * @return task in string form
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Task(").append(this.name).append(", ").append(this.length).append(", ")
                    .append(this.remainingTime).append(")");
            return sb.toString();
        }

        /**
         * Overridden compare to method
         *
         * @param o other object
         * @return if remaining time for this task is <, ==, > another task
         *         if they are equal then compare names in alphabetical order
         */
        @Override
        public int compareTo(Object o) {
            Task other = (Task) o;
            if (this.remainingTime < other.remainingTime){
                return -1;
            }else if (this.remainingTime > other.remainingTime){
                return 1;
            }else{
                if (this.name.compareTo(other.name) < 0){
                    return -1;
                }else if (this.name.compareTo(other.name) > 0){
                    return 1;
                }else{
                    return 0;
                }
            }
        }
    }

    /* instance variables */
    private PriorityQueue<Task> waitlist;
    private int time;
    private List<String> log;

    /**
     * SRTFScheduler constructor, inits waitlist, time, and log
     */
    public SRTFScheduler() {
        this.waitlist = new PriorityQueue<>();
        this.time = 1;
        this.log = new LinkedList<>();
    }

    /**
     * Adds a new task to the scheduler
     *
     * @param name name of task
     * @param length init length of task
     */
    public void addTask(String name, int length) {
        Task newT = new Task(name, length);
        this.waitlist.add(newT);
    }

    /**
     * Runs the task in the front of the priorityQ for one time unit
     *
     * @return Whether or not the run was successful
     */
    public boolean run() {
        if (!this.waitlist.isEmpty()){
            Task curr = this.waitlist.peek();
            curr.run();
            if (curr.remainingTime <= 0){
                this.waitlist.poll();
            }
            this.log.add(this.time + " " + curr.toString());
            this.time++;
            return true;
        }else{
            this.log.add(this.time + " " + "<IDLE>");
            this.time++;
            return false;
        }
    }

    /**
     * Returns string representation of waitlist, used to help test the class
     *
     * @return string representation of waitlist
     */
    public String getWaitList(){
        StringBuilder sb = new StringBuilder();
        for (Task t : this.waitlist) {
            sb.append(t.toString()).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    /**
     * Returns the log of the scheduler
     *
     * @return List of logs
     */
    public List<String> getLog() {
        return this.log;
    }
}
