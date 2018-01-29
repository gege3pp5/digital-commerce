package pl.jkan.support.http;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import java.util.HashMap;
import java.util.Map;

public class NativeHttpClient implements HttpClient {
    @Override
    public Response send(Request request) throws IOException {

        if (request.getMethod().toUpperCase().equals("GET")) {
            return sendGet(request.getUrl(), request.getParams(), request.getHeaders());
        }

        if (request.getMethod().toUpperCase().equals("POST")) {
            return sendPost(request.getUrl(), request.getParams(), request.getHeaders());
        }

        throw new RuntimeException(String.format("Method %s not supported", request.getMethod()));
    }

    private Response sendPost(String url, Map<String, String> params, Map<String, String> headers) throws IOException{
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String parameters = params.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .reduce((s, acc) -> acc + "&" + s)
                .orElse("")
        ;

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(parameters);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        return new Response(con.getResponseCode(), new HashMap<>(), readAll(in));
    }

    private Response sendGet(String url, Map<String, String> params, Map<String, String> headers) throws IOException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String content = readAll(in);
        in.close();

        return new Response(con.getResponseCode(), new HashMap<>(), content);
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] chars = new char[4*1024];
        int len;
        while((len = rd.read(chars))>=0) {
            sb.append(chars, 0, len);
        }

        return sb.toString();
    }
}
