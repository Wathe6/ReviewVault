package io.envoi.replica.mediaitem.config;

import io.envoi.replica.config.ReplicaMigration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration(proxyBeanMethods = false)
@ComponentScan("io.envoi.replica.mediaitem")
@EntityScan("io.envoi.replica.mediaitem.entity")
@EnableJpaRepositories("io.envoi.replica.mediaitem.repository")
public class MediaItemReplicaConfig {

    @Bean
    public ReplicaMigration mediaItemReplicaMigration() {
        return new ReplicaMigration(
                "classpath:db/replica/mediaitem",
                "flyway_history_mediaitem"
        );
    }
}
