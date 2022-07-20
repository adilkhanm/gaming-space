package com.example.gaming_space.mapper;

import com.example.gaming_space.dto.CitiesGameRecordDto;
import com.example.gaming_space.model.AppUser;
import com.example.gaming_space.model.CitiesGameRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CitiesGameRecordMapper {

//    @Mapping(target = "recordId", source = "gameRecordDto.recordId")
    @Mapping(target = "score", source = "gameRecordDto.score")
    @Mapping(target = "citiesCount", source = "gameRecordDto.citiesCount")
    @Mapping(target = "avgTime", source = "gameRecordDto.avgTime")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "date", expression = "java(java.time.Instant.now())")
    CitiesGameRecord mapDtoToRecord(CitiesGameRecordDto gameRecordDto, AppUser user);

    @Mapping(target = "recordId", source = "gameRecord.recordId")
    @Mapping(target = "score", source = "gameRecord.score")
    @Mapping(target = "citiesCount", source = "gameRecord.citiesCount")
    @Mapping(target = "avgTime", source = "gameRecord.avgTime")
    @Mapping(target = "username", source = "gameRecord.user.username")
    @Mapping(target = "date", source = "gameRecord.date")
    CitiesGameRecordDto mapRecordToDto(CitiesGameRecord gameRecord);

}
