import java.util.UUID;

/**
 * This class implements a customer, which is used for holding data
 * and update the statistics
 */

public class Customer {

    /**
     *  Creates a new Customer.  Each customer should be given a
     *  unique ID
     */
    // private UUID id;
    private int id;
    private int eatenOrders = this.getRandomNumber();
    private int takeawayOrders = this.getRandomNumber();
    private int numberOfOrders = eatenOrders + takeawayOrders;

    public Customer() {
        this.id = SushiBar.customerCounter;
        SushiBar.customerCounter++;
    }


    /**
     * Here you should implement the functionality for ordering food
     * as described in the assignment.
     */
    public void order() {
        try {
            // Logging
            SushiBar.write(Thread.currentThread().getName() + ": Customer " + this.getCustomerID() + " is now eating.");
            Thread.sleep(SushiBar.customerWait);
            SushiBar.write(
                    Thread.currentThread().getName() + ": Customer " + this.getCustomerID() + " is now leaving.");
        } catch (InterruptedException e) {}

        SushiBar.totalOrders.add(this.getNumberOfOrders());
        SushiBar.servedOrders.add(this.getEatenOrders());
        SushiBar.takeawayOrders.add(this.getTakeawayOrders());

    }

    /**
     *
     * @return Returns a random number between 0 and maxOrder/2
     */

    private int getRandomNumber() {
        return (int)(Math.random()*SushiBar.maxOrder/2);
    }

    /**
     *
     * @return Should return the customerID
     */
    public int getCustomerID() { return this.id; }

    public int getNumberOfOrders() {
        return this.numberOfOrders;
    }

    public int getEatenOrders() {
        return this.eatenOrders;
    }

    public int getTakeawayOrders() {
        return this.takeawayOrders;
    }
}
