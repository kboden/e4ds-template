package ch.ralscha.e4ds.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.ralscha.e4ds.entity.User;
import ch.ralscha.e4ds.repository.UserRepository;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.ExtDirectStoreResponse;
import ch.ralscha.extdirectspring.bean.SortDirection;
import ch.ralscha.extdirectspring.bean.SortInfo;

import com.google.common.collect.Lists;

@Service
public class UserService {
		
	@Autowired
	private UserRepository userRepository;
	
	@ExtDirectMethod(ExtDirectMethodType.STORE_READ)
	public ExtDirectStoreResponse<User> load(ExtDirectStoreReadRequest request) {

		List<Order> orders = Lists.newArrayList();
		for (SortInfo sortInfo : request.getSorters()) {
			if (sortInfo.getDirection() == SortDirection.ASCENDING) {
				orders.add(new Order(Direction.ASC, sortInfo.getProperty()));	
			} else {
				orders.add(new Order(Direction.DESC, sortInfo.getProperty()));	
			}			
		}
		
		Sort sort = new Sort(orders);		
		Page<User> page = userRepository.findAll(new PageRequest(request.getPage()-1, request.getLimit(), sort));		
		return new ExtDirectStoreResponse<User>((int)page.getTotalElements(), page.getContent());
	}
	
	
	@ExtDirectMethod(ExtDirectMethodType.STORE_MODIFY)
	public List<User> create(List<User> newUsers) {
		List<User> insertedUsers = Lists.newArrayList();

		for (User newUser : newUsers) {
			newUser.setCreateDate(new Date());
			insertedUsers.add(userRepository.save(newUser));
		}

		return insertedUsers;
	}

	@ExtDirectMethod(ExtDirectMethodType.STORE_MODIFY)
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

	@ExtDirectMethod(ExtDirectMethodType.STORE_MODIFY)
	public void destroy(List<User> destroyUsers) {
		for (User user : destroyUsers) {
			userRepository.delete(user);
		}
	}	
}
