package com.bmo.common.delivery_service.core.mapper;


import com.bmo.common.delivery_service.model.rest.PageRequestDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageableMapper {

  public static Pageable map(PageRequestDto pageRequest) {
    return PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize());
  }
}
