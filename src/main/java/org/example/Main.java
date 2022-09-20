package org.example;

/*
Example REST endpoints to ADD a message and to GET all messages.
Assumptions:
1) Spring-boot framework used
2) A repository class for Message has been created to allow for encapsulation, storage, retrieval and search behaviours e.g. findAll().

    @PostMapping("/addMessage")
    public String add(@RequestBody Message message){
        messageRepository.save(message);
        return "New Message added";
    }

     @GetMapping("/getMessages")
     public ResponseEntity<List<Message>> getMessages() {
        List<Message> messages = messageRepository.findAll();
      return ResponseEntity.status(HttpStatus.OK).body(messages);
    }
 */

public class Main {
    public static void main(String[] args)  {

        Publisher publish1 = new Publisher();

        Subscriber sub1 = new Subscriber();
        Subscriber sub2 = new Subscriber();
        Subscriber sub3 = new Subscriber();

        //CREATE AND PUBLISH NEW RECORDS
        Message msg1 = new Message("106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001");
        Message msg2 = new Message("107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002");
        Message msg3 = new Message("108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002");

        publish1.publish(msg1, ContentServer.getInstance());
        publish1.publish(msg2, ContentServer.getInstance());
        publish1.publish(msg3, ContentServer.getInstance());

        Message msg4 = new Message("109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100"); //should overwrite record 108
        Message msg5 = new Message("110, EUR/JPY, 119.61,119.91,01-06-2020 12:01:02:110"); //should overwrite record 107

        publish1.publish(msg4, ContentServer.getInstance());
        publish1.publish(msg5, ContentServer.getInstance());

        //DECLARE SUBSCRIBERS
        //SUB1
        sub1.addSubscriber("EUR/USD",ContentServer.getInstance());
        sub1.addSubscriber("GBP/USD",ContentServer.getInstance());

        //SUB2
        sub2.addSubscriber("EUR/JPY",ContentServer.getInstance());

        //SUB3
        sub3.addSubscriber("GBP/USD",ContentServer.getInstance());
        sub3.addSubscriber("EUR/JPY",ContentServer.getInstance());

        ContentServer.getInstance().broadcast();

        //Print messages of each subscriber to see which messages they got
        System.out.println("\nMessages of sub1 Subscriber are: ");
        sub1.displayMessages();

        System.out.println("\nMessages of sub2 Subscriber are: ");
        sub2.displayMessages();

        System.out.println("\nMessages of sub3 Subscriber are: ");
        sub3.displayMessages();

    }
}