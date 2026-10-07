CREATE TABLE review.media_item_replica
(
    id                   UUID                        NOT NULL,
    created_at           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    title                VARCHAR(255)                NOT NULL,
    last_event_id        UUID                        NOT NULL,
    last_event_at        TIMESTAMP WITHOUT TIME ZONE,
    deleted              BOOLEAN                     NOT NULL,
    CONSTRAINT pk_media_item PRIMARY KEY (id)
);
