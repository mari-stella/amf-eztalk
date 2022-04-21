package com.hea.eztalk.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Community {

    //가입된 커뮤니티 목록용..
    @Id 
    Long commId;
    String commName;
    
    public Long getCommId() {
        return commId;
    }
    public void setCommId(Long commId) {
        this.commId = commId;
    }
    public String getCommName() {
        return commName;
    }
    public void setCommName(String commName) {
        this.commName = commName;
    }

    

}
