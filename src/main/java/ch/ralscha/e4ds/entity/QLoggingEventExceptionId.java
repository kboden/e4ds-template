package ch.ralscha.e4ds.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QLoggingEventExceptionId is a Querydsl query type for LoggingEventExceptionId
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QLoggingEventExceptionId extends BeanPath<LoggingEventExceptionId> {

    private static final long serialVersionUID = -214551357;

    public static final QLoggingEventExceptionId loggingEventExceptionId = new QLoggingEventExceptionId("loggingEventExceptionId");

    public final NumberPath<Long> eventId = createNumber("eventId", Long.class);

    public final NumberPath<Short> i = createNumber("i", Short.class);

    public QLoggingEventExceptionId(String variable) {
        super(LoggingEventExceptionId.class, forVariable(variable));
    }

    public QLoggingEventExceptionId(BeanPath<? extends LoggingEventExceptionId> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QLoggingEventExceptionId(PathMetadata<?> metadata) {
        super(LoggingEventExceptionId.class, metadata);
    }

}

