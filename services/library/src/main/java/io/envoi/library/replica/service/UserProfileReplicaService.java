package io.envoi.library.replica.service;

import io.envoi.library.replica.entity.UserProfileReplicaEntity;
import io.envoi.library.replica.repository.MediaUserProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserProfileReplicaService {

    private final MediaUserProfileRepository repository;

    public UserProfileReplicaEntity save(UserProfileReplicaEntity userProfile) {

        UserProfileReplicaEntity save = repository.save(userProfile);

        log.info("UserProfileReplicaEntity saved with id {}", save.getId());

        return save;
    }

    public UserProfileReplicaEntity update(UserProfileReplicaEntity userProfile) {

        UserProfileReplicaEntity save = repository.findById(userProfile.getId())
                .orElseThrow(() -> new EntityNotFoundException("UserProfileReplica not found with id " + userProfile.getId()));

        save.setLastEventAt(userProfile.getLastEventAt());
        save.setLastEventId(userProfile.getLastEventId());
        save.setDeleted(false);

        save = repository.save(save);

        log.info("UserProfileReplica updated with id {}", save.getId());

        return save;
    }

    public void delete(UserProfileReplicaEntity userProfile) {

        UserProfileReplicaEntity save = repository.findById(userProfile.getId())
                .orElseThrow(() -> new EntityNotFoundException("UserProfileReplica not found with id " + userProfile.getId()));

        save.setLastEventId(userProfile.getLastEventId());
        save.setLastEventAt(userProfile.getLastEventAt());
        save.setDeleted(true);

        repository.save(save);

        log.info("UserProfileReplica deleted with id {}", save.getId());
    }
}
