package pl.jkan.przelewy24;

import pl.jkan.przelewy24.Model.ApiResponse;
import pl.jkan.przelewy24.Model.RegisterPaymentData;
import pl.jkan.przelewy24.Model.VerifyPaymentData;
import pl.jkan.przelewy24.hash.Md5;
import pl.jkan.support.http.HttpClient;
import pl.jkan.support.http.Request;
import pl.jkan.support.http.Response;

import java.io.IOException;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Przelewy24Api {
    private final String liveHost = "https://secure.przelewy24.pl";
    private final String testHost = "https://sandbox.przelewy24.pl";

    private Przelewy24Properties properties;
    private HttpClient httpClient;
    private String endpoint;

    public Przelewy24Api(Przelewy24Properties properties, HttpClient httpClient) {
        this.properties = properties;
        this.httpClient = httpClient;

        if (properties.getTestEnv()) {
            endpoint = testHost;
        } else {
            endpoint = liveHost;
        }
    }

    public ApiResponse testConnection() throws InvalidRequestException {

        try {
            ApiResponse apiResp = callApi(testConnectionRequest());

            return apiResp;
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new InvalidRequestException(e.getMessage());
        }
    }

    public ApiResponse registerPayment(RegisterPaymentData data) throws InvalidRequestException {

        try {
            return callApi(registerPaymentRequest(data));
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new InvalidRequestException(e.getMessage());
        }
    }

    public ApiResponse verifyPayment(VerifyPaymentData data) throws InvalidRequestException {
        try {
            ApiResponse apiResp = callApi(verifyPaymentRequest(data));

            return apiResp;
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new InvalidRequestException(e.getMessage());
        }
    }

    public String getPaymentUrl(String token) {
        return String.format("%s/%s/%s", endpoint, "trnRequest", token);
    }

    private ApiResponse callApi(Request request) throws InvalidRequestException, IOException {
        Response r = httpClient.send(request);
        ApiResponse apiResp = mapResponse(r);

        if (r.getStatus() != 200) {
            throw new InvalidRequestException("Invalid request");
        }

        if (apiResp.containsKey("error") && !apiResp.getValue("error").equals("0")) {
            throw new InvalidRequestException(apiResp.getValue("error"));
        }

        return apiResp;
    }

    private Request verifyPaymentRequest(VerifyPaymentData data) throws NoSuchAlgorithmException {
        String crc = generateMd5(
                String.format(
                        "%s|%s|%s|%s|%s",
                        data.getSessionId(),
                        data.getOrderId(),
                        data.getAmount(),
                        data.getCurrency(),
                        properties.getCrc()
                )
        );

        Map<String, String> params = new HashMap<>();
        params.put("p24_session_id", data.getSessionId());
        params.put("p24_order_id", data.getOrderId());
        params.put("p24_amount", data.getAmount().toString());
        params.put("p24_currency", data.getCurrency());
        params.put("p24_sign", crc);

        return Request.post(
                String.format("%s/%s", endpoint, "trnVerify"),
                params,
                new HashMap<>()
        );
    }

    private Request registerPaymentRequest(RegisterPaymentData data) throws NoSuchAlgorithmException {
        String crc = generateMd5(
            String.format(
                "%s|%s|%s|%s|%s",
                data.getSessionId(),
                properties.getPosId(),
                data.getAmount(),
                data.getCurrency(),
                properties.getCrc()
            )
        );

        Map<String, String> params = new HashMap<>();
        params.put("p24_merchant_id", properties.getMerchantId());
        params.put("p24_pos_id", properties.getPosId());
        params.put("p24_session_id", data.getSessionId());
        params.put("p24_amount", data.getAmount().toString());
        params.put("p24_currency", data.getCurrency());
        params.put("p24_description", data.getDescription());
        params.put("p24_email", data.getEmail());
        params.put("p24_country", data.getCountry());
        params.put("p24_url_return", data.getUrlReturn());
        params.put("p24_api_version", data.getApiVersion());
        params.put("p24_url_status", data.getStatusUrl());
        params.put("p24_sign", crc);

        return Request.post(
                String.format("%s/%s", endpoint, "trnRegister"),
                params,
                new HashMap<>()
        );
    }

    private Request testConnectionRequest() throws NoSuchAlgorithmException {
        String crc = generateMd5(String.format("%s|%s", properties.getPosId(), properties.getCrc()));

        Map<String, String> params = new HashMap<>();
        params.put("p24_merchant_id", properties.getMerchantId());
        params.put("p24_pos_id", properties.getPosId());
        params.put("p24_sign", crc);

        return Request.post(
            String.format("%s/%s", endpoint, "testConnection"),
            params,
            new HashMap<>()
        );
    }

    private String generateMd5(String input) throws NoSuchAlgorithmException {
        return Md5.encode(input);
    }

    private ApiResponse mapResponse(Response r) {
        Map<String, String> responseData = new HashMap<>();
        Arrays.stream(r.getBody().split("&"))
                .filter(s -> s.contains("="))
                .map(s -> s.split("="))
                .forEach(pair -> responseData.put(pair[0], pair[1]))
        ;

        return new ApiResponse(responseData);
    }
}
