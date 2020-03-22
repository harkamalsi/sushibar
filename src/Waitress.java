/**
 * This class implements the consumer part of the producer/consumer problem. One
 * waitress instance corresponds to one consumer.
 */
public class Waitress implements Runnable {

    /**
     * Creates a new waitress. Make sure to save the parameter in the class
     *
     * @param waitingArea The waiting area for customers
     */

    private WaitingArea waitingArea;

    Waitress(WaitingArea waitingArea) {
        this.waitingArea = waitingArea;
    }

    /**
     * This is the code that will run when a new thread is created for this instance
     */
    @Override
    public void run() {
        while (this.waitingArea.getQueueSize() > 0 || SushiBar.isOpen) {
            Customer customer;
            synchronized (this.waitingArea) {
                // this.waitingArea is protecting (monitoring) the code inside this block
                while (this.waitingArea.getQueueSize() == 0) {
                    try {
                        this.waitingArea.wait();
                    } catch (InterruptedException e) {
                    }
                }

                customer = this.waitingArea.next();
                // Notifies the producer
                this.waitingArea.notifyAll();
            }

            try {
                Thread.sleep(SushiBar.waitressWait);
            } catch (InterruptedException e) {
            }

            customer.order();

        }

    }

}
