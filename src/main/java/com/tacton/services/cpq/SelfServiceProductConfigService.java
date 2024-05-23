/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacton.services.cpq;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tacton.entities.cpqresponse.SelfServiceProduct;
import com.tacton.entities.cpqresponse.SelfServiceProductList;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
//import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Properties;
import java.util.*;
import java.util.Base64;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.jsoup.select.Elements;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import static org.apache.tomcat.jni.Shm.buffer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

/**
 *
 * @author narvik.sanchez
 */
@Service
public class SelfServiceProductConfigService {

    @Value("${cpq_user}")
    private String cpq_user;

    @Value("${cpq_pass}")
    private String cpq_pass;

    @Value("${cart_limit}")
    private String cartLimit;

    private final Logger LOGGER = LoggerFactory.getLogger(SelfServiceProductConfigService.class);

    private static String CART_ATTRIBUTES_URI = "/cart";

    private static String CART_ITEMS_URI = "/cart/items";

    private static String CPQ_GETSHOPPINGCARTS_URI = "/shoppingcart/list";
    
    private static String CPQ_INSTANCE = "https://p4fbd076-edu.earlyaccess.tactoncpq.com/!tickets~T-00000001";
    
    private static String API_URL = "/self-service-api-v1.2";

    private static String API_KEY_PARAM = "?_key=dVeAIWh13m5Gq";
    
    private static String API_KEY_PWD = "dVeAIWh13m5Gq";
    
    public SelfServiceProductConfigService() {
    }
    
    public String getProductConfigSelfService (String catalog, String product) throws IOException{
        HttpHeaders headers = getAuthenticationHeaders(cpq_user, cpq_pass);

        HttpEntity<String> httpEntity = new HttpEntity<>("body", headers);
        RestTemplate template = new RestTemplate();

        String url = CPQ_INSTANCE+API_URL+"/config/start/"+catalog+"/"+product+API_KEY_PARAM;
        LOGGER.info("getSelfService URL: "+url);
        ResponseEntity<String> selfServiceConfigurator;
        try{
            selfServiceConfigurator = template.exchange(
                    url,
                    HttpMethod.POST,
                    httpEntity,
                    String.class);
        }catch(HttpClientErrorException exception ) {
            LOGGER.error("ERROR", exception);
            return null;
        }
        LOGGER.info("Response WS: "+selfServiceConfigurator);
        
        SelfServiceProduct setAttrs = setSelfServiceProductAttributesFromResponse(selfServiceConfigurator);
        LOGGER.info("list:"+setAttrs);
        
        return selfServiceConfigurator.getBody();
    }
    
    public String getSizingSelfService (String catalog, String product) throws IOException{
        HttpHeaders headers = getAuthenticationHeaders(cpq_user, cpq_pass);
        //API call to start configuration and get configState
        SelfServiceProductConfigService cssApiCallService=new SelfServiceProductConfigService();
        cssApiCallService.getProductConfigSelfService(catalog, product);
        //handling the String response
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode;
        jsonNode=mapper.readTree(cssApiCallService.getProductConfigSelfService(catalog, product));
        JsonNode configState = jsonNode.get("configState"); 
        
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> selfServiceSizing;
        String step="step_7030eb2912c8457eafd457a57fcce27c";//TO-DO: make it dynamic
        String strConfigState = configState.asText();
        String encodedConfigState = URLEncoder.encode(strConfigState, "UTF-8");
        //byte[] bytes = strConfigState.getBytes(StandardCharsets.UTF_8);
        //String encodedConfigState = new String(Base64.getUrlEncoder().encode(bytes));
        
        //byte[] encodedConfigStateByte = Base64.getEncoder().encode(strConfigState.getBytes(StandardCharsets.UTF_8));
        //String encodedConfigState = new String(encodedConfigStateByte);
        String url = CPQ_INSTANCE+API_URL+"/config/step"+API_KEY_PARAM+"&step="+step+"&configState="+encodedConfigState;
        LOGGER.info("url configState: "+url);
        HttpEntity<String> httpEntity = new HttpEntity<>("body", headers);
        
        try{
            selfServiceSizing = template.exchange(
                    url,
                    HttpMethod.POST,
                    httpEntity,
                    String.class);
        }catch(RestClientException exception ) {
            LOGGER.error("ERROR", exception);
            return null;
        }
        LOGGER.info("Response Sizing WS: "+selfServiceSizing);
        
        
        return selfServiceSizing.getBody();
    }
    
        private HttpHeaders getAuthenticationHeaders(String username, String password) {
        String auth = username + ":" + password;
        //byte[] encodedAuth = Base64.getEncoder(auth.getBytes(StandardCharsets.UTF_8));
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        //byte[] encodedAuth = Base64.encodeBase64(
                //auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",authHeader);
        return headers;
    }
        
        private SelfServiceProduct setSelfServiceProductAttributesFromResponse(ResponseEntity<String> response) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            SelfServiceProduct selfServiceProduct = new SelfServiceProduct();
            JsonNode jsonNode;
            jsonNode = mapper.readTree(response.getBody());
            
            //Setters
            try{
            selfServiceProduct.setConfigState(jsonNode.findValue("configState").asText());
            selfServiceProduct.setNumberOfUncommittedMandatoryFields(jsonNode.findValue("numberOfUncommittedMandatoryFields").asText());
            
            LOGGER.info("selfServiceProduct: "+selfServiceProduct.toString());
            }catch(Exception e){
                LOGGER.error("Error setting values",e);
            }
    
            return selfServiceProduct;
    }
 

    
}
