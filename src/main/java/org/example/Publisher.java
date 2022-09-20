package org.example;

public class Publisher {

    public void publish(Message message, ContentServer contentServer) {
        contentServer.addMessageToList(message);
    }

}
