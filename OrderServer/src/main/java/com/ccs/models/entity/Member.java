package com.ccs.models.entity;

import com.ccs.models.constant.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

    private Long id;
    private String name;
    private Role role;

}
