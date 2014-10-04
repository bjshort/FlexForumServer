package com.brendex.flex.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class ServerConfiguration extends Configuration {
    @JsonProperty
    private String message;

    @JsonProperty
    private int messageRepetitions;

    public String getMessage() {
        return message;
    }

    public int getMessageRepetitions() {
        return messageRepetitions;
    }
}