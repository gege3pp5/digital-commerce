package pl.jkan.support.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Request {
    private String url;
    private String method;
    private Map<String, String> params;
    private Map<String, String> headers;

    private Request(String method, String url, Map<String, String> params, Map<String, String> headers) {
        this.url = url;
        this.method = method;
        this.params = params;
        this.headers = headers;
    }

    public static Request get(String url, Map<String, String> headers) {
        return new Request("GET", url, new HashMap<>(), headers);
    }

    public static Request post(String url, Map<String, String> params, Map<String, String> headers) {
        return new Request("POST", url, params, headers);
    }

    public String getUrl() {
        return url.toString();
    }

    public String getMethod() {
        return method.toString();
    }

    public Map<String, String> getParams() {
        return Collections.unmodifiableMap(params);
    }

    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }
}
