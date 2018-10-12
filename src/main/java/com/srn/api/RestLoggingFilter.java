package com.srn.api;

import org.springframework.web.filter.CommonsRequestLoggingFilter;

public class RestLoggingFilter extends CommonsRequestLoggingFilter {

    public RestLoggingFilter() {
        super.setIncludeQueryString(true);
        super.setIncludePayload(true);
        super.setIncludeHeaders(true);
        super.setIncludeClientInfo(true);
        super.setMaxPayloadLength(10000);
    }
}