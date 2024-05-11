import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();
        MessageMap map = new MessageMap();
        // Initial count of 1
        //Test1(queue);
        //Test2(queue);
        // System.out.println("Before" + queue.size());
        Test3(queue,map);
        // System.out.println("After" + queue.size());
    }

    private static void Test3(MessageQueue queue,MessageMap map) {
        Consumer consumer = new Consumer(queue,map);
        Consumer consumer2 = new Consumer(queue,map);
        Producer producer = new Producer(queue, 5);

        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.submit(() -> consumer.consume());
        executor.submit(() -> consumer2.consume());
        executor.submit(() -> producer.produce());
        executor.submit(() -> producer.produceInt());
        

        executor.shutdown();
    }

    public static void Test1(MessageQueue queue,MessageMap map) {
        
        Consumer consumer = new Consumer(queue,map);
        Producer producer = new Producer(queue, 100);

        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.submit(() -> consumer.consume());
        executor.submit(() -> producer.produce());
        

        executor.shutdown();
    }

    public static void Test2(MessageQueue queue){
        Producer producer = new Producer(queue, 200);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> producer.produce());

        executor.shutdown();
    }
}