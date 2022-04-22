package com.hea.eztalk.domain;

import java.util.UUID;

public class EmailCertificationRequested {
    
    String id;
    String name;
    String email;
    Address address;
    String certString; //인증키

    public void publish() {
        // 메일발송...링크만들자...
    }




    // getter  // setter
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getCertString() {
        return certString;
    }
    public void setCertString() {    
        this.certString = UUID.randomUUID().toString();
    }


    

}
