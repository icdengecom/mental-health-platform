package com.icdenge.common.utils.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class RequestHeaderConstants {

  static final String AUTHORIZATION_HEADER = "Authorization";
  static final String ACCEPT_LANGUAGE_HEADER = "Accept-Language";
  static final String BEARER_PREFIX = "Bearer ";
  static final String REQUEST_ID_HEADER = "X-Request-ID";
  static final String CLIENT_ID_HEADER = "X-Client-ID";

}
