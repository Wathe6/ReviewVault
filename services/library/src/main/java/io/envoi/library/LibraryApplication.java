package io.envoi.library;

import io.envoi.replica.config.FlywayConfig;
import io.envoi.replica.mediagroup.config.MediaGroupReplicaConfig;
import io.envoi.replica.mediaitem.config.MediaItemReplicaConfig;
import io.envoi.replica.profile.config.ProfileReplicaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("io.envoi.library")
@EnableJpaRepositories("io.envoi.library")
@Import({
		FlywayConfig.class,
		MediaItemReplicaConfig.class,
		MediaGroupReplicaConfig.class,
		ProfileReplicaConfig.class
})
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

}
