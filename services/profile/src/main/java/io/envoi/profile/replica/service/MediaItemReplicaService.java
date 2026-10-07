package io.envoi.profile.replica.service;

import io.envoi.profile.replica.entity.MediaItemReplicaEntity;
import io.envoi.profile.replica.repository.MediaItemReplicaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MediaItemReplicaService {

    private final MediaItemReplicaRepository repository;

    public MediaItemReplicaEntity save(MediaItemReplicaEntity mediaItem) {

        MediaItemReplicaEntity save = repository.save(mediaItem);

        log.info("MediaItemReplicaEntity saved with id {}", save.getId());

        return save;
    }

    public MediaItemReplicaEntity update(MediaItemReplicaEntity mediaItem) {

        MediaItemReplicaEntity save = repository.findById(mediaItem.getId())
                .orElseThrow(() -> new EntityNotFoundException("MediaItemReplica not found with id " + mediaItem.getId()));

        save.setTitle(mediaItem.getTitle());
        save.setLastEventAt(mediaItem.getLastEventAt());
        save.setLastEventId(mediaItem.getLastEventId());
        save.setDeleted(false);

        save = repository.save(save);

        log.info("MediaItemReplica updated with id {}", save.getId());

        return save;
    }

    public void delete(MediaItemReplicaEntity mediaItem) {

        MediaItemReplicaEntity save = repository.findById(mediaItem.getId())
                .orElseThrow(() -> new EntityNotFoundException("MediaItemReplica not found with id " + mediaItem.getId()));

        save.setLastEventId(mediaItem.getLastEventId());
        save.setLastEventAt(mediaItem.getLastEventAt());
        save.setDeleted(true);

        repository.save(save);

        log.info("MediaItemReplica deleted with id {}", save.getId());
    }
}
