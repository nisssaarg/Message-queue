import java.io.*;
import java.net.*;

class Producer {
    private static final String SERVER_ADDRESS = "localhost"; // Replace with the server IP address if needed
    private static final int SERVER_PORT = 8080;

    private int number;

    public Producer(int number) {
        this.number = number;
    }

    public void produce() {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            for (int i = 1; i <= number; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append("Message");
                sb.append(number);
                sb.append(i);

                String message = sb.toString();
                out.println("enqueue " + message + " " + MessageType.Milk);
                String response = in.readLine();
                System.out.println("Server response: " + response);

                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void produceInt() {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            for (int i = 1; i <= number; i++) {
                out.println("enqueue " + i + " " + MessageType.Pizza);
                String response = in.readLine();
                System.out.println("Server response: " + response);

                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}