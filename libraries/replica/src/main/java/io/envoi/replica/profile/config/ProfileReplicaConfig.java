package io.envoi.replica.profile.config;

import io.envoi.replica.config.ReplicaMigration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration(proxyBeanMethods = false)
@ComponentScan("io.envoi.replica.profile")
@EntityScan("io.envoi.replica.profile.entity")
@EnableJpaRepositories("io.envoi.replica.profile.repository")
public class ProfileReplicaConfig {

    @Bean
    public ReplicaMigration profileReplicaMigration() {
        return new ReplicaMigration(
                "classpath:db/replica/profile",
                "flyway_history_profile"
        );
    }
}
