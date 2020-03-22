import java.util.Random;

/**
 * This class implements the Door component of the sushi bar assignment The Door
 * corresponds to the Producer in the producer/consumer problem
 */
public class Door implements Runnable {

    /**
     * Creates a new Door. Make sure to save the
     * 
     * @param waitingArea The customer queue waiting for a seat
     */

    private WaitingArea waitingArea;

    public Door(WaitingArea waitingArea) {
        this.waitingArea = waitingArea;
    }

    /**
     * This method will run when the door thread is created (and started). The
     * method should create customers at random intervals and try to put them in the
     * waiting area.
     */
    @Override
    public void run() {

        while (SushiBar.isOpen) {
            synchronized (this.waitingArea) {

                while (this.waitingArea.getQueueSize() == this.waitingArea.getWaitingAreaCapacity()) {
                    try {
                        this.waitingArea.wait();
                    } catch (InterruptedException e) {
                    }
                }

                try {
                    // Little random doorWait
                    Thread.sleep(SushiBar.doorWait + (getRandomInteger() * getRandomPlusOrMinus()));
                } catch (InterruptedException e) {
                }

                this.waitingArea.enter(new Customer());
                this.waitingArea.notifyAll();
            }
        }

        // Logging
        SushiBar.write(Thread.currentThread().getName() + " ***** DOOR CLOSED *****");

    }

    private int getRandomInteger() {
        return (int) (Math.random() * SushiBar.doorWait / 2);
    }

    private int getRandomPlusOrMinus() {
        Random random = new Random();
        return (random.nextBoolean() ? 1 : -1);
    }
}
