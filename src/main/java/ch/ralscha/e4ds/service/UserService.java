package ch.ralscha.e4ds.service;

import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.STORE_MODIFY;
import static ch.ralscha.extdirectspring.annotation.ExtDirectMethodType.STORE_READ;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.ralscha.e4ds.entity.User;
import ch.ralscha.e4ds.repository.UserRepository;
import ch.ralscha.e4ds.util.Util;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResponse;

import com.google.common.collect.Lists;

@Service
public class UserService {
		
	@Autowired
	private UserRepository userRepository;
	
	@ExtDirectMethod(STORE_READ)
	public ExtDirectStoreResponse<User> load(ExtDirectStoreReadRequest request) {
		Page<User> page = userRepository.findAll(Util.createPageRequest(request));		
		return new ExtDirectStoreResponse<User>((int)page.getTotalElements(), page.getContent());
	}

	@ExtDirectMethod(STORE_MODIFY)
	public List<User> create(List<User> newUsers) {
		List<User> insertedUsers = Lists.newArrayList();

		for (User newUser : newUsers) {
			newUser.setCreateDate(new Date());
			insertedUsers.add(userRepository.save(newUser));
		}

		return insertedUsers;
	}

	@ExtDirectMethod(STORE_MODIFY)
	@Transactional
	public List<User> update(List<User> modifiedUsers) {
		List<User> updatedRecords = Lists.newArrayList();
		for (User modifiedUser : modifiedUsers) {
			User dbUser = userRepository.findOne(modifiedUser.getId());
			if (dbUser != null) {
				dbUser.update(modifiedUser);
				updatedRecords.add(dbUser);
			}
		}
		return updatedRecords;
	}

	@ExtDirectMethod(STORE_MODIFY)
	public void destroy(List<User> destroyUsers) {
		for (User user : destroyUsers) {
			userRepository.delete(user);
		}
	}	
}
