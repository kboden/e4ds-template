package ch.ralscha.e4ds.schedule;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ch.ralscha.e4ds.entity.LoggingEvent;
import ch.ralscha.e4ds.entity.QLoggingEvent;
import ch.ralscha.e4ds.repository.LoggingEventRepository;

@Component
public class LogCleanup {
	
	@Autowired
	private LoggingEventRepository loggingEventRepository;

	@Transactional
	@Scheduled(cron = "0 0 4 * * *")
	public void doCleanup() {

		//Delete all log entries that are older than 1 day
		DateTime yesterday = new DateTime().minusDays(1);

		Iterable<LoggingEvent> eventsToDelete = loggingEventRepository.findAll(QLoggingEvent.loggingEvent.timestmp
				.loe(yesterday.toDate().getTime()));
		loggingEventRepository.delete(eventsToDelete);

	}

}
