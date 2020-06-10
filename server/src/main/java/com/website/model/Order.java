package com.website.model;

public class Order {
    private Long OID;
    private OrderState state;

    public Long getOID() {
        return OID;
    }

    public void setOID(Long oID) {
        OID = oID;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    
}