package io.envoi.eureka.listeners;

import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EurekaServerEventListener {

    private static final Logger log = LoggerFactory.getLogger(EurekaServerEventListener.class);

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        log.warn("Сервис отменил регистрацию: {}, время: {}, сервер: {}",
                event.getAppName(),
                event.getTimestamp(),
                event.getServerId());
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        log.info("Сервис зарегистрирован: {}, IP: {}, порт: {}, версия: {}",
                instanceInfo.getAppName().toUpperCase(),
                instanceInfo.getIPAddr(),
                instanceInfo.getPort(),
                instanceInfo.getMetadata().get("version"));

        log.debug("Метаданные сервиса {}: {}",
                instanceInfo.getAppName(),
                instanceInfo.getMetadata());
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        log.debug("Сервис обновил регистрацию: {}, время: {}",
                event.getAppName(),
                event.getTimestamp());
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        log.info("Реестр Eureka доступен");
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        log.info("Eureka Server запущен и готов к регистрации сервисов");
    }
}
