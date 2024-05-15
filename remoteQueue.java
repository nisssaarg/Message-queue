import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class remoteQueue<T> implements queue<T> {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;
    private static final String ENQUEUE_STRING = "Enqueue";
    private static final String ERROR_STRING = "Error occurred, retrying...";
    private static final String DEQUEUE_STRING = "dequeue";

    @Override
    public void enqueue(Node node) {
        boolean messageSent = false;
        while (!messageSent) {
            try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                out.writeObject(ENQUEUE_STRING);
                out.writeObject(node);
                String response = (String) in.readObject();
                System.out.println("Server response: " + response);
                messageSent = true;
                Thread.sleep(1000);
            } catch (IOException | InterruptedException | ClassNotFoundException e) {
                System.out.println(ERROR_STRING);
            }
        }
    }

    @Override
    public Node<T> dequeue() {
        while (true) {
            boolean messageReceived = false;
            while (!messageReceived) {
                try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                     ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                    out.writeObject(DEQUEUE_STRING);
                    Object response = in.readObject();
                    if (response.equals("EMPTY")) {
                        System.out.println("Queue is empty");
                    } else if (response instanceof Node) {
                        Node<T> node = (Node<T>) response;
                        return node;
                    } else {
                        System.out.println("Invalid server response: " + response);
                    }
                    messageReceived = true;
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println(ERROR_STRING);
                }
            }
        }
    }
}
