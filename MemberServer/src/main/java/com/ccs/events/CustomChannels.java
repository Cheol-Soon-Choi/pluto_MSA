package com.ccs.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomChannels {

    @Output("memberOutput")
    MessageChannel output();
}
