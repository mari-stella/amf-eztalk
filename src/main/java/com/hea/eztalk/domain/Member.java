package com.hea.eztalk.domain;

import java.util.Date;
import java.util.List;
import java.util.Optional; 
import javax.persistence.*;
import com.hea.eztalk.MemberApplication;


@Entity
public class Member {

    
    @Id
    String id; // 사용자계정
    String name; // 사용자명
    String email; // 이메일

    @Embedded
    Address address; // 주소 (아파트명,우편번호,동,호수)

    Level level; // 준회원, 정회원
    Active active;  // 이메일인증필요, 이메일인증요청상태, 이메일인증완료

    @OneToMany
    List<Community> joinCommunityList; // 가입된 커뮤니티 목록 조회용

    Date recentActivityDate; // 최근접속일자
 

    // 회원가입 절차 
    // 1. 중복가입여부(주소) 확인
    // 2. 이메일 중복 체크
    // 3. 회원정보 입력
    // 4. 회원가입
    // 5. 등록된 이메일로 인증메일 자동 발송



    // 1. 중복가입여부(주소) 확인
    public boolean duplicateMemberCheck(){
        MemberRepository repository = MemberApplication.getApplicationContext().getBean(MemberRepository.class); //주입받기 
        Optional<Member> chk_addr = repository.findByAddress(address); 

        // 주소 중복체크
        if(chk_addr.isPresent()){
            // 가입된 주소가 있음
            return false;
        }
        else{ 
            // 가입된 주소가 없으므로 통과~
            return true;
        }

    }
    // 2. 이메일 중복 체크
    public boolean duplicateEmailCheck(){
        MemberRepository repository = MemberApplication.getApplicationContext().getBean(MemberRepository.class); //주입받기
        Optional<Member> chk_email = repository.findByEmail(email); 

        // 이메일 중복체크
        if(chk_email.isPresent()){
            // 가입된 이멜주소가 있다!! 
            return false;
        }
        else{ 
            // 가입된 이멜 주소 없으니까 통과~
            return true;
        }

    }

    // 3. 회원정보 입력
    // 4. 회원가입
    
    public void joinMember(){ 
        
        // 가입시 기본 부여 상태 : 준회원, 이메일인증필요
		setLevel(Level.준회원);
		setActive(Active.이메일인증필요);
        // 회원가입
        save();

    } 

    // 5. 등록된 이메일로 인증메일 자동 발송   
    @PostPersist //insert method가 호출된 이후
    public void requestEmailCredential(){

        EmailCertificationRequested emailCertificationRequested = new EmailCertificationRequested();
        emailCertificationRequested.setId(getId());
        emailCertificationRequested.setName(getName());
        emailCertificationRequested.setAddress(getAddress());
        emailCertificationRequested.setEmail(getEmail());

        // 메일발송 ( 발송시 인증링크 전달할까함... )
        emailCertificationRequested.publish();


    }

    // Member 저장
	public void save(){ 
		MemberRepository repository  = MemberApplication.getApplicationContext().getBean(MemberRepository.class);
		repository.save(this);
	}


    //2차인증
    public void publishSecondaryCredentialEnteredEvent(){

        //2차인증 정보 : ID, 이름, 주소
        SecondaryCredentialEntered secondaryCredentialEntered = new SecondaryCredentialEntered();
        secondaryCredentialEntered.setId(getId());
        secondaryCredentialEntered.setName(getName());
        secondaryCredentialEntered.setAddress(getAddress());

        // 2차인증 요청 -> 커뮤니티 ( 이부분은 어떻게 구현할지? ~_~ 일단킵~)
        secondaryCredentialEntered.publish();
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

        // this.id = UUID.randomUUID().toString();

}
