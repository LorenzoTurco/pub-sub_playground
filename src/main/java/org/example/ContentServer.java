package org.example;

import java.sql.SQLOutput;
import java.util.*;

public class ContentServer {
    private HashMap<String, Set<Subscriber>> subscriberList;
    private List<Message> messages;
    private static ContentServer serverInstance; //Singleton instantiation of a content server

    public static ContentServer getInstance() { //Create content server if it doesn't already exist
        if (serverInstance == null) {
            serverInstance = new ContentServer();
        }
        return serverInstance;
    }
    public ContentServer(){
        this.subscriberList = new HashMap<>();
        this.messages = new ArrayList<>();
    }

    public void addMessageToList(Message message){ //always maintain the latest price for each exchange
        for(Message m : messages){
            if(m.getExchange().equals(message.getExchange())) {
                messages.remove(m);
                break;
            }
        }
        messages.add(message);
    }

    public void addSubscriber(String exchange, Subscriber subscriber){

        Set<Subscriber> subscribers;
        if(subscriberList.containsKey(exchange)){ //if exchange already exists, add subscriber to that exchange
            subscribers = subscriberList.get(exchange);
        }else{
            subscribers = new HashSet<>(); //if exchange doesn't exist, create new a set of subscribers for that exchange and add that subscriber to it.
        }
        subscribers.add(subscriber);
        subscriberList.put(exchange, subscribers);
    }

    public void broadcast(){ // broadcast the new messages to the subscribers
        if(messages.isEmpty()){
            System.out.println("No new records have been added by the publisher");
        }else{
            while(!messages.isEmpty()){
                Message message = messages.remove(messages.size()-1); //Get the latest message

                Set<Subscriber> subscribersOfExchange = subscriberList.get(message.getExchange()); //Gets all the subscribers that are subscribed to the exchange of the latest message

                for(Subscriber subscriber : subscribersOfExchange){ //loops through the subscribers of the exchange of the latest message
                    List<Message> subscriberMessages = subscriber.getSubscriberMessages(); //get current exchanges the subscriber is subscribed to
                    subscriberMessages.add(message); //add the new message
                    subscriber.setSubscriberMessages(subscriberMessages); //update the current subscriptions
                }
            }
        }
    }
    public List<Message> getMessages() {
        return messages;
    }
}
