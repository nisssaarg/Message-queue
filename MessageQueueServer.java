import java.io.*;
import java.net.*;
import java.util.concurrent.*;

class MessageQueueServer {
    private static final int PORT = 8080;
    private static final MessageQueue<Node<String>> queue = new MessageQueue<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            ExecutorService executor = Executors.newCachedThreadPool();

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Accepted connection from " + clientSocket.getInetAddress());
                    executor.execute(new ClientHandler(clientSocket));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
            try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                 ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

                while (true) {
                    String command;
                    try {
                        command = (String) in.readObject();
                        if (command == null) {
                            break; // Client disconnected
                        }
                    } catch (EOFException e) {
                        System.out.println("Client disconnected");
                        break;
                    }

                    processCommand(command, in, out);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void processCommand(String command, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
            switch (command) {
                case "Enqueue":
                    Node<?> node = (Node<?>) in.readObject();
                    queue.enqueue(node);
                    out.writeObject("OK");
                    break;
                case "dequeue":
                    Node<?> message = queue.dequeue();
                    if (message != null) {
                        out.writeObject(message);
                    } else {
                        out.writeObject("EMPTY");
                    }
                    break;
                case "size":
                    out.writeObject("SIZE " + queue.size());
                    break;
                default:
                    out.writeObject("INVALID");
            }
        }
    }
}
