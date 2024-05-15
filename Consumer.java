import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class Consumer {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;
    private MessageMap map;
    remoteQueue<?> remoteQueue = new remoteQueue<>();

    public Consumer(MessageMap map) {
        this.map = map;
    }

    public void consume()  {
        while (true) {
            Node<?> node = remoteQueue.dequeue();
            System.out.println("Received message: " + node.getMessage() + " (" + node.getMessageType() + ")");
            map.getProcess(node.getMessageType()).process(node);
            
        }
    }
}
