package com.ryderbelserion.blockparticles.platform;

public interface Sender {

    boolean isPlayer();

    String getName();

    boolean hasPermission(String permission);

    //todo() add args support.
    void sendMessage(String value, boolean hasPrefix);

    default void sendMessage(String value) {
        sendMessage(value, false);
    }

    default void sendMessageWithPrefix(String value) {
        sendMessage(value, true);
    }
}