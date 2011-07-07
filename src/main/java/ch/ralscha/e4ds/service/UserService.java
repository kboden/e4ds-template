package ch.ralscha.e4ds.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResponse;

import com.google.common.collect.Lists;

@Service
public class UserService {
		
	@ExtDirectMethod(ExtDirectMethodType.STORE_READ)
	public ExtDirectStoreResponse<User> load(ExtDirectStoreReadRequest request) {
		List<User> users = Lists.newArrayList();
		return new ExtDirectStoreResponse<User>(users);
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
