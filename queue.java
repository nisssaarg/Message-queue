public interface queue<T> {
    void enqueue(T message);
    T dequeue();
}
