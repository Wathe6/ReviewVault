CREATE TABLE profile.user_profile
(
    id                   UUID                        NOT NULL,
    created_at           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    keycloak_user_id     UUID                 UNIQUE NOT NULL,
    name                 VARCHAR(255)                NOT NULL,
    cover_url            VARCHAR(255)                        ,
    deleted              BOOLEAN                     NOT NULL,
    CONSTRAINT pk_user_profile PRIMARY KEY (id)
);