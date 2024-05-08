class MessageQueue<T> implements queue<T>{
    public Node<T> front;
    public Node<T> back;
    
    public MessageQueue(){
        front = null;
        back = null;
    }
    
    @Override
    public synchronized void enqueue(T message){
        Node<T> current = new Node<T>(message);
        if(back == null || front == null)
                back = front = current;
        else{
            back.next = current;
            back = current;
        }
        notifyAll();
    }
    
    @Override
    public synchronized T dequeue(){
        if (front == null){ 
            back = null;
            return null;
        }
        Node<T> current = (Node<T>)front;
        front = front.next;

        notifyAll();
        return current.message;    
    }
}
