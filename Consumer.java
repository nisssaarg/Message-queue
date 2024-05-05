class Consumer {
    private MessageQueue queue;

    public Consumer(MessageQueue queue) {
        this.queue = queue;
    }

    public synchronized void consume() {
        while (true) { // Loop indefinitely
            while (queue.front == null) {
                try {
                    // Wait until there are messages in the queue
                    System.out.println("Waiting");
                    wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Consume messages while the queue is not empty
            while (queue.front != null) {
                System.out.println("Consumed " + Thread.currentThread().getId() + " " + queue.dequeue());
            }
        }
    }
}
