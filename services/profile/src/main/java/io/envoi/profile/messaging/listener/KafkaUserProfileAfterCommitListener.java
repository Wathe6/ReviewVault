package io.envoi.profile.messaging.listener;

import io.envoi.contracts.profile.v1.UserProfileCreatedV1;
import io.envoi.contracts.profile.v1.UserProfileDeletedV1;
import io.envoi.contracts.profile.v1.UserProfileUpdatedV1;
import io.envoi.profile.messaging.producer.KafkaUserProfileProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class KafkaUserProfileAfterCommitListener {

    private final KafkaUserProfileProducerService producer;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onCreated(UserProfileCreatedV1 entity) {

        producer.sendUserProfileCreatedV1(entity);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onUpdated(UserProfileUpdatedV1 entity) {

        producer.sendUserProfileUpdatedV1(entity);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onDeleted(UserProfileDeletedV1 entity) {

        producer.sendUserProfileDeletedV1(entity);
    }
}