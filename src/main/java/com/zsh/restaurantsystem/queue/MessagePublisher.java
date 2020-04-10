package com.zsh.restaurantsystem.queue;

public interface MessagePublisher {
    void publish(final String message);
}
