package ch.ralscha.e4ds.service;

import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.FORM_POST;
import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.STORE_MODIFY;
import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.STORE_READ;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.ralscha.e4ds.entity.QUser;
import ch.ralscha.e4ds.entity.User;
import ch.ralscha.e4ds.repository.UserRepository;
import ch.ralscha.e4ds.util.Util;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.bean.ExtDirectResponse;
import ch.ralscha.extdirectspring.bean.ExtDirectResponseBuilder;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResponse;

import com.mysema.query.BooleanBuilder;

@Service
@Controller
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@ExtDirectMethod(STORE_READ)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ExtDirectStoreResponse<User> load(ExtDirectStoreReadRequest request) {
		Page<User> page = userRepository.findAll(Util.createPageRequest(request));
		return new ExtDirectStoreResponse<User>((int) page.getTotalElements(), page.getContent());
	}

	@ExtDirectMethod(STORE_MODIFY)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void destroy(List<User> destroyUsers) {
		for (User user : destroyUsers) {
			userRepository.delete(user);
		}
	}

	@ExtDirectMethod(FORM_POST)
	@ResponseBody
	@RequestMapping(value = "/userFormPost", method = RequestMethod.POST)
	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ExtDirectResponse userFormPost(HttpServletRequest request, @Valid User modifiedUser, BindingResult result) {

		//Check uniqueness of userName and email
		if (!result.hasErrors()) {
			BooleanBuilder bb = new BooleanBuilder(QUser.user.userName.equalsIgnoreCase(modifiedUser.getUserName()));
			if (modifiedUser.getId() != null) {
				bb.and(QUser.user.id.ne(modifiedUser.getId()));
			}			
			if (userRepository.count(bb) > 0) {
				result.rejectValue("userName", null, "Username already taken");
			}

			
			bb =  new BooleanBuilder(QUser.user.email.equalsIgnoreCase(modifiedUser.getEmail()));
			if (modifiedUser.getId() != null) {
				bb.and(QUser.user.id.ne(modifiedUser.getId()));
			}
			if (userRepository.count(bb) > 0) {
				result.rejectValue("email", null, "Email already taken");
			}
		}

		if (!result.hasErrors()) {
			
			if (StringUtils.hasText(modifiedUser.getPasswordHash())) {
				modifiedUser.setPasswordHash(passwordEncoder.encode(modifiedUser.getPasswordHash()));
			}
			
			if (modifiedUser.getId() != null) {
				User dbUser = userRepository.findOne(modifiedUser.getId());
				if (dbUser != null) {
					dbUser.update(modifiedUser);
				}
			} else {
				modifiedUser.setCreateDate(new Date());
				userRepository.save(modifiedUser);
			}
		}
		
		ExtDirectResponseBuilder builder = new ExtDirectResponseBuilder(request);
		builder.addErrors(result);
		return builder.build();

	}

	//	@ExtDirectMethod(STORE_MODIFY)
	//	public List<User> create(List<User> newUsers) {
	//		List<User> insertedUsers = Lists.newArrayList();
	//
	//		for (User newUser : newUsers) {
	//			
	//			if (StringUtils.hasText(newUser.getPasswordHash())) {
	//				newUser.setPasswordHash(passwordEncoder.encode(newUser.getPasswordHash()));
	//			}
	//			
	//			newUser.setCreateDate(new Date());
	//			insertedUsers.add(userRepository.save(newUser));
	//			
	//			newUser.setPasswordHash(null);
	//		}
	//
	//		return insertedUsers;
	//	}
	//
	//	@ExtDirectMethod(STORE_MODIFY)
	//	@Transactional
	//	public List<User> update(List<User> modifiedUsers) {
	//		List<User> updatedRecords = Lists.newArrayList();
	//		for (User modifiedUser : modifiedUsers) {
	//			User dbUser = userRepository.findOne(modifiedUser.getId());
	//			if (dbUser != null) {
	//				dbUser.update(modifiedUser);
	//								
	//				if (StringUtils.hasText(modifiedUser.getPasswordHash())) {
	//					dbUser.setPasswordHash(passwordEncoder.encode(modifiedUser.getPasswordHash()));
	//					modifiedUser.setPasswordHash(null);
	//				}				
	//				
	//				updatedRecords.add(dbUser);
	//			}
	//		}
	//		return updatedRecords;
	//	}	

}
