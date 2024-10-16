package com.nta.cms.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JwtConstant {
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_TOKEN_PREFIX = "Bearer ";

    public static final String JWT_SECRET = "Weather Forecast";
    public static final long JWT_EXPIRATION = (long) 1000 * 60 * 60 * 24;
}
