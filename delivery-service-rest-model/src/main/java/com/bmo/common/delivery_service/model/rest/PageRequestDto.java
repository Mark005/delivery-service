package com.bmo.common.delivery_service.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PageRequestDto {

  private Integer pageSize;
  private Integer pageNumber;
}
