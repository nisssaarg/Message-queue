import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Consumer {

    private static final String SERVER_ADDRESS = "localhost"; // Replace with the server IP address if needed
    private static final int SERVER_PORT = 8080;
    private MessageMap map ;
    
    public Consumer(MessageMap map) {
        this.map = map;
    }

    public void consume() throws InterruptedException {
        while (true) {
            try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
    
                out.println("dequeue");
                String response = in.readLine();
                if (response.equals("EMPTY")) {
                    System.out.println("Queue is empty");
                } else if (response.startsWith("NODE ")) {
                    String[] parts = response.split(" ", 3);
                    String message = parts[1];
                    MessageType messageType = MessageType.valueOf(parts[2]);
                    Node node = new Node<>(message, messageType);
                    System.out.println("Received message: " + node.message + " (" + node.getMessageType() + ")");
                    map.getProcess(node.getMessageType()).process(node);
                } else {
                    System.out.println("Invalid server response: " + response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}