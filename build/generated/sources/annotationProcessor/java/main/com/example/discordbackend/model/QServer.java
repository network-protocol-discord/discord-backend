package com.example.discordbackend.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QServer is a Querydsl query type for Server
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QServer extends EntityPathBase<Server> {

    private static final long serialVersionUID = -1064160867L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QServer server = new QServer("server");

    public final StringPath code = createString("code");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QUser owner;

    public QServer(String variable) {
        this(Server.class, forVariable(variable), INITS);
    }

    public QServer(Path<? extends Server> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QServer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QServer(PathMetadata metadata, PathInits inits) {
        this(Server.class, metadata, inits);
    }

    public QServer(Class<? extends Server> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.owner = inits.isInitialized("owner") ? new QUser(forProperty("owner")) : null;
    }

}

