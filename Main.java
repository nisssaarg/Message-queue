import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {
    public static void main(String[] args) {
        MessageQueue<String> queue = new MessageQueue<String>();
        // Initial count of 1
        //Test1(queue);
        //Test2(queue);
        // System.out.println("Before" + queue.size());
        Test3(queue);
        // System.out.println("After" + queue.size());
    }

    private static void Test3(MessageQueue<String> queue) {
        Consumer consumer = new Consumer(queue,1);
        Consumer consumer2 = new Consumer(queue,2);
        Producer producer = new Producer(queue, 5);

        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.submit(() -> consumer.consume());
        executor.submit(() -> consumer2.consume());
        executor.submit(() -> producer.produce());
        

        executor.shutdown();
    }

    public static void Test1(MessageQueue<String> queue) {
        
        Consumer consumer = new Consumer(queue,1);
        Producer producer = new Producer(queue, 100);

        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.submit(() -> consumer.consume());
        executor.submit(() -> producer.produce());
        

        executor.shutdown();
    }

    public static void Test2(MessageQueue<String> queue){
        Producer producer = new Producer(queue, 200);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> producer.produce());

        executor.shutdown();
    }
}