package org.example;

import java.util.ArrayList;
import java.util.List;

public class Subscriber {
    private List<Message> subscriberMessages = new ArrayList<>();
    public List<Message> getSubscriberMessages() {
        return subscriberMessages;
    }
    public void setSubscriberMessages(List<Message> subscriberMessages) {
        this.subscriberMessages = subscriberMessages;
    }
    public void addSubscriber(String topic, ContentServer contentServer){
        contentServer.addSubscriber(topic, this);
    }
    public void displayMessages(){
        for(Message message : subscriberMessages){
            System.out.println("Message Exchange: "+ message.getExchange() + "  new asking price: " + message.getAskingPriceWithCommission());
        }
    }
}
