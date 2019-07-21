package com.sda.TicketSystem.model;

public class PriceDTO {

    private String type;
    private String price;

    public PriceDTO(){
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
