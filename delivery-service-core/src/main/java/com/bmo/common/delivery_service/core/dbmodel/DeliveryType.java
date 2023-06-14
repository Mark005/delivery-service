package com.bmo.common.delivery_service.core.dbmodel;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "delivery_type")
public class DeliveryType {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(name = "delivery_type_enum", nullable = false, unique = true)
  private ShippingMethod shippingMethod;

  private String name;

  private String description;

  @Column(name = "is_available", nullable = false)
  private Boolean isAvailable;

  @Builder.Default
  @OneToMany(mappedBy = "deliveryType", orphanRemoval = true)
  private Set<Delivery> deliveries = new LinkedHashSet<>();

}
