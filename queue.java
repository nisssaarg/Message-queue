public interface queue<T> {
    void enqueue(T message,MessageType type);
    T dequeue();
}
