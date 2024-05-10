class Node<T>{
    MessageType type;
    T message;
    Node<T> next;
    
    public Node (T message, MessageType type){
        this.message = message;
        this.type = type;
        next = null;
    }
}