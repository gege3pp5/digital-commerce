package pl.jkan.support.http;

public class SpyHttp implements HttpClient {
    public Request lastRequest;
    private Response response;

    public SpyHttp(Response response) {
        this.response = response;
    }

    @Override
    public Response send(Request request) {
        lastRequest = request;
        return response;
    }
}
