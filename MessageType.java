public enum MessageType implements MessageTypeInterface{
    String{
        @Override
        public void  process(Node node){
            System.out.println("String processed " + node.message);
        }    
    },
    Integer{
        @Override
        public  void process(Node node){
            System.out.println("Integer processed " + node.message);
        }    
    }
}
