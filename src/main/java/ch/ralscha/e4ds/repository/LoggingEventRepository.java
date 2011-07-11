package ch.ralscha.e4ds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import ch.ralscha.e4ds.entity.LoggingEvent;


public interface LoggingEventRepository extends JpaRepository<LoggingEvent, Long>, QueryDslPredicateExecutor<LoggingEvent>  {
	//nothing here right now
}
