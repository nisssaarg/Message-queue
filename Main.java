import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {
    public static void main(String[] args) {
        MessageQueue<String> queue = new MessageQueue<>();
        CountDownLatch latch = new CountDownLatch(1); // Initial count of 1
        Test1(queue,latch);
        Test2(queue,latch);
    }

    public static void Test1(MessageQueue<String> queue, CountDownLatch latch) {
        
        Consumer consumer = new Consumer(queue, latch);
        Producer producer = new Producer(queue, 100, latch);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> consumer.consume());
        executor.submit(() -> producer.produce());
        

        executor.shutdown();
    }

    public static void Test2(MessageQueue<String> queue, CountDownLatch latch){
        Producer producer = new Producer(queue, 200 , latch);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> producer.produce());

        executor.shutdown();
    }
}