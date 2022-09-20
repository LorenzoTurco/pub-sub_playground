package org.example;

public class Message {
    private int id;
    private String exchange;
    private Double askingPrice;
    private Double bid;
    private String timeStamp;
    private Double askingPriceWithCommission;
    private Double BidWithCommission;

    public Message(String record) throws IllegalArgumentException {

        String[] stringArray = record.split(",");
        this.askingPrice = Double.parseDouble(stringArray[2]);
        this.bid = Double.parseDouble(stringArray[3]);

        if(askingPrice > bid){
            throw new IllegalArgumentException("Bid must be higher than asking price");
        }

        this.id = Integer.parseInt(stringArray[0].trim());
        this.exchange = stringArray[1].trim();

        this.BidWithCommission = (((Double.parseDouble(stringArray[2]) * 0.99) * 10000) / 10000.0);
        this.askingPriceWithCommission = (((Double.parseDouble(stringArray[3]) * 1.01) * 10000) / 10000.0);
        this.timeStamp = stringArray[4];
    }
    public int getId() {
        return id;
    }
    public String getExchange() {
        return exchange;
    }
    public Double getAskingPrice() {
        return askingPrice;
    }

    public Double getAskingPriceWithCommission() {
        return askingPriceWithCommission;
    }

    public Double getBidWithCommission() {
        return BidWithCommission;
    }

    public Double getBid() {
        return bid;
    }
    public String getTimeStamp() {
        return timeStamp;
    }
}
