package com.hea.eztalk;
 
import java.util.Date;

import com.hea.eztalk.domain.Active;
import com.hea.eztalk.domain.Address;
import com.hea.eztalk.domain.Level;
import com.hea.eztalk.domain.Member;
import com.hea.eztalk.domain.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class MemberApplication {

	static ApplicationContext applicationContext;
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(MemberApplication.class, args);
		MemberRepository repository = applicationContext.getBean(MemberRepository.class);


		//회원가입 ui...
		Member member =new Member();

		member.setId("qwerty"); 
		member.setName("박연주");
		member.setEmail("qwerty@naver.com");

		Address address = new Address();
		address.setAptName("대우아파트");
		address.setZipcode("12345");
		address.setDong("101");
		address.setHo("102");		
		member.setAddress(address);
		// member.setAddress(new Address("대우아파트","16868","206","702"));
		member.setLevel(Level.준회원);
		member.setActive(Active.이메일인증필요);

		int result = member.joinMember();

		if(result==0)  System.out.println("회원가입되었습니다.");
		else if(result==1)  System.out.println("가입실패. 동일주소가 가입되어있습니다.");
		else if(result==2) System.out.println("가입실패. 이메일주소가 중복됩니다.");
			
		// Member talent = new Member();
		// talent.setRecentActivityDate(new Date());
		// talent.setLevel(Level.정회원);

		// repository.save(talent);

		// System.out.println(talent.check잠수() == false);
	}



	@Autowired
	MemberRepository memberRepository;
	
	@RequestMapping(method=RequestMethod.PUT, path="/members/{id}/mailAuth")
	public void join(@PathVariable("id") String id){
		memberRepository.findById(id).ifPresent(member ->{
			member.emailAuth();
			member.save();
		});
	}


}
