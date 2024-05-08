import java.util.concurrent.CountDownLatch;

class Producer {
    private MessageQueue queue;
    private int number;
    private CountDownLatch latch;

    public Producer(MessageQueue queue, int number, CountDownLatch latch) {
        this.queue = queue;
        this.number = number;
        this.latch = latch;
    }

    public void produce() {
        for (int i = 1; i <= number; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Message");
            sb.append(number);
            sb.append(i);
            queue.enqueue(sb.toString());
            System.out.println("Producing "  + sb.toString());
            latch.countDown(); // Signal the latch each time a message is enqueued
            try {
                Thread.sleep(100); // Simulate some delay between producing messages
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}