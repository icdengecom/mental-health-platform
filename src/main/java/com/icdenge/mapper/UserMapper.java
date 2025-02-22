package com.icdenge.mapper;

import com.icdenge.dto.request.SignUpRequest;
import com.icdenge.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

  @Mapping(target = "enabled", ignore = true)
  @Mapping(target = "credentialsNonExpired", ignore = true)
  @Mapping(target = "accountNonLocked", ignore = true)
  @Mapping(target = "accountNonExpired", ignore = true)
  User convert(SignUpRequest request);

}
