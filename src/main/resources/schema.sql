-- CREATE TABLE IF NOT EXISTS users
-- (
--    id SERIAL PRIMARY KEY,
--    first_name varchar(255) not null,
--    last_name varchar(255) not null,
--    email_id varchar(255) not null
-- );

CREATE TABLE IF NOT EXISTS "public"."agents"
(
    id               BIGSERIAL,
    name             VARCHAR(100)   NOT NULL,
    username         VARCHAR(100)   NOT NULL,
    password         VARCHAR(100)   NOT NULL,
    description      VARCHAR(100)   NOT NULL,
    experience       VARCHAR(100)   NOT NULL,
    agency_id        INTEGER        NOT NULL,
    banner_name      VARCHAR(100)   NOT NULL,
    banner_link      VARCHAR(100)   NOT NULL,
    profile_name     VARCHAR(100)   NOT NULL,
    profile_link     VARCHAR(100)   NOT NULL,
    email            VARCHAR(100)   NOT NULL,
    phone_number     VARCHAR(100)   NOT NULL,
    created_at       DATE           NOT NULL,
    created_by       INTEGER        NOT NULL,
    updated_at       DATE                   ,
    updated_by       INTEGER                ,
    deleted          BOOLEAN        NOT NULL DEFAULT false,
    deleted_at       DATE                   ,
    deleted_by       INTEGER                ,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "public"."review"
(
    id               BIGSERIAL,
    agent_id         INTEGER        NOT NULL,
    property_id      INTEGER        NOT NULL,
    user_id          INTEGER        NOT NULL,
    agency_id        INTEGER        NOT NULL,
    category         VARCHAR(100)   NOT NULL,
    description      VARCHAR(100)   NOT NULL,
    rating           INTEGER        NOT NULL,
    verify_admin_id  INTEGER        NOT NULL,
    created_at       DATE           NOT NULL,
    created_by       INTEGER        NOT NULL,
    updated_at       DATE                   ,
    updated_by       INTEGER                ,
    deleted          BOOLEAN        NOT NULL DEFAULT false,
    deleted_at       DATE                   ,
    deleted_by       INTEGER                ,
    PRIMARY KEY (id),
    CONSTRAINT fk_public_agents_id
    FOREIGN KEY(agent_id)
    REFERENCES "public"."agents"(id)
);

-- CREATE TABLE IF NOT EXISTS testing
-- (
--    id SERIAL PRIMARY KEY,
--    first_name varchar(255) not null,
--    last_name varchar(255) not null,
--    email_id varchar(255) not null
-- );