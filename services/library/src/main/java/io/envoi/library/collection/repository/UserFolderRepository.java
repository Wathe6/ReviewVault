package io.envoi.library.collection.repository;

import io.envoi.library.collection.entity.UserFolderEntity;
import org.apache.kafka.common.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFolderRepository extends JpaRepository<UserFolderEntity, Uuid> {
}
