package ch.ralscha.e4ds.entity;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.SetPath;
import com.mysema.query.types.path.StringPath;

/**
 * QLoggingEvent is a Querydsl query type for LoggingEvent
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLoggingEvent extends EntityPathBase<LoggingEvent> {

	private static final long serialVersionUID = -1510446457;

	public static final QLoggingEvent loggingEvent = new QLoggingEvent("loggingEvent");

	public final StringPath arg0 = createString("arg0");

	public final StringPath arg1 = createString("arg1");

	public final StringPath arg2 = createString("arg2");

	public final StringPath arg3 = createString("arg3");

	public final StringPath callerClass = createString("callerClass");

	public final StringPath callerFilename = createString("callerFilename");

	public final StringPath callerLine = createString("callerLine");

	public final StringPath callerMethod = createString("callerMethod");

	public final NumberPath<Long> eventId = createNumber("eventId", Long.class);

	public final StringPath formattedMessage = createString("formattedMessage");

	public final StringPath levelString = createString("levelString");

	public final StringPath loggerName = createString("loggerName");

	public final SetPath<LoggingEventException, QLoggingEventException> loggingEventException = this
			.<LoggingEventException, QLoggingEventException> createSet("loggingEventException",
					LoggingEventException.class, QLoggingEventException.class);

	public final SetPath<LoggingEventProperty, QLoggingEventProperty> loggingEventProperty = this
			.<LoggingEventProperty, QLoggingEventProperty> createSet("loggingEventProperty",
					LoggingEventProperty.class, QLoggingEventProperty.class);

	public final NumberPath<Short> referenceFlag = createNumber("referenceFlag", Short.class);

	public final StringPath threadName = createString("threadName");

	public final NumberPath<java.math.BigDecimal> timestmp = createNumber("timestmp", java.math.BigDecimal.class);

	public QLoggingEvent(String variable) {
		super(LoggingEvent.class, forVariable(variable));
	}

	public QLoggingEvent(Path<? extends LoggingEvent> entity) {
		super(entity.getType(), entity.getMetadata());
	}

	public QLoggingEvent(PathMetadata<?> metadata) {
		super(LoggingEvent.class, metadata);
	}

}
