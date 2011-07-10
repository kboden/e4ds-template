package ch.ralscha.e4ds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import ch.ralscha.e4ds.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User> {
	User findByUserName(String userName);
}
