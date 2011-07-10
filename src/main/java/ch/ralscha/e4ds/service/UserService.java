package ch.ralscha.e4ds.service;

import java.util.List;

import liquibase.changelog.visitor.ChangeSetVisitor.Direction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import ch.ralscha.e4ds.entity.User;
import ch.ralscha.e4ds.repository.UserRepository;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResponse;

import com.google.common.collect.Lists;

@Service
public class UserService {
		
	@Autowired
	private UserRepository userRepository;
	
	@ExtDirectMethod(ExtDirectMethodType.STORE_READ)
	public ExtDirectStoreResponse<User> load(ExtDirectStoreReadRequest request) {

		
		//todo
		//Sort sort = new Sort(Order.create(Direction.FORWARD, request.getSorters()));
		
		Page<User> page = userRepository.findAll(new PageRequest(request.getPage(), request.getLimit()));		
		return new ExtDirectStoreResponse<User>((int)page.getTotalElements(), page.getContent());
	}
	
	
	@ExtDirectMethod(ExtDirectMethodType.STORE_MODIFY)
	public List<User> create(List<User> newUsers) {
		List<User> insertedUsers = Lists.newArrayList();

//		for (User newUser : newUsers) {
//			userDb.insert(newUser);
//			insertedUsers.add(newUser);
//		}

		return insertedUsers;
	}

	@ExtDirectMethod(ExtDirectMethodType.STORE_MODIFY)
	public List<User> update(List<User> modifiedUsers) {
		List<User> updatedRecords = Lists.newArrayList();
//		for (User modifiedUser : modifiedUsers) {
//			User u = userDb.findUser(modifiedUser.getId());
//			if (u != null) {
//				u.update(modifiedUser);
//				updatedRecords.add(u);
//			}
//		}
		return updatedRecords;
	}

	@ExtDirectMethod(ExtDirectMethodType.STORE_MODIFY)
	public void destroy(List<User> destroyUsers) {
//		for (User user : destroyUsers) {
//			userDb.deleteUser(user);
//		}
	}	
}
