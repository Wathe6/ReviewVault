package io.envoi.replica.config;

public record ReplicaMigration(
        String location,
        String historyTable
) {
}
