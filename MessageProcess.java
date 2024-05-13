public interface MessageProcess<T> {
    void process(Node<T> message);
}
