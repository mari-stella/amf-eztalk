package com.hea.eztalk.domain;

import java.util.Date;
import java.util.Optional;

import javax.persistence.*;

import com.hea.eztalk.MemberApplication;
 

@Entity
public class Member {

    static long TEN_DAYS = (1000*60*60*24*10) ;
    
    String memberId;
    String name;
    Level level;
    boolean active;
    Date recentActivityDate;

    @Id @GeneratedValue
    Long id;

    public boolean check가입여부(){

        Repository repository = MemberApplication.getApplicationContext().getBean(Repository.class); //주입받기
        Optional<Member> chk_mem = repository.findByMemberId(memberId); 
        
        if(chk_mem.isPresent()){
            return false;
        }
        else{ 
            return true;
        }

    }

 //   http :8080/members


    public boolean check잠수(){ // 장기미접속자 처리용으루 쓰면 될듯
        Date today = new Date();
        if(today.before( new Date(getRecentActivityDate().getTime()+TEN_DAYS) ) ){
            return false;
        }
        else {
            return true;
        }
    }

    public String getName() {
        return name;
    }


    public String getMemberId() {
        return memberId;
    }


    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getRecentActivityDate() {
        return recentActivityDate;
    }

    public void setRecentActivityDate(Date recentActivityDate) {
        this.recentActivityDate = recentActivityDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}