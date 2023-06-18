package com.bmo.common.delivery_service.core.configs.properties;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
public class KafkaProducerProperties {

  @NotEmpty
  private String topic;

  @Valid
  @NestedConfigurationProperty
  private KafkaClusterProperties cluster;
}
