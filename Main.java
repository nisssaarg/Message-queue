public class Main {
    static MessageQueue<String> queue = new MessageQueue<>();
    static Consumer consumer = new Consumer(queue);
    public static void main(String args[]){
        Thread ConsumerThread = new Thread(() -> consumer.consume());
        ConsumerThread.start();

        Producer producer = new Producer(queue, 100);
        Thread ProducerThread = new Thread(() -> producer.produce());

        ProducerThread.start();
    }
}
