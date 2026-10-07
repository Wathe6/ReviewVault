package io.envoi.media.credits.repository;

import io.envoi.media.credits.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {

    List<CompanyEntity> findByName(String name);
}
