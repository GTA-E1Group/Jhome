package com.nettyService.model;

/**
 * @author : Daxv
 * @date : 17:54 2020/5/15 0015
 */

public enum ChatType {
    REGISTER("REGISTER"), SINGLE_SENDING("SINGLE_SENDING"), GROUP_SENDING("GROUP_SENDING"), FILE_MSG_SINGLE_SENDING("FILE_MSG_SINGLE_SENDING"), FILE_MSG_GROUP_SENDING("FILE_MSG_GROUP_SENDING");
    private String type;
    ChatType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}