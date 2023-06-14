package com.bmo.common.delivery_service.core.dbmodel;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(
    name = "jsonb",
    typeClass = JsonBinaryType.class
)
@Entity
@Table(name = "delivery")
public class Delivery {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "order_id", nullable = false)
  private UUID orderId;

  @Column(name = "user_id", nullable = false)
  private UUID userId;

  @Type(type = "jsonb")
  @Column(name = "delivery_type", columnDefinition = "jsonb", nullable = false)
  private DeliveryTypeSnapshot deliveryType;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private DeliveryStatus status;

  @Type(type = "jsonb")
  @Column(name = "delivery_address", columnDefinition = "jsonb")
  private DeliveryAddress deliveryAddress;

  @Type(type = "jsonb")
  @Column(name = "contact_phone", columnDefinition = "jsonb", nullable = false)
  private ContactPhone contactPhone;

  @Builder.Default
  @Type(type = "jsonb")
  @Column(name = "delivery_history", columnDefinition = "jsonb")
  private List<DeliverySnapshot> deliveryHistory = new ArrayList<>();
}
