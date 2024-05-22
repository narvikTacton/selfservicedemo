/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacton.entities.cpqresponse;

/**
 *
 * @author narvik.sanchez
 */
public class DomainElement extends SelfServiceProduct {
    String needDescription;
    String name;
    String description;
    String state;
    Boolean selected;
    
    //constructors
    public DomainElement (){
    }

    public DomainElement(String needDescription,String name, String description, String state, Boolean selected) {
        this.needDescription = needDescription;
        this.name = name;
        this.description = description;
        this.state = state;
        this.selected = selected;
    }
    
    //Setters

    public void setNeedDescription(String needDescription) {
        this.needDescription = needDescription;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
    
    //getters

    public String getNeedDescription() {
        return needDescription;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    public Boolean getSelected() {
        return selected;
    }
    
    @Override
    public String toString() {
        return "DomainElement{" +
                "needDescription='"+needDescription + '\'' +
                ",name='" + name + '\'' +
                ",description='" + description + '\'' +
                ",state='" + state + '\'' +
                ",selected=" + selected +
                '}';
    }
    
    
}
