package com.example.gaming_space.mapper;

import com.example.gaming_space.dto.UserDto;
import com.example.gaming_space.model.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "created", source = "user.created")
    UserDto mapUserToDto(AppUser user);
}
