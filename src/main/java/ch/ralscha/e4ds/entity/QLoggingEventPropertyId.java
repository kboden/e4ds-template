package ch.ralscha.e4ds.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QLoggingEventPropertyId is a Querydsl query type for LoggingEventPropertyId
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QLoggingEventPropertyId extends BeanPath<LoggingEventPropertyId> {

    private static final long serialVersionUID = 104189879;

    public static final QLoggingEventPropertyId loggingEventPropertyId = new QLoggingEventPropertyId("loggingEventPropertyId");

    public final NumberPath<Long> eventId = createNumber("eventId", Long.class);

    public final StringPath mappedKey = createString("mappedKey");

    public QLoggingEventPropertyId(String variable) {
        super(LoggingEventPropertyId.class, forVariable(variable));
    }

    public QLoggingEventPropertyId(BeanPath<? extends LoggingEventPropertyId> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    public QLoggingEventPropertyId(PathMetadata<?> metadata) {
        super(LoggingEventPropertyId.class, metadata);
    }

}

