package com.maua.roubit.shared.helpers.external_interfaces;

import com.maua.roubit.shared.helpers.external_interfaces.HttpModels.HttpResponse;
import com.maua.roubit.shared.helpers.external_interfaces.HttpModels.HttpRequest;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class LambdaHttp {

    public static class LambdaHttpResponse extends HttpResponse {
        public LambdaHttpResponse(final Object body, final Integer statusCode, final Map<String, Object> headers, final Map<String, Object> kwargs) {
            super(
                    statusCode != null ? statusCode : 200,
                    body != null ? HttpCodes.castToMap(body) : new HashMap<>(),
                    headers != null ? headers : new HashMap<>()
            );

            final Map<String, Object> _headers = this.getData();
            _headers.put("Access-Control-Allow-Origin", "*");

            if (!Boolean.FALSE.equals(kwargs.get("add_default_cors_headers"))) {
                _headers.put("Access-Control-Allow-Origin", "*");
            }
        }


        public Map<String, Object> toJSON() {
            final Map<String, Object> response = new HashMap<>();
            response.put("statusCode", this.getStatusCode());
            response.put("body", this.getData().get("body").toString());
            response.put("headers", this.getData().get("headers"));
            response.put("isBase64Encoded", false);
            return response;
        }

        @Override
        public String toString() {
            return "LambdaHttpResponse (statusCode=" + this.getStatusCode() + ", body=" + this.getData().get("body") + ", headers=" + this.getData().get("headers") + ")";
        }
    }

    public static class LambdaDefaultHTTP {
        private final String method;
        private final String path;
        private final String protocol;
        private final String sourceIp;
        private final String userAgent;

        public LambdaDefaultHTTP(final Map<String, Object> data) {
            this.method = data.getOrDefault("method", "").toString();
            this.path = data.getOrDefault("path", "").toString();
            this.protocol = data.getOrDefault("protocol", "").toString();
            this.sourceIp = data.getOrDefault("sourceIp", "").toString();
            this.userAgent = data.getOrDefault("userAgent", "").toString();
        }

        public boolean equals(final LambdaDefaultHTTP other) {
            return this.method.equals(other.method) &&
                    this.path.equals(other.path) &&
                    this.protocol.equals(other.protocol) &&
                    this.sourceIp.equals(other.sourceIp) &&
                    this.userAgent.equals(other.userAgent);
        }
    }

    @Getter
    public static class LambdaHttpRequest extends HttpRequest {
        private final String version;
        private final String rawPath;
        private final String rawQueryString;
        private final Map<String, Object> queryStringParameters;
        private final Map<String, Object> requestContext;
        private final LambdaDefaultHTTP http;

        public LambdaHttpRequest(final Map<String, Object> data) {
            super(safeCastToMap(data.get("body")), safeCastToMap(data.get("headers")), safeCastToMap(data.get("queryStringParameters")));
            this.version = data.get("version").toString();
            this.rawPath = data.get("rawPath").toString();
            this.rawQueryString = data.get("rawQueryString").toString();
            this.queryStringParameters = safeCastToMap(data.get("queryStringParameters"));
            this.requestContext = safeCastToMap(data.get("requestContext"));
            this.http = new LambdaDefaultHTTP(safeCastToMap(this.requestContext.get("external_interfaces")));
        }

        private static Map<String, Object> safeCastToMap(final Object obj) {
            if (obj instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) obj;
                return map;
            } else {
                return new HashMap<>();
            }
        }
    }

    public static class HttpResponseRedirect extends HttpResponse {
        public HttpResponseRedirect(final String location) {
            super(302, Map.of("Location", location), null);
        }
    }
}
