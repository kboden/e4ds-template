package ch.ralscha.e4ds.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QLoggingEventException is a Querydsl query type for LoggingEventException
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLoggingEventException extends EntityPathBase<LoggingEventException> {

    private static final long serialVersionUID = -1671729784;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QLoggingEventException loggingEventException = new QLoggingEventException("loggingEventException");

    public final QLoggingEvent eventId;

    public final QLoggingEventExceptionId id;

    public final StringPath traceLine = createString("traceLine");

    public QLoggingEventException(String variable) {
        this(LoggingEventException.class, forVariable(variable), INITS);
    }

    public QLoggingEventException(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLoggingEventException(PathMetadata<?> metadata, PathInits inits) {
        this(LoggingEventException.class, metadata, inits);
    }

    public QLoggingEventException(Class<? extends LoggingEventException> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.eventId = inits.isInitialized("eventId") ? new QLoggingEvent(forProperty("eventId")) : null;
        this.id = inits.isInitialized("id") ? new QLoggingEventExceptionId(forProperty("id")) : null;
    }

}

