public class  MilkProcess implements MessageProcess {

    @Override
    public void process(Node message) {
        System.out.println(" Milk processed " + message.message);
    }

}
