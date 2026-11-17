package io.envoi.replica.mediagroup.config;

import io.envoi.replica.config.ReplicaMigration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration(proxyBeanMethods = false)
@ComponentScan("io.envoi.replica.mediagroup")
@EntityScan("io.envoi.replica.mediagroup.entity")
@EnableJpaRepositories("io.envoi.replica.mediagroup.repository")
public class MediaGroupReplicaConfig {

    @Bean
    public ReplicaMigration mediaGroupReplicaMigration() {
        return new ReplicaMigration(
                "classpath:db/replica/mediagroup",
                "flyway_history_mediagroup"
        );
    }
}
