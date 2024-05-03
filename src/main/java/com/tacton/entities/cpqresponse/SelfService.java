/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacton.entities.cpqresponse;

/**
 *
 * @author narvik.sanchez
 */
public class SelfService{
    private String productId;
    private String name;
    private String key;
    private String proposalTemplate;
    private String productPriceModel;
    private String role;
    private String organization;

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setProposalTemplate(String proposalTemplate) {
        this.proposalTemplate = proposalTemplate;
    }

    public void setProductPriceModel(String productPriceModel) {
        this.productPriceModel = productPriceModel;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getProposalTemplate() {
        return proposalTemplate;
    }

    public String getProductPriceModel() {
        return productPriceModel;
    }

    public String getRole() {
        return role;
    }

    public String getOrganization() {
        return organization;
    }
    
    @Override
    public String toString() {
        return "SelfService{" +
                "id='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", proposalTemplate='" + proposalTemplate + '\'' +
                ", productPriceModel='" + productPriceModel + '\'' +
                ", role='" + role + '\'' +
                ", organization'" + organization + '\'' +
                '}';
    }
}
