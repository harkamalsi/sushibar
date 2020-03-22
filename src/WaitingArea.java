import java.util.LinkedList;
import java.util.Queue;

/**
 * This class implements a waiting area used as the bounded buffer, in the producer/consumer problem.
 */
public class WaitingArea {

    /**
     * Creates a new waiting area.
     *
     * @param size The maximum number of Customers that can be waiting.
     */

    // FIFO queue
    private Queue<Customer> waitingArea;
    private int waitingAreaCapacity;

    public WaitingArea(int size) {
        this.waitingAreaCapacity = size;
        waitingArea = new LinkedList<Customer>();
    }

    /**
     * This method should put the customer into the waitingArea
     *
     * @param customer A customer created by Door, trying to enter the waiting area
     */
    public synchronized void enter(Customer customer) {
        SushiBar.write(
                Thread.currentThread().getName() + ": Customer " + customer.getCustomerID() + " is now waiting.");
        this.waitingArea.add(customer);
    }

    /**
     * @return The customer that is first in line.
     */
    public synchronized Customer next() {
        Customer customer = this.waitingArea.remove();
        SushiBar.write(
                Thread.currentThread().getName() + ": Customer " + customer.getCustomerID() + " is now fetched.");

        return customer;
    }

    public int getQueueSize() { return this.waitingArea.size(); }

    public int getWaitingAreaCapacity() { return this.waitingAreaCapacity; }
}
