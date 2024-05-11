import java.util.HashMap;

public class MessageMap {
    private static HashMap<MessageType,MessageProcess> map = new HashMap<>();

    static{
        map.put(MessageType.Milk, new MilkProcess());
        map.put(MessageType.Pizza, new PizzaProcess());
    }

    public MessageProcess getProcess(MessageType type){
        return map.get(type);
    }
}
