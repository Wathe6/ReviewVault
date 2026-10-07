package io.envoi.media.messaging.listener;

import io.envoi.contracts.media.v1.MediaItemCreatedV1;
import io.envoi.contracts.media.v1.MediaItemDeletedV1;
import io.envoi.contracts.media.v1.MediaItemUpdatedV1;
import io.envoi.media.media.entity.MediaItemEntity;
import io.envoi.media.messaging.producer.KafkaMediaItemProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class KafkaMediaItemAfterCommitListener {

    private final KafkaMediaItemProducerService producer;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onCreated(MediaItemCreatedV1 entity) {

        producer.sendMediaItemCreatedV1(entity);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onUpdated(MediaItemUpdatedV1 entity) {

        producer.sendMediaItemUpdatedV1(entity);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onDeleted(MediaItemDeletedV1 entity) {

        producer.sendMediaItemDeletedV1(entity);
    }
}
