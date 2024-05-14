import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class Consumer {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;
    private MessageMap map;

    public Consumer(MessageMap map) {
        this.map = map;
    }

    public void consume()  {
        while (true) {
            boolean messageReceived = false;
            while (!messageReceived) {
                try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                     ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                    out.writeObject("dequeue");
                    Object response = in.readObject();
                    if (response.equals("EMPTY")) {
                        System.out.println("Queue is empty");
                    } else if (response instanceof Node) {
                        Node<?> node = (Node<?>) response;
                        System.out.println("Received message: " + node.getMessage() + " (" + node.getMessageType() + ")");
                        map.getProcess(node.getMessageType()).process(node);
                    } else {
                        System.out.println("Invalid server response: " + response);
                    }
                    messageReceived = true;
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Error occurred, retrying...");
                    try {
                        Thread.sleep(1000); // Wait before retrying
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}
