package com.service.model;

public enum MessageType {

    WAITINGCONFIRM(0), SENDED(1), COMPLETED(2);
    private Integer type;

    MessageType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
