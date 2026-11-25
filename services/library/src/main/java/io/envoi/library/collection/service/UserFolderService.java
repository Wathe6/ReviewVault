package io.envoi.library.collection.service;

import io.envoi.library.collection.dto.request.UserFolderRequest;
import io.envoi.library.collection.entity.UserFolderEntity;
import io.envoi.library.collection.mapper.UserFolderMapper;
import io.envoi.library.collection.repository.UserFolderRepository;
import io.envoi.replica.profile.repository.UserProfileReplicaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserFolderService {

    private final UserFolderRepository userFolderRepository;

    private final UserProfileReplicaRepository userProfileRepository;

    private final UserFolderMapper mapper;

    @Transactional(readOnly = true)
    public UserFolderEntity findById(Long id) {

        return userFolderRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("UserFolder not found " + id));
    }

    public UserFolderEntity create(UUID userProfileId, UserFolderRequest request) {

        UserFolderEntity entity = mapper.toEntity(request);

        entity.setUserProfileReplica(userProfileRepository.findById(userProfileId)
                .orElseThrow(() -> new EntityNotFoundException("UserProfile not found " + userProfileId)));

        entity = userFolderRepository.save(entity);

        log.info("UserFolder saved with id {}", entity.getId());

        return entity;
    }

    public UserFolderEntity update(Long id, UserFolderRequest request) {

        UserFolderEntity entity = userFolderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserFolder not found " + id));

        mapper.update(request, entity);

        entity = userFolderRepository.save(entity);

        log.info("UserFolder updated with id {}", entity.getId());

        return entity;
    }

    public void deleteById(Long id) {

        UserFolderEntity entity = userFolderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserFolder not found " + id));

        userFolderRepository.delete(entity);

        log.info("UserFolder deleted with id {}", id);
    }
}
