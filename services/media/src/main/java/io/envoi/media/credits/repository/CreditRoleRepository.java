package io.envoi.media.credits.repository;

import io.envoi.media.credits.entity.CreditRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRoleRepository extends JpaRepository<CreditRoleEntity, Short> {
}
