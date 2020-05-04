package com.foryou.security.core.properties;

public interface SecurityConstants {

    public static final String VALIDATE_CODE_IMAGE_PARAM_KEY = "imageCode";

    public static final String VALIDATE_CODE_SMS_PARAM_KEY = "smsCode";

    public static final String VALIDATE_CODE_IMAGE_AUTHENTICATION_URL = "/authentication/form";

    public static final String VALIDATE_CODE_SMS_AUTHENTICATION_URL = "/authentication/sms";

    public static final String AUTHENTICATION_FAILURE_URL = "/authentication/failure";

    public static final String AUTHENTICATION_REQUIRE_URL = "/authentication/require";

    public static final String VALIDATE_CODE_URL = "/code/*";

}
