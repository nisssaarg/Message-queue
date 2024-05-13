public class  MilkProcess<T> implements MessageProcess<T> {

    @Override
    public void process(Node<T> message) {
        System.out.println(" Milk processed " + message.message);
    }

}
