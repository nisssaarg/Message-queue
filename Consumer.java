class Consumer {
    private MessageQueue queue;

    public Consumer(MessageQueue queue) {
        this.queue = queue;
    }

    public void consume() {
        while (true) {
            Object message = queue.dequeue();
            //System.out.println(Thread.currentThread().getId() + " finished");
            if (message != null) {
                //System.out.println(Thread.currentThread().getId() + " Consumed "+ message );
                //System.out.println("Size after removing: " + queue.size());
            }
        }
    }
}