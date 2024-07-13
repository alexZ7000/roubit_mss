package com.maua.roubit.shared.helpers.external_interfaces;

import com.maua.roubit.shared.helpers.enums.HttpStatusCodeEnum;
import com.maua.roubit.shared.helpers.external_interfaces.ExternalInterface.IRequest;
import com.maua.roubit.shared.helpers.external_interfaces.ExternalInterface.IResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HttpModels {

    public static class HttpRequest implements IRequest {
        private final Map<String, Object> body;
        private final Map<String, Object> headers;
        private final Map<String, Object> queryParams;
        private Map<String, Object> data = new HashMap<>();

        public HttpRequest(Map<String, Object> body, Map<String, Object> headers, Map<String, Object> queryParams) {
            this.body = body != null ? body : new HashMap<>();
            this.headers = headers != null ? headers : new HashMap<>();
            this.queryParams = queryParams != null ? queryParams : new HashMap<>();
            Map<String, Object> dataJson = new HashMap<>();

            if (body != null) {
                dataJson.putAll(body);
                for (String key : body.keySet()) {
                    assert queryParams != null;
                    if (queryParams.containsKey(key) || Objects.requireNonNull(headers).containsKey(key)) {
                        System.out.println("Warning: body, queryParams, and/or headers have overlapping keys → " + key);
                    }
                }
            } else {
                assert queryParams != null;
                for (String key : queryParams.keySet()) {
                    assert headers != null;
                    if (headers.containsKey(key)) {
                        System.out.println("Warning: queryParams and headers have overlapping keys → " + key);
                    }
                }
            }

            assert headers != null;
            dataJson.putAll(headers);
            assert queryParams != null;
            dataJson.putAll(queryParams);
            this.data = dataJson;
        }

        @Override
        public Map<String, Object> getData() {
            return data;
        }

        @Override
        public String toString() {
            return "HttpRequest (body=" + body + ", headers=" + headers + ", queryParams=" + queryParams + ", data=" + data + ")";
        }
    }

    public static class HttpResponse implements IResponse {
        private final int statusCode;
        private final Map<String, Object> body;
        private final Map<String, Object> headers;
        private Map<String, Object> data = new HashMap<>();

        public HttpResponse(Integer statusCode, Map<String, Object> body, Map<String, Object> headers) {
            this.statusCode = statusCode != null ? statusCode : HttpStatusCodeEnum.OK.getValue();
            this.body = body != null ? body : new HashMap<>();
            this.headers = headers != null ? headers : new HashMap<>();

            Map<String, Object> dataJson = new HashMap<>();
            if (body != null) {
                dataJson.putAll(body);
            }

            assert headers != null;
            dataJson.putAll(headers);
            this.data = dataJson;
        }

        @Override
        public int getStatusCode() {
            return statusCode;
        }

        @Override
        public Map<String, Object> getData() {
            return data;
        }

        @Override
        public String toString() {
            return "HttpResponse (statusCode=" + statusCode + ", body=" + body + ", headers=" + headers + ")";
        }
    }
}

