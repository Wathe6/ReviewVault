CREATE TABLE ${replicaSchema}.user_profile_replica
(
    id                   UUID                        NOT NULL,
    created_at           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_event_id        UUID                        NOT NULL,
    last_event_at        TIMESTAMP WITHOUT TIME ZONE         ,
    deleted              BOOLEAN                     NOT NULL,
    CONSTRAINT pk_user_profile PRIMARY KEY (id)
);

