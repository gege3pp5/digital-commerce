package pl.jkan.support.http;

import java.io.IOException;

public interface HttpClient {
    public Response send(Request request) throws IOException;
}
