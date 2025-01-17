package com.hea.eztalk.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    
	String aptName;    
    String zipcode;
	String dong;
    String ho;
    
    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public String getAptName() {
        return aptName;
    }
    public void setAptName(String aptName) {
        this.aptName = aptName;
    }
    public String getDong() {
        return dong;
    }
    public void setDong(String dong) {
        this.dong = dong;
    }
    public String getHo() {
        return ho;
    }
    public void setHo(String ho) {
        this.ho = ho;
    }

}
