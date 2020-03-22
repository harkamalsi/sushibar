import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SushiBar {
    // SushiBar settings.
    private static int waitingAreaCapacity = 20;
    private static int waitressCount = 7;

    // The duration is in seconds
    private static int duration = 5;

    public static int maxOrder = 15;

    // The following times are in milliseconds
    public static int waitressWait = 60; // Used to calculate the time the waitress spends before taking the order
    public static int customerWait = 2500; // Used to calculate the time the customer spends eating
    public static int doorWait = 120; // Used to calculate the interval at which the door tries to create a customer

    public static boolean isOpen = true;

    public static int customerCounter = 1;

    // Creating log file.
    private static File log;
    private static String path = "./";

    // Variables related to statistics.
    public static SynchronizedInteger servedOrders;
    public static SynchronizedInteger takeawayOrders;
    public static SynchronizedInteger totalOrders;

    public static void main(String[] args) {
        log = new File(path + "log.txt");

        // Initializing shared variables for counting number of orders.
        totalOrders = new SynchronizedInteger(0);
        servedOrders = new SynchronizedInteger(0);
        takeawayOrders = new SynchronizedInteger(0);

        WaitingArea waitingArea = new WaitingArea(waitingAreaCapacity);
        // Producer
        new Thread(new Door(waitingArea)).start();
        // Consumers
        ArrayList<Thread> consumers = new ArrayList<>();
        for (int i = 0; i < waitressCount; i++) {
            Thread t = new Thread(new Waitress(waitingArea));
            consumers.add(t);
            t.start();
        }

        // Starting the clock
        new Clock(duration);

        try {
            for (Thread t : consumers) {
                // Joining main thread with consumer threads to log the final statistics in the
                // end
                t.join();
            }

            // Logging
            SushiBar.write(
                    Thread.currentThread().getName() + ": ***** NO MORE CUSTOMERS - THE SHOP IS CLOSED NOW. *****");
            SushiBar.write(Thread.currentThread().getName() + ": Total orders [" + SushiBar.totalOrders.get()
                    + "] = Eaten orders [" + SushiBar.servedOrders.get() + "] + Takeaway orders ["
                    + SushiBar.takeawayOrders.get() + "]");
        } catch (Exception e) {
        }
    }

    // Writes actions in the log file and console.
    public static void write(String str) {
        try {
            FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Clock.getTime() + ", " + str + "\n");
            bw.close();
            System.out.println(Clock.getTime() + ", " + str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
