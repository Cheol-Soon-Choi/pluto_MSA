package com.ccs.events.source;

import com.ccs.events.CustomChannels;
import com.ccs.events.models.ItemChangeModel;
import com.ccs.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component // 메시지 발행 코드
@EnableBinding(CustomChannels.class)
public class SimpleSourceBean {
//    private Source source;
    //다중 채널로 customchannels 사용
    private CustomChannels pushbinder;

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    @Autowired
    public SimpleSourceBean(CustomChannels pushbinder) {
        this.pushbinder = pushbinder;
    }

    public void publishItemChange(String action, Long itemId) {
        logger.debug("***** Sending Kafka message {} for Item Id: {}", action, itemId);

        //POJO
        ItemChangeModel itemChangeModel = new ItemChangeModel(
                ItemChangeModel.class.getTypeName(),
                action,
                itemId,
                UserContext.getCorrelationId()
        );

        //source클래스를 사용해서 메시지 전달 -> 단일채널에 메시지 전달시 사용
        //다중 채널 사용을 위해 커스텀 바인더/채널로 변경
        pushbinder.output().send(MessageBuilder.withPayload(itemChangeModel).build());
    }
}
