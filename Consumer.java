class Consumer {
    private MessageQueue queue;
    MessageMap map ;
    public Consumer(MessageQueue queue , MessageMap map) {
        this.queue = queue;
        this.map = map;
    }

    public void consume() {
        while (true) {
            Node message = queue.dequeue();
            if (message != null) {
                map.getProcess(message.getMessageType()).process(message);
            }
        }
    }
}