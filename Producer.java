class Producer {
    private MessageQueue queue;
    private int number;

    public Producer(MessageQueue queue, int number) {
        this.queue = queue;
        this.number = number;
    }

    public void produce() {
        for (int i = 1; i <= number; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Message");
            sb.append(number);
            sb.append(i);
            queue.enqueue(sb.toString(), MessageType.String);
           //System.out.println("Producing "  + sb.toString());
            // System.out.println("Size after adding: " + queue.size());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void produceInt(){
        for(int i = 1 ; i <= number; i++){
            queue.enqueue(i, MessageType.Integer);
        }
    }
}
