package io.envoi.replica.mediagroup.service;

import io.envoi.replica.mediagroup.entity.MediaGroupReplicaEntity;
import io.envoi.replica.mediagroup.repository.MediaGroupReplicaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MediaGroupReplicaService {

    private final MediaGroupReplicaRepository repository;

    public MediaGroupReplicaEntity save(MediaGroupReplicaEntity mediaGroup) {

        MediaGroupReplicaEntity save = repository.save(mediaGroup);

        log.info("MediaGroupReplicaEntity saved with id {}", save.getId());

        return save;
    }

    public MediaGroupReplicaEntity update(MediaGroupReplicaEntity mediaGroup) {

        MediaGroupReplicaEntity save = repository.findById(mediaGroup.getId())
                .orElseThrow(() -> new EntityNotFoundException("MediaGroupReplica not found with id " + mediaGroup.getId()));

        save.setTitle(mediaGroup.getTitle());
        save.setCoverUrl(mediaGroup.getCoverUrl());
        save.setLastEventAt(mediaGroup.getLastEventAt());
        save.setLastEventId(mediaGroup.getLastEventId());
        save.setDeleted(false);

        save = repository.save(save);

        log.info("MediaGroupReplica updated with id {}", save.getId());

        return save;
    }

    public void delete(MediaGroupReplicaEntity mediaGroup) {

        MediaGroupReplicaEntity save = repository.findById(mediaGroup.getId())
                .orElseThrow(() -> new EntityNotFoundException("MediaGroupReplica not found with id " + mediaGroup.getId()));

        save.setLastEventId(mediaGroup.getLastEventId());
        save.setLastEventAt(mediaGroup.getLastEventAt());
        save.setDeleted(true);

        repository.save(save);

        log.info("MediaGroupReplica deleted with id {}", save.getId());
    }
}
