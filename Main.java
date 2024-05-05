public class Main {
    static MessageQueue<String> queue = new MessageQueue<>();
    static Consumer consumer = new Consumer(queue);
    public static void main(String args[]){
        Thread ConsumerThread = new Thread(() -> consumer.consume());
        ConsumerThread.start();

        Producer producer = new Producer(queue, 100);
        Thread ProducerThread = new Thread(() -> producer.produce());

        ProducerThread.start();
        Producer producer2 = new Producer(queue, 200);
        Thread ProducerThread2 = new Thread(() -> producer2.produce());
        
        ProducerThread2.start();
    }
}
