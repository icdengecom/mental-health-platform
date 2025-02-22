package com.icdenge.common.utils.header;

import org.springframework.security.core.Authentication;

import java.util.Map;
import java.util.Optional;

public interface RequestHeaderUtil {

    Optional<String> getJwtToken();

    String getRequestId();

    Optional<String> getClientId();

    Optional<Authentication> getAuthentication();

    Optional<String> getCurrentUsername();

    Optional<String> getHeader(String headerName);
}