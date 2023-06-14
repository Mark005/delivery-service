package com.bmo.common.delivery_service.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ContactPhoneDto {

  private String phoneNumber;

  private PhoneTypeDto type;
}
