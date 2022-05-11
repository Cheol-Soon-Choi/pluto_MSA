package com.ccs.filters;

import org.springframework.stereotype.Component;

@Component
public class FilterUtils {
    public static final String CORRELATION_ID = "ccs-correlation-id";
    public static final String AUTH_TOKEN     = "ccs-auth-token";
    public static final String PRE_FILTER_TYPE = "pre";
    public static final String POST_FILTER_TYPE = "post";
}
