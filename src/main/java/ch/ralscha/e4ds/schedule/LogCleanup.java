package ch.ralscha.e4ds.schedule;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ch.ralscha.e4ds.entity.LoggingEvent;
import ch.ralscha.e4ds.entity.QLoggingEvent;

import com.mysema.commons.lang.CloseableIterator;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

@Component
public class LogCleanup {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@Scheduled(cron="0 51 6 * * *")
	public void doCleanup() {
		//Delete all log entries that are older than 1 day
		DateTime yesterday = new DateTime().minusDays(1);
		
		JPQLQuery query = new JPAQuery(entityManager).from(QLoggingEvent.loggingEvent);
		query.where(QLoggingEvent.loggingEvent.timestmp.loe(yesterday.toDate().getTime()));
		
		CloseableIterator<LoggingEvent> it = query.iterate(QLoggingEvent.loggingEvent);
		while (it.hasNext()) {
			LoggingEvent event = it.next();
			entityManager.remove(event);			
		}
		
		
		
	}
	
}
