class Consumer {
    private MessageQueue queue;

    public Consumer(MessageQueue queue) {
        this.queue = queue;
    }

    public void consume() {
        while (true) {
            Node message = queue.dequeue();
            if (message != null) {
                message.getMessageType().process(message);
            }
        }
    }
}