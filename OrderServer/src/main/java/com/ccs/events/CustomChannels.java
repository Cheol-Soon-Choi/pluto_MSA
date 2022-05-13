package com.ccs.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomChannels {

    @Input("itemInput")
    SubscribableChannel itemInputChannel();

    @Input("memberInput")
    SubscribableChannel memberInputChannel();
}
