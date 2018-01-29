package pl.jkan.przelewy24;

import java.util.HashMap;
import java.util.Map;

public class RequestRegistry {
    public static Map<String,String> expectedRegisterPaymentParams() {
        Map<String, String> params = new HashMap<>();
        params.put("p24_merchant_id", "1234");
        params.put("p24_pos_id", "5678");
        params.put("p24_api_version", "3.2");
        params.put("p24_session_id", "sesion_id");
        params.put("p24_amount", "12300");
        params.put("p24_currency", "PLN");
        params.put("p24_description", "");
        params.put("p24_email", "kanclerj@uek.krakow.pl");
        params.put("p24_country", "PL");
        params.put("p24_url_return", "");
        params.put("p24_url_status", "");
        params.put("p24_sign", "f0f9253a5c2ed665aa37c415f9163f63");


        return params;
    }

    public static Map<String, String> expectedVerifyPaymentParams() {
        Map<String, String> params = new HashMap<>();
        params.put("p24_session_id", "sesion_id");
        params.put("p24_order_id", "order_id");
        params.put("p24_amount", "12300");
        params.put("p24_currency", "PLN");
        params.put("p24_sign", "ca4f25cb6ff85216636fbea952e0d5c1");

        return params;
    }


    public static Map<String, String> expectedTestConnectionParams() {
        Map<String, String> params = new HashMap<>();
        params.put("p24_sign", "b143416931ca355d8f6d2bdfffbd553b");
        params.put("p24_merchant_id", "1234");
        params.put("p24_pos_id", "5678");

        return params;
    }
}
