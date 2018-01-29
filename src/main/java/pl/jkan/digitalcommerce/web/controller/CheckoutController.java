package pl.jkan.digitalcommerce.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.application.api.ConfirmOrderCommand;
import pl.jkan.ecommerce.sales.application.api.ConfirmOrderHandler;
import pl.jkan.ecommerce.sales.application.api.VerifyPaymentCommand;
import pl.jkan.ecommerce.sales.application.api.VerifyPaymentHandler;
import pl.jkan.ecommerce.sales.readmodel.offer.OfferDto;
import pl.jkan.ecommerce.sales.readmodel.offer.OfferFinder;
import pl.jkan.ecommerce.sales.readmodel.readmodel.PaymentURLs;

import java.util.Map;

@RestController
public class CheckoutController {

    @Autowired
    private OfferFinder offerMaker;

    @Autowired
    ConfirmOrderHandler confirmOrderHandler;

    @Autowired
    PaymentURLs paymentURLs;

    @Autowired
    VerifyPaymentHandler verifyPaymentCommandHandler;

    @RequestMapping("/offer")
    @ResponseBody
    public OfferDto offer() {
        return offerMaker.currentOffer();
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    ResponseEntity checkout(@RequestBody Map<String, String> payload) {

        Identifier id = Identifier.generateUUID();
        confirmOrderHandler.handle(new ConfirmOrderCommand(id, payload.get("email")));

        return ResponseEntity.ok(paymentURLs.getPaymentUrl(id));
    }

    @RequestMapping(value = "/verify-payment", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity verifyPayment(@RequestBody MultiValueMap<String,String> formData) {

        Identifier id = Identifier.generateUUID();

        VerifyPaymentCommand c = new VerifyPaymentCommand(
                formData.get("p24_session_id").get(0).toString(),
                formData.get("p24_order_id").get(0).toString(),
                formData.get("p24_sign").get(0).toString()
        );

        verifyPaymentCommandHandler.handle(c);

        return ResponseEntity.ok("");
    }

}