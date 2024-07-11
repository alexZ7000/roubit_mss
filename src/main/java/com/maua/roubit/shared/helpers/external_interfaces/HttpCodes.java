package com.maua.roubit.shared.helpers.external_interfaces;

import com.maua.roubit.shared.helpers.enums.HttpStatusCodeEnum;
import com.maua.roubit.shared.helpers.external_interfaces.HttpModels.HttpResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class HttpCodes {

    public static class OK extends HttpResponse {
        public OK(Object body) {
            super(HttpStatusCodeEnum.OK.getValue(), castToMap(body), null);
        }
    }

    public static class Created extends HttpResponse {
        public Created(Object body) {
            super(HttpStatusCodeEnum.CREATED.getValue(), castToMap(body), null);
        }
    }

    public static class NoContent extends HttpResponse {
        public NoContent() {
            super(HttpStatusCodeEnum.NO_CONTENT.getValue(), null, null);
        }
    }

    public static class BadRequest extends HttpResponse {
        public BadRequest(Object body) {
            super(HttpStatusCodeEnum.BAD_REQUEST.getValue(), castToMap(body), null);
        }
    }

    public static class InternalServerError extends HttpResponse {
        public InternalServerError(Object body) {
            super(HttpStatusCodeEnum.INTERNAL_SERVER_ERROR.getValue(), castToMap(body), null);
        }
    }

    public static class NotFound extends HttpResponse {
        public NotFound(Object body) {
            super(HttpStatusCodeEnum.NOT_FOUND.getValue(), castToMap(body), null);
        }
    }

    public static class Conflict extends HttpResponse {
        public Conflict(Object body) {
            super(HttpStatusCodeEnum.CONFLICT.getValue(), castToMap(body), null);
        }
    }

    @Setter
    @Getter
    public static class RedirectResponse extends HttpResponse {
        private Map<String, Object> location;

        public RedirectResponse(Map<String, Object> location) {
            super(HttpStatusCodeEnum.REDIRECT.getValue(), null, null);
            this.location = location;
        }

    }

    public static class Forbidden extends HttpResponse {
        public Forbidden(Object body) {
            super(HttpStatusCodeEnum.FORBIDDEN.getValue(), castToMap(body), null);
        }
    }

    protected static Map<String, Object> castToMap(Object body) {
        if (body instanceof Map<?, ?> rawMap) {
            Map<String, Object> resultMap = new HashMap<>();
            for (Map.Entry<?, ?> entry : rawMap.entrySet()) {
                if (entry.getKey() instanceof String) {
                    resultMap.put((String) entry.getKey(), entry.getValue());
                } else {
                    throw new IllegalArgumentException("Invalid map key: " + entry.getKey());
                }
            }
            return resultMap;
        }
        return new HashMap<>();
    }
}
