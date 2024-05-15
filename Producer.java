class Producer {
    private int number;
    remoteQueue<?> remoteQueue = new remoteQueue<>();
    public Producer(int number) {
        this.number = number;
    }

    public void produce() {
        for (int i = 1; i <= number; i++) {
            Node<?> node = new Node<>("Milk " +i,MessageType.Milk);
            remoteQueue.enqueue(node);
            try {
                Thread.sleep(1000); // Wait before retrying
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void produceInt() {
        for (int i = 1; i <= number; i++) {
            Node<?> node = new Node<>("Pizza " +i,MessageType.Pizza);
            remoteQueue.enqueue(node);
            try {
                Thread.sleep(1000); // Wait before retrying
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
