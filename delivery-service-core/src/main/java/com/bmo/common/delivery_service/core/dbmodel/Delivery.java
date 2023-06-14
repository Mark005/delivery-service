package com.bmo.common.delivery_service.core.dbmodel;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

  @ManyToOne(optional = false)
  @JoinColumn(name = "delivery_type_id", nullable = false)
  private DeliveryType deliveryType;

  @Column(name = "status", nullable = false)
  private DeliveryStatus status;

  @Type(type = "jsonb")
  @Column(name = "order_info", columnDefinition = "jsonb")
  private DeliveryAddress deliveryAddress;

  @Type(type = "jsonb")
  @Column(name = "contact_phone", columnDefinition = "jsonb")
  private ContactPhone contactPhone;

  @Type(type = "jsonb")
  @Column(name = "order_info", columnDefinition = "jsonb")
  private List<DeliverySnapshot> deliveryHistory;
}
