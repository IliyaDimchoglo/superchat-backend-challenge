CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table if not exists bitcoin_rate (
    id UUID PRIMARY KEY default uuid_generate_v4(),
    created_time       timestamp,
    updated_time       timestamp,
    rate  varchar not null,
    currency  varchar not null
);

create table if not exists contacts (
    id UUID PRIMARY KEY default uuid_generate_v4(),
    created_time       timestamp,
    updated_time       timestamp,
    "name" varchar,
    email varchar not null
    );

create table if not exists channels (
    id UUID PRIMARY KEY default uuid_generate_v4(),
    created_time       timestamp,
    updated_time       timestamp,
    "type"  varchar not null,
    contact_id UUID not null,
    foreign key (contact_id) references contacts
);

create table if not exists messages (
    id UUID PRIMARY KEY default uuid_generate_v4(),
    created_time       timestamp,
    updated_time       timestamp,
    contact_id UUID not null,
    channel_id UUID not null,
    text varchar not  null,
    foreign key (contact_id) references contacts,
    foreign key (channel_id) references channels
);