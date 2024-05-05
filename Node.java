class Node<T>{
    T message;
    Node<T> next;
    
    public Node (T message){
        this.message = message;
        next = null;
    }
}