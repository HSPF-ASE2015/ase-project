/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfulServiceObjects;

import java.io.Serializable;

/**
 *
 * @author Jeroen
 */
public class KursWS implements Serializable{
    
    private int ects;
    private String name;
    private String sprache;
    private String hochschule;
    
    private String errorKurs;
    
    public KursWS(){
    }
    
    public KursWS(int ects, String name, String sprache, String hochschule){

        this.ects = ects;
        this.name = name;
        this.sprache = sprache;
        this.hochschule = hochschule;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSprache() {
        return sprache;
    }

    public void setSprache(String sprache) {
        this.sprache = sprache;
    }
    
    public String getErrorKurs() {
        return errorKurs;
    }

    public void setErrorKurs(String errorKurs) {
        this.errorKurs = errorKurs;
    }
    
    public String getHochschule() {
        return hochschule;
    }

    public void setHochschule(String hochschule) {
        this.hochschule = hochschule;
    }
    
}
