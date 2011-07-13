package ch.ralscha.e4ds.config;

import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import ch.ralscha.e4ds.entity.Role;
import ch.ralscha.e4ds.entity.User;
import ch.ralscha.e4ds.repository.RoleRepository;
import ch.ralscha.e4ds.repository.UserRepository;

import com.google.common.collect.Sets;

@Component
public class Startup {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TransactionTemplate readWriteTransactionTemplate;

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private MessageSource messageSource;
	
	@PostConstruct
	public void startup() {
		System.out.println(context.getMessage("welcome.title", null, Locale.GERMAN));

		readWriteTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				initUsers();
			}
		});
	}

	void initUsers() {
		if (userRepository.count() == 0) {
			//admin user
			User adminUser = new User();
			adminUser.setUserName("admin");
			adminUser.setEmail("test@test.ch");
			adminUser.setFirstName("admin");
			adminUser.setName("admin");
			System.out.println(passwordEncoder.encode("admin"));
			adminUser.setPasswordHash(passwordEncoder.encode("admin"));
			adminUser.setEnabled(true);
			adminUser.setLocale("en");
			adminUser.setCreateDate(new Date());

			Role adminRole = roleRepository.findByName("ROLE_ADMIN");
			adminUser.setRoles(Sets.newHashSet(adminRole));

			userRepository.save(adminUser);

			//normal user
			User normalUser = new User();
			normalUser.setUserName("user");
			normalUser.setEmail("user@test.ch");
			normalUser.setFirstName("user");
			normalUser.setName("user");

			normalUser.setPasswordHash(passwordEncoder.encode("user"));
			normalUser.setEnabled(true);
			normalUser.setLocale("de");
			normalUser.setCreateDate(new Date());

			Role userRole = roleRepository.findByName("ROLE_USER");
			normalUser.setRoles(Sets.newHashSet(userRole));

			userRepository.save(normalUser);
		}
	}

}
