/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacton.entities.cpqresponse;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author narvik.sanchez
 */
public class SelfServiceProductList {

    //public static Object getConfigJSON;
    
    @JsonAlias("steps")
    private List<SelfServiceProduct> postResponse;
    
    public SelfServiceProductList() {
        List<SelfServiceProduct> postResponse = new ArrayList<>();
    }

    public List<SelfServiceProduct> getConfigJSON() {
        return postResponse;
    }

    public void setConfigJSON(List<SelfServiceProduct> postResponse) {
        this.postResponse = postResponse;
    }
    
}
