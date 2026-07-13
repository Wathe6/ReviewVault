package io.envoi.media.credits.repository;

import io.envoi.media.credits.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<PersonEntity, UUID> {
}
