package io.envoi.media;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

@TestConfiguration
class TransactionTestConfiguration {

    @Bean
    PlatformTransactionManager transactionManager() {
        AbstractPlatformTransactionManager manager =
                new AbstractPlatformTransactionManager() {

                    @Override
                    protected Object doGetTransaction() {
                        return new Object();
                    }

                    @Override
                    protected void doBegin(
                            Object transaction,
                            TransactionDefinition definition
                    ) {
                    }

                    @Override
                    protected void doCommit(
                            DefaultTransactionStatus status
                    ) {
                    }

                    @Override
                    protected void doRollback(
                            DefaultTransactionStatus status
                    ) {
                    }
                };

        manager.setTransactionSynchronization(
                AbstractPlatformTransactionManager.SYNCHRONIZATION_ALWAYS
        );

        return manager;
    }

    @Bean
    TransactionTemplate transactionTemplate(
            PlatformTransactionManager transactionManager
    ) {
        return new TransactionTemplate(transactionManager);
    }
}