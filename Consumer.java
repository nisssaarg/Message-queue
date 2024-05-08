import java.util.concurrent.CountDownLatch;

class Consumer {
    private MessageQueue queue;
    private CountDownLatch latch;

    public Consumer(MessageQueue queue, CountDownLatch latch) {
        this.queue = queue;
        this.latch = latch;
    }

    public void consume() {
        while (true) {
            try {
                latch.await(); // Wait for the latch to count down
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object message = queue.dequeue();
            if (message != null) {
                System.out.println(message);
            }
        }
    }
}