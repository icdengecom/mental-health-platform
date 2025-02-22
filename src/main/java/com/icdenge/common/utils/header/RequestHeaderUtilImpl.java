package com.icdenge.common.utils.header;

import jakarta.servlet.http.HttpServletRequest;
import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static com.icdenge.common.utils.header.RequestHeaderConstants.*;

@Slf4j
@Component
@RequiredArgsConstructor
class RequestHeaderUtilImpl implements RequestHeaderUtil {

    private final HttpServletRequest request;

    /**
     * JWT Token'ı Authorization Header'dan alır.
     */
    public Optional<String> getJwtToken() {
        return getHeader(AUTHORIZATION_HEADER)
            .filter(token -> token.startsWith(BEARER_PREFIX))
            .map(token -> token.substring(BEARER_PREFIX.length()));
    }

    /**
     * X-Request-ID header'ını döner. Eğer yoksa yeni bir UUID üretir.
     */
    public String getRequestId() {
        return getHeader(REQUEST_ID_HEADER).orElse(UUID.randomUUID().toString());
    }

    /**
     * X-Client-ID header'ını döner.
     */
    public Optional<String> getClientId() {
        return getHeader(CLIENT_ID_HEADER);
    }

    /**
     * Accept-Language header bilgisini döner.
     */
    public Optional<String> getAcceptLanguage() {
        return getHeader(ACCEPT_LANGUAGE_HEADER);
    }

    /**
     * Mevcut kullanıcının kimlik doğrulama bilgisini döner.
     */
    public Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }

    /**
     * Mevcut kullanıcının adını döner.
     */
    public Optional<String> getCurrentUsername() {
        return getAuthentication().map(Authentication::getName);
    }

    /**
     * Belirtilen header'ın değerini döner.
     */
    public Optional<String> getHeader(String headerName) {
        String headerValue = request.getHeader(headerName);
        return StringUtils.hasText(headerValue) ? Optional.of(headerValue) : Optional.empty();
    }
}
