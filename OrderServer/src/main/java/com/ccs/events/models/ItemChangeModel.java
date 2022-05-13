package com.ccs.events.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemChangeModel {

    private String type;
    private String action;
    private Long ItemId;
    private String correlationId;

}
