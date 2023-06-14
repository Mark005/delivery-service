package com.bmo.common.delivery_service.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = DeliveryServiceClient.class)
@ConditionalOnProperty("feign.client.config.delivery-service.url")
public class DeliveryServiceClientAutoconfiguration {

}
