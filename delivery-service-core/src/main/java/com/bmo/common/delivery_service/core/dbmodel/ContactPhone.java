package com.bmo.common.delivery_service.core.dbmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ContactPhone {

  private String phoneNumber;

  private PhoneType type;
}
