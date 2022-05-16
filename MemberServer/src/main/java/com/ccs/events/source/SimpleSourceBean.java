package com.ccs.events.source;

import com.ccs.events.CustomChannels;
import com.ccs.events.models.MemberChangeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component // 메시지 발행 코드
@EnableBinding(CustomChannels.class)
public class SimpleSourceBean {

    private CustomChannels pushbinder;

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    @Autowired
    public SimpleSourceBean(CustomChannels pushbinder) {
        this.pushbinder = pushbinder;
    }

    public void publishMemberChange(String action, Long memberId) {
        logger.debug("***** Sending Kafka message {} for Member Id: {}", action, memberId);

        //POJO
        MemberChangeModel memberChangeModel = new MemberChangeModel(
                MemberChangeModel.class.getTypeName(),
                action,
                memberId,
                "none"
        );

        //source클래스를 사용해서 메시지 전달 -> 단일채널에 메시지 전달시 사용
        //다중 채널일때 CustomChannels을 사용
        pushbinder.output().send(MessageBuilder.withPayload(memberChangeModel).build());
    }
}
