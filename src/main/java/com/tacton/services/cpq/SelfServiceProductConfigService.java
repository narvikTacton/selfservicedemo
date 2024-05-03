/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacton.services.cpq;

import java.nio.charset.StandardCharsets;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author narvik.sanchez
 */
@Service
public class SelfServiceProductConfigService {

    @Value("${customer_self_service_api_key}")
    private String api_key;

    @Value("${customer_self_service_api_url}")
    private String api_url;

    @Value("${cpq_api_url}")
    private String cpq_api_url;

    @Value("${cpq_user}")
    private String cpq_user;

    @Value("${cpq_pass}")
    private String cpq_pass;

    @Value("${cart_limit}")
    private String cartLimit;

    private final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    private static String CART_ATTRIBUTES_URI = "/cart";

    private static String CART_ITEMS_URI = "/cart/items";

    //private static String PROPOSAL_DRAFT_URI = "/proposal/draft";

    //private static String REQUEST_FIRM_PROPOSAL_URI = "/proposal/firm-requests";

    private static String CPQ_GETSHOPPINGCARTS_URI = "/shoppingcart/list";

    private static String API_KEY_PARAM = "?_key=dVeAIWh13m5Gq";

    //private static String EXTERNAL_ID_PARAM = "&_externalId={external_id}";

    //private static String ACCOUNT_PARAM = "&account={account}";

    //private static String CURRENCY_PARAM = "&currency={currency}";

    //private static String INSTALLATION_COUNTRY_PARAM = "&installationCountry={installationCountry}";

    //private static String REFERENCE_EXTERNAL_USER_PARAM = "&referenceExternalUser={referenceExternalUser}";

    //private static String CART_LIMIT = "&limit=15";

    //static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
    
    public ResponseEntity<String> getSelfService (String catalog, String product){
        HttpHeaders headers = getAuthenticationHeaders(cpq_user, cpq_pass);

        HttpEntity<String> httpEntity = new HttpEntity<>("body", headers);
        RestTemplate template = new RestTemplate();

        String url = api_url + "/config/start/"+catalog+product+"&_key="+API_KEY_PARAM;
        ResponseEntity<String> response;
        try{
            response = template.exchange(
                    url,
                    HttpMethod.POST,
                    httpEntity,
                    String.class);
        }catch(HttpClientErrorException exception ) {
            LOGGER.warn("ERROR", exception);
            return null;
        }
        return response;
    }
        private HttpHeaders getAuthenticationHeaders(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",authHeader);
        return headers;
    }
 

    
}
