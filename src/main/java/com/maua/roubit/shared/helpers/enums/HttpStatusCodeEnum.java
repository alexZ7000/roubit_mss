package com.maua.roubit.shared.helpers.enums;

import lombok.Getter;

@Getter
public enum HttpStatusCodeEnum {
    OK(200),
    CREATED(201),
    NO_CONTENT(204),
    REDIRECT(303),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    CONFLICT(409),
    INTERNAL_SERVER_ERROR(500),
    BAD_GATEWAY(502),
    SERVICE_UNAVAILABLE(503),
    GATEWAY_TIMEOUT(504);

    private final int value;

    HttpStatusCodeEnum(final int value) {
        this.value = value;
    }

}
