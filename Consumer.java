class Consumer {
    private MessageQueue queue;
    private int number;

    public Consumer(MessageQueue queue,int number) {
        this.queue = queue;
        this.number = number;
    }

    public void consume() {
        while (true) {
            Object message = queue.dequeue();
            System.out.println(Thread.currentThread().getId() + " finished");
            if (message != null) {
                System.out.println("Consumbed "+ message + " by "+ number);
                //System.out.println("Size after removing: " + queue.size());
            }
        }
    }
}