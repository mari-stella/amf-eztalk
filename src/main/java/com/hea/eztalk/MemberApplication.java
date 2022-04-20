package com.hea.eztalk;
 
import java.util.Date;

import com.hea.eztalk.domain.Level;
import com.hea.eztalk.domain.Member;
import com.hea.eztalk.domain.Repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MemberApplication {

	static ApplicationContext applicationContext;
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(MemberApplication.class, args);
		Repository repository = applicationContext.getBean(Repository.class);


		//회원가입
		Member member =new Member();

		member.setMemberId("qwerty"); 
		member.setName("박연주");
		member.setLevel(Level.준회원);
		member.setRecentActivityDate(new Date()); 
		repository.save(member); 

		System.out.println("가입하셨습니다.");
		//중복오류발생용 동일가입 시도		 

		//중복가입체크
		if(member.check가입여부()==false){
			System.out.println("중복으로 가입실패");
		}else{

			//저장
			repository.save(member); 
			System.out.println("가입됐습니다.");

		}


			
		// Member talent = new Member();
		// talent.setRecentActivityDate(new Date());
		// talent.setLevel(Level.정회원);

		// repository.save(talent);

		// System.out.println(talent.check잠수() == false);
	}

}
