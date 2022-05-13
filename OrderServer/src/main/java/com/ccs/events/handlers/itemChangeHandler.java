package com.ccs.events.handlers;

import com.ccs.events.CustomChannels;
import com.ccs.events.models.ItemChangeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;


@EnableBinding(CustomChannels.class)
public class itemChangeHandler {

    private static final Logger logger = LoggerFactory.getLogger(itemChangeHandler.class);

    @StreamListener("itemInput")
    public void loggerSink(ItemChangeModel itemChangeModel) {
        logger.debug("Received a message of type " + itemChangeModel.getType());

        switch(itemChangeModel.getAction()){
            case "GET":
                logger.debug("Received a GET event from the item server for Item id {}", itemChangeModel.getItemId());
                break;
            case "SAVE":
                logger.debug("Received a SAVE event from the item server for Item id {}", itemChangeModel.getItemId());
                break;
            case "UPDATE":
                logger.debug("Received a UPDATE event from the item server for Item id {}", itemChangeModel.getItemId());

                break;
            case "DELETE":
                logger.debug("Received a DELETE event from the item server for Item id {}", itemChangeModel.getItemId());

                break;
            default:
                logger.error("Received an UNKNOWN event from the item server of type {}", itemChangeModel.getType());
                break;

        }
    }

}
