class MessageQueue<T> implements queue<T> {
    private Node<T>  front;
    private Node<T>  back;
    private int size;

    public MessageQueue() {
        front = null;
        back = null;
        size = 0;
    }

    @Override
    public synchronized void enqueue(T message, MessageType type) {
        Node<T> current = new Node<T>(message,type);
        if (back == null || front == null)
            back = front = current;
        else {
            back.next = current;
            back = current;
        }
        size++;
        System.out.println(Thread.currentThread().getId() + " Enqueued "  +" Message " + message);
        // Notify waiting threads that a new message is enqueued
        notify();
    }

    @Override
    public synchronized Node<T> dequeue() {
        //System.out.println(Thread.currentThread().getId() + " entering dequeue");
        while (front == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(Thread.currentThread().getId() + " wait exited");
        }
        Node<T> current = front;
        front = front.next;
        size--;
        System.out.println(Thread.currentThread().getId() + " Dequeued "  +" Message " + current.message); 
        return current;
    }

    public int size() {
        return size;
    }

}
