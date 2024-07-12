package com.webchat.webchat_server.type;

public enum MessageState {
    SUCCESS(0), UNKNOWN_ERROR(1), SESSION_EXPIRED(2), LOGIN_FAILURE(3), REGISTER_FAILURE(4);

    private final int id;

    MessageState(int id) {
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}