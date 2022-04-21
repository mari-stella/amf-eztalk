package com.hea.eztalk.domain;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;

import com.hea.eztalk.MemberApplication;
 

@Entity
public class Member {


    String memberId;
    String name;
    Level level;
    boolean active;
    Date recentActivityDate;

    @Embedded
    Address address;

    @OneToMany
    List<Community> joinCommunityList;

    @Id @GeneratedValue
    Long id;

    public boolean check가입여부(){

        MemberRepository repository = MemberApplication.getApplicationContext().getBean(MemberRepository.class); //주입받기
        Optional<Member> chk_mem = repository.findByMemberId(memberId); 
        
        if(chk_mem.isPresent()){
            return false;
        }
        else{ 
            return true;
        }

    }
 
	public void save(){
		MemberRepository repository = MemberApplication.getApplicationContext().getBean(MemberRepository.class);
		repository.save(this);
	}


    public List<Community> getJoinCommunityList() {
        return joinCommunityList;
    }

    public void setJoinCommunityList(List<Community> joinCommunityList) {
        this.joinCommunityList = joinCommunityList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getMemberId() {
        return memberId;
    }


    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }


    public String getName() {
        return name;
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

    // static long TEN_DAYS = (1000*60*60*24*10) ;
 //   http :8080/members


    // public boolean check잠수(){ // 장기미접속자 처리용으루 쓰면 될듯
    //     Date today = new Date();
    //     if(today.before( new Date(getRecentActivityDate().getTime()+TEN_DAYS) ) ){
    //         return false;
    //     }
    //     else {
    //         return true;
    //     }
    // }
}
