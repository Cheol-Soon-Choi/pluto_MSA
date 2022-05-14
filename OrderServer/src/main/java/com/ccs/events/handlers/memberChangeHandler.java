package com.ccs.events.handlers;

import com.ccs.events.CustomChannels;
import com.ccs.events.models.MemberChangeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(CustomChannels.class)
public class memberChangeHandler {

    private static final Logger logger = LoggerFactory.getLogger(itemChangeHandler.class);

    @StreamListener("memberInput")
    public void loggerSink(MemberChangeModel memberChangeModel) {
        logger.debug("##### Received a message of type " + memberChangeModel.getType());

        switch (memberChangeModel.getAction()) {
            case "GET":
                logger.debug("##### Received a GET event from the item server for Member id {}", memberChangeModel.getMemberId());
                break;
            case "SAVE":
                logger.debug("##### Received a SAVE event from the item server for Member id {}", memberChangeModel.getMemberId());
                break;
            case "UPDATE":
                logger.debug("##### Received a UPDATE event from the item server for Member id {}", memberChangeModel.getMemberId());

                break;
            case "DELETE":
                logger.debug("##### Received a DELETE event from the item server for Member id {}", memberChangeModel.getMemberId());

                break;
            default:
                logger.error("##### Received an UNKNOWN event from the item server of type {}", memberChangeModel.getType());
                break;

        }
    }

}
