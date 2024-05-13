import java.io.*;
import java.net.*;
import java.util.concurrent.*;

class MessageQueueServer {
    private static final int PORT = 8080;
    private static final MessageQueue<String> queue = new MessageQueue<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            ExecutorService executor = Executors.newCachedThreadPool();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket.getInetAddress());
                executor.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                while (true) {
                    String command = in.readLine();
                    if (command == null) {
                        break; // Client disconnected
                    }

                    processCommand(command, out);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void processCommand(String command, PrintWriter out) {
            String[] parts = command.split(" ");
            switch (parts[0]) {
                case "enqueue":
                    queue.enqueue(parts[1], MessageType.valueOf(parts[2]));
                    out.println("OK");
                    break;
                case "dequeue":
                    Node<String> message = queue.dequeue();
                    if (message != null) {
                        out.println("NODE " + message.message+" "+message.getMessageType());
                    } else {
                        out.println("EMPTY");
                    }
                    break;
                case "size":
                    out.println("SIZE " + queue.size());
                    break;
                default:
                    out.println("INVALID");
            }
        }
    }
}