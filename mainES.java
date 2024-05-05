import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class mainES {
    public static void main(String[] args) {
        MessageQueue<String> queue = new MessageQueue<>();
        Producer producer1 = new Producer(queue, 100);
        Consumer consumer1 = new Consumer(queue);
        Producer producer2 = new Producer(queue, 200);
        //Consumer consumer2 = new Consumer(queue);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.submit(() -> consumer1.consume());
        executor.submit(() -> consumer1.consume());
        executor.submit(() -> producer1.produce());
        executor.submit(() -> producer2.produce());
        

        executor.shutdown();
    }
}
