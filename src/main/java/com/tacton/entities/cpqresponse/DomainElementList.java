/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacton.entities.cpqresponse;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author narvik.sanchez
 */
public class DomainElementList {
    private List<DomainElement> domainElements;
    
    public DomainElementList (){
        List<DomainElement> domainElements = new ArrayList<>();
    }

    public DomainElementList(List<DomainElement> domainElements) {
        this.domainElements = domainElements;
    }
    
    //Setters

    public void setDomainElements(List<DomainElement> domainElements) {
        this.domainElements = domainElements;
    }
    
    //Getters

    public List<DomainElement> getDomainElements() {
        return domainElements;
    }
    
    
}
