package com.hea.eztalk.domain;

import javax.persistence.Entity;

public class Community {

    //가입된 커뮤니티 목록용..
 
    String communityId;
    String communityName;
    
    public String getCommunityId() {
        return communityId;
    }
    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }
    public String getCommunityName() {
        return communityName;
    }
    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
     

    

}
