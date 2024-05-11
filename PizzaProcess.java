public class PizzaProcess implements MessageProcess {

    @Override
    public void process(Node message) {
        System.out.println("Pizza Method processed" + message.message);
    }

}
