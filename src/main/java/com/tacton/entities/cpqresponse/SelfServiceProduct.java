/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacton.entities.cpqresponse;

/**
 *
 * @author narvik.sanchez
 */
public class SelfServiceProduct{
    
    private String configState;
    private String numberOfUncommittedMandatoryFields;
    
    public SelfServiceProduct (){};

    public SelfServiceProduct(String configState, String numberOfUncommittedMandatoryFields) {
        this.configState = configState;
        this.numberOfUncommittedMandatoryFields = numberOfUncommittedMandatoryFields;
    }
    
    //Setters

    public void setConfigState(String configState) {
        this.configState = configState;
    }

    public void setNumberOfUncommittedMandatoryFields(String numberOfUncommittedMandatoryFields) {
        this.numberOfUncommittedMandatoryFields = numberOfUncommittedMandatoryFields;
    }
    
    //Getters

    public String getConfigState() {
        return configState;
    }

    public String getNumberOfUncommittedMandatoryFields() {
        return numberOfUncommittedMandatoryFields;
    }
    
    @Override
    public String toString() {
        return "SelfServiceProduct{" +
                "configState='" + configState + '\'' +
                ", numberOfUncommittedMandatoryFields='" + numberOfUncommittedMandatoryFields + '\'' +
                '}';
    }
}
