import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class Producer {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;

    private int number;

    public Producer(int number) {
        this.number = number;
    }

    public void produce() {
        for (int i = 1; i <= number; i++) {
            boolean messageSent = false;
            while (!messageSent) {
                try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                     ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                    String message = "Message" + number + i;
                    Node<String> node = new Node<>(message, MessageType.Milk);

                    out.writeObject("enqueue");
                    out.writeObject(node);
                    String response = (String) in.readObject();
                    System.out.println("Server response: " + response);

                    messageSent = true;
                    Thread.sleep(1000);
                } catch (IOException | InterruptedException | ClassNotFoundException e) {
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

    public void produceInt() {
        for (int i = 1; i <= number; i++) {
            boolean messageSent = false;
            while (!messageSent) {
                try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                     ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                    Node<Integer> node = new Node<>(i, MessageType.Pizza);

                    out.writeObject("enqueue");
                    out.writeObject(node);
                    String response = (String) in.readObject();
                    System.out.println("Server response: " + response);

                    messageSent = true;
                    Thread.sleep(1000);
                } catch (IOException | InterruptedException | ClassNotFoundException e) {
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
