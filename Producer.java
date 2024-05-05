class Producer {
    private MessageQueue queue;
    private int number;
    
    public Producer(MessageQueue queue, int number) {
        this.queue = queue;
        this.number = number;
    }
    
    public synchronized void produce() {
        System.out.println("Producing");
        for (int i = 1; i <= number; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Message");
            sb.append(number);
            sb.append(i);
            queue.enqueue(sb.toString());
            System.out.println("Produced " + sb.toString());
            if (i % 10 == 0) {
                try {
                    System.out.println("Waiting");
                    Thread.sleep(1000); // Adjust sleep duration to 5 seconds (5000 milliseconds)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        notifyAll(); // Notify waiting threads after producing all messages
    }
}