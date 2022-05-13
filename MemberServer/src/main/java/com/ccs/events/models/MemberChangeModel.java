package com.ccs.events.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberChangeModel {

    private String type;
    private String action;
    private Long MemberId;
    private String correlationId;

}
