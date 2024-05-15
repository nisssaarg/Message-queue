public class PizzaProcess<T> implements MessageProcess<T> {

    @Override
    public void process(Node<T> message) {
        System.out.println("Pizza processed " + message.message);
    }

}
