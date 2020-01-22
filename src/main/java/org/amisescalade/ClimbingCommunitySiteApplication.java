package org.amisescalade;

import java.util.Date;

import org.amisescalade.dao.UserCategoryRepository;
import org.amisescalade.dao.UserRepository;
import org.amisescalade.entity.User;
import org.amisescalade.entity.UserCategory;
import org.amisescalade.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ClimbingCommunitySiteApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserCategoryRepository userCategoryRepository;
	
	@Autowired
	private IUserService iUserService;

	public static void main(String[] args) {
		
		SpringApplication.run(ClimbingCommunitySiteApplication.class, args);	
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		// insert data
		
		UserCategory uc1 = userCategoryRepository.save(new UserCategory(new Date(), "grimpeur"));
		
		
		// method execution
		
		User uV1 = iUserService.register(new User(new Date(), "nicolas", "desdevises", "nico", "123", uc1));
		
		System.out.println("\n >>> : "+uV1.toString()+"\n");
		
		uV1.setFirstname("James");
		uV1.setLastname("Bond");
		
		User uV2 = iUserService.edit(uV1);
		
		System.out.println("\n >>> : "+uV2.toString()+"\n");
		
		
		
		
	}

}