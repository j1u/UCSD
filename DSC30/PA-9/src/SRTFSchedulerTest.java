import static org.junit.Assert.*;

public class SRTFSchedulerTest {
    SRTFScheduler t1;
    SRTFScheduler t2;
    SRTFScheduler t3;

    @org.junit.Before
    public void setUp() throws Exception {
        t1 = new SRTFScheduler();
        t2 = new SRTFScheduler();
        t3 = new SRTFScheduler();

        t1.addTask("P1", 10);
        t1.addTask("P2", 8);
    }

    @org.junit.Test
    public void addTask() {
        assertEquals("Task(P2, 8, 8), Task(P1, 10, 10)", t1.getWaitList());
        t1.addTask("P3", 5);
        t1.addTask("P4", 13);
        assertEquals("Task(P3, 5, 5), Task(P1, 10, 10), " +
                "Task(P2, 8, 8), Task(P4, 13, 13)", t1.getWaitList());
        t1.addTask("P5", 6);
        t1.addTask("P6", 16);
        assertEquals("Task(P3, 5, 5), Task(P5, 6, 6), Task(P2, 8, 8), " +
                "Task(P4, 13, 13), Task(P1, 10, 10), Task(P6, 16, 16)", t1.getWaitList());
    }

    @org.junit.Test
    public void run() {
        t1.run();
        assertEquals("[1 Task(P2, 8, 7)]", t1.getLog().toString());
        t1.run();
        assertEquals("[1 Task(P2, 8, 7), 2 Task(P2, 8, 6)]", t1.getLog().toString());
        t1.run();
        assertEquals("[1 Task(P2, 8, 7), 2 Task(P2, 8, 6), 3 Task(P2, 8, 5)]", t1.getLog().toString());
    }

    @org.junit.Test
    public void getLog() {
        t1.run();
        assertEquals("[1 Task(P2, 8, 7)]", t1.getLog().toString());
        t1.run();
        assertEquals("[1 Task(P2, 8, 7), 2 Task(P2, 8, 6)]", t1.getLog().toString());
        t1.run();
        assertEquals("[1 Task(P2, 8, 7), 2 Task(P2, 8, 6), 3 Task(P2, 8, 5)]", t1.getLog().toString());
    }
}