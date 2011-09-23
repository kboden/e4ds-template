package ch.ralscha.e4ds.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QLoggingEventProperty is a Querydsl query type for LoggingEventProperty
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLoggingEventProperty extends EntityPathBase<LoggingEventProperty> {

    private static final long serialVersionUID = 2091726204;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QLoggingEventProperty loggingEventProperty = new QLoggingEventProperty("loggingEventProperty");

    public final QLoggingEvent eventId;

    public final QLoggingEventPropertyId id;

    public final StringPath mappedValue = createString("mappedValue");

    public QLoggingEventProperty(String variable) {
        this(LoggingEventProperty.class, forVariable(variable), INITS);
    }

    public QLoggingEventProperty(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QLoggingEventProperty(PathMetadata<?> metadata, PathInits inits) {
        this(LoggingEventProperty.class, metadata, inits);
    }

    public QLoggingEventProperty(Class<? extends LoggingEventProperty> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.eventId = inits.isInitialized("eventId") ? new QLoggingEvent(forProperty("eventId")) : null;
        this.id = inits.isInitialized("id") ? new QLoggingEventPropertyId(forProperty("id")) : null;
    }

}

