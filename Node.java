import java.io.EOFException;
import java.io.Serializable;

class Node<T> implements Serializable{
    MessageType type;
    T message;
    Node<T> next;

    private static final long serialVersionUID = 1L;
    
    public Node (T message, MessageType type){
        this.message = message;
        this.type = type;
        next = null;
    }

    public MessageType getMessageType(){
        return type;
    }

    public T getMessage(){
        return message;
    }

}