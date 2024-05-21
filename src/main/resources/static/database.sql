create table user(
                     id bigint not null auto_increment primary key,
                     username varchar(50) not null,
                     password varchar(100) not null,
                     email varchar(50) not null,
                     nickname varchar(30) not null,
                     authority varchar(30) not null,
                     created_at datetime(6) not null
);

CREATE TABLE server (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        code VARCHAR(10) NOT NULL,
                        name VARCHAR(50) NOT NULL,
                        owner_id BIGINT not null,
                        created_at datetime(6) NOT NULL,
                        CONSTRAINT server_user_fk FOREIGN KEY (owner_id) REFERENCES user(id)
);

create table channel(
                        id bigint not null auto_increment primary key,
                        server_id bigint not null,
                        name varchar(50) not null,
                        created_at datetime(6) not null,
                        constraint channel_server_fk foreign key (server_id) references server(id)
);

create table message(
                        id bigint not null auto_increment primary key,
                        channel_id bigint not null,
                        user_id bigint not null,
                        content longtext,
                        created_at datetime(6) not null,
                        constraint message_channel_fk foreign key (channel_id) references channel(id),
                        constraint message_user_fk foreign key (user_id) references user(id)
);