package org.testing;

import org.example.ContentServer;
import org.example.Message;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class ContentServerTest {

    @Test
    @DisplayName("Test if Message string is split correctly upon instantiation")
    public void createMessage(){
        Message message = new Message("106, EUR/USD, 1.1234,1.2345,01-06-2020 12:01:01:001");
        assertEquals("106", String.valueOf(message.getId()));
        assertEquals("EUR/USD", message.getExchange());
        assertEquals("1.1234", String.valueOf(message.getAskingPrice()));
        assertEquals("1.2345", String.valueOf(message.getBid()));
        assertEquals("1.246845", String.valueOf(message.getAskingPriceWithCommission()));
        assertEquals("1.112166", String.valueOf(message.getBidWithCommission()));
        assertEquals("01-06-2020 12:01:01:001", message.getTimeStamp());
    }

    @Test
    @DisplayName("Test if a message that holds an exchange already present in the hashmap successfully overwrites the previous record")
    public void testAddMessageToList(){
        Message msg1 = new Message("106, EUR/USD, 1.1234,1.2345,01-06-2020 12:01:01:001");
        Message msg2 = new Message("108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002");
        Message msg3 = new Message("109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100");

        ContentServer contentServer = new ContentServer();

        contentServer.addMessageToList(msg1);
        contentServer.addMessageToList(msg2);
        contentServer.addMessageToList(msg3);

        List<Message> messages = new ArrayList<>();
        messages.add(msg1);
        messages.add(msg3);
        assertEquals("The hashmap should always overwrite the latest message for every exchange", messages, contentServer.getMessages());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testMessage(){
        Message msg1 = new Message("106, EUR/USD, 1.5,1.4,01-06-2020 12:01:01:001");
        assertEquals("Should throw an error when bid > askingPrice", msg1, 0);
    }

}
