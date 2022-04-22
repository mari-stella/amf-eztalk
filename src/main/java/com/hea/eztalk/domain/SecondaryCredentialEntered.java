package com.hea.eztalk.domain;

public class SecondaryCredentialEntered{
 
    String id;
    String name;
    Address address;

    public void publish() {
        // 이벤트 발행
        // 
    }  



    // getter // setter
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    




//     {"eventType":"PetReserved","timestamp":1650602790422,"id":1,"appearance":0,"name":"yo","type":"Cat","energy":0}
// {"eventType":"PetUpdated","timestamp":1650602852447,"id":1,"appearance":0,"name":"yo","type":"Cat","energy":1}
    
}
