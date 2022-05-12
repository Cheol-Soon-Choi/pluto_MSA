package com.ccs.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UserContext {
    public static final String CORRELATION_ID = "ccs-correlation-id";
    public static final String AUTH_TOKEN = "ccs-auth-token";
    public static final String USER_ID = "ccs-user-id";
    public static final String ORG_ID = "ccs-org-id";

    private String correlationId = "";
    private String authToken = "";
    private String userId = "";
    private String orgId = "";

}