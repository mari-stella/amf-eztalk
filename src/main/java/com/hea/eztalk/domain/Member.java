package com.hea.eztalk.domain;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;

import com.hea.eztalk.MemberApplication;
 

@Entity
public class Member {

 
    @Id
    String id;
    String name;
    String email;
    Level level;
    Active active; 
    Date recentActivityDate;

    @Embedded
    Address address;

    @OneToMany
    List<Community> joinCommunityList;

    public int joinMember(){ //회원가입

        if(check가입여부()){ 
            if(check이메일()){
                save();
                return 0;
            }
            else{
                System.out.println("오류2 : 동일 이메일 주소 존재함");
                return 2;
            }
        }
        else{
            //기존 가입내역 있음
            System.out.println("오류1 :  동일 주소로 가입됨");
            return 1;
        }
    }

    // 이메일 인증요청
    public void requestEmailAuth(){

        if(check이메일()==true){
            // 중복이메일 아니므로 인증메일 발송
            setActive(active.인증요청);
            System.out.println("인증메일을 발송했습니다. 메일을 확인해주세요.");
        }
        else{
            // 가입된 이메일 주소 있음. 발송안함
            System.out.println("이미 인증된 메일 주소입니다.");
        }

    }

    public void emailAuth() {

    }



    // 이메일 중복체크
    public boolean check이메일(){
        MemberRepository repository = MemberApplication.getApplicationContext().getBean(MemberRepository.class); //주입받기
        // Optional<Member> chk_mem = repository.findById(id); 
        Optional<Member> chk_email = repository.findByEmail(email); 

        // 이메일 중복체크
        if(chk_email.isPresent()){
            return false;
        }
        else{ 
            return true;
        }
    }


    // 회원가입 중복체크(동호수)
    public boolean check가입여부(){
        MemberRepository repository = MemberApplication.getApplicationContext().getBean(MemberRepository.class); //주입받기
        // Optional<Member> chk_mem = repository.findById(id); 
        Optional<Member> chk_addr = repository.findByAddress(address); 



        // 동호수 가입 체크
        if(chk_addr.isPresent()){
            return false;
        }
        else{ 
            return true;
        }

    }
 
	public void save(){ //회원정보 저장
		MemberRepository repository  = MemberApplication.getApplicationContext().getBean(MemberRepository.class);
		repository.save(this);
	}

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
    public Level getLevel() {
        return level;
    }
    public void setLevel(Level level) {
        this.level = level;
    }
    public Active getActive() {
        return active;
    }
    public void setActive(Active active) {
        this.active = active;
    }
    public Date getRecentActivityDate() {
        return recentActivityDate;
    }
    public void setRecentActivityDate(Date recentActivityDate) {
        this.recentActivityDate = recentActivityDate;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public List<Community> getJoinCommunityList() {
        return joinCommunityList;
    }
    public void setJoinCommunityList(List<Community> joinCommunityList) {
        this.joinCommunityList = joinCommunityList;
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
