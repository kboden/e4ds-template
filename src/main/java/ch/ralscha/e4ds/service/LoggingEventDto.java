package ch.ralscha.e4ds.service;

import java.util.Set;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.DateTime;

import ch.ralscha.e4ds.entity.LoggingEvent;
import ch.ralscha.e4ds.entity.LoggingEventProperty;
import ch.ralscha.e4ds.util.DateTimeSerializer;

public class LoggingEventDto {
	private DateTime dateTime;
	private String message;
	private String level;
	private String callerClass;
	private String callerLine;
	private String ip;

	public LoggingEventDto(LoggingEvent event) {

		this.dateTime = new DateTime(event.getTimestmp().longValue());
		this.message = event.getFormattedMessage();
		this.level = event.getLevelString();
		this.callerClass = event.getCallerClass();
		this.callerLine = event.getCallerLine();

		Set<LoggingEventProperty> properties = event.getLoggingEventProperty();

		for (LoggingEventProperty prop : properties) {
			if ("ip".equals(prop.getId().getMappedKey())) {
				this.ip = prop.getMappedValue();
				break;
			}
		}

	}

	@JsonSerialize(using = DateTimeSerializer.class)
	public DateTime getDateTime() {
		return dateTime;
	}

	public String getMessage() {
		return message;
	}

	public String getLevel() {
		return level;
	}

	public String getCallerClass() {
		return callerClass;
	}

	public String getCallerLine() {
		return callerLine;
	}

	public String getIp() {
		return ip;
	}

}
