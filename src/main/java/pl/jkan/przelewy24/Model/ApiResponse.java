package pl.jkan.przelewy24.Model;

import java.util.Map;

public class ApiResponse {
    private Map<String, String> params;

    public ApiResponse(Map<String, String> params) {
        this.params = params;
    }

    public String getValue(String key) {
        return params.get(key);
    }

    public Boolean containsKey(String key) {
        return params.containsKey(key);
    }
}
