package pl.jkan.support.http;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NativeHttpClientTest {

    @Test
    public void itAllowSendGetRequest() throws IOException {
        HttpClient http = new NativeHttpClient();
        Request r = Request.get("https://postman-echo.com/get?foo=bar", new HashMap<>());

        Response resp = http.send(r);
        Assert.assertEquals(resp.getStatus(), 200);
        Assert.assertTrue("Contains valid content", resp.getBody().contains("\"foo\":\"bar\""));
    }

    @Test
    public void itAllowSendPostRequest() throws IOException {
        HttpClient http = new NativeHttpClient();
        Map<String, String> parmas = new HashMap<>();
        parmas.put("param_1", "value_1");

        Request r = Request.post("https://postman-echo.com/post?foo=bar", parmas, new HashMap<>());

        Response resp = http.send(r);
        Assert.assertEquals(resp.getStatus(), 200);
        Assert.assertTrue("Contains valid content", resp.getBody().contains("\"param_1\":\"value_1\""));
    }
}
