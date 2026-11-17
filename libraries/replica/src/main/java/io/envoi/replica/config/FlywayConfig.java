package io.envoi.replica.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.flyway.autoconfigure.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class FlywayConfig {

    @Bean
    public FlywayMigrationStrategy flywayStrategy(
            ObjectProvider<ReplicaMigration> migrations
    ) {
        return serviceFlyway -> {
            migrations.orderedStream().forEach(migration ->
                    Flyway.configure()
                            .configuration(serviceFlyway.getConfiguration())
                            .locations(migration.location())
                            .table(migration.historyTable())
                            .load()
                            .migrate()
            );

            serviceFlyway.migrate();
        };
    }
}