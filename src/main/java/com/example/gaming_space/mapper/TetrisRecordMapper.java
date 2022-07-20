package com.example.gaming_space.mapper;

import com.example.gaming_space.dto.TetrisRecordDto;
import com.example.gaming_space.model.AppUser;
import com.example.gaming_space.model.TetrisRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TetrisRecordMapper {

//    @Mapping(target = "recordId", source = "gameRecordDto.recordId")
    @Mapping(target = "score", source = "gameRecordDto.score")
    @Mapping(target = "timePlayed", source = "gameRecordDto.timePlayed")
    @Mapping(target = "tetrisNumber", source = "gameRecordDto.tetrisNumber")
    @Mapping(target = "triplesNumber", source = "gameRecordDto.triplesNumber")
    @Mapping(target = "twosNumber", source = "gameRecordDto.twosNumber")
    @Mapping(target = "linesCleared", source = "gameRecordDto.linesCleared")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "date", expression = "java(java.time.Instant.now())")
    TetrisRecord mapDtoToRecord(TetrisRecordDto gameRecordDto, AppUser user);

    @Mapping(target = "recordId", source = "gameRecord.recordId")
    @Mapping(target = "score", source = "gameRecord.score")
    @Mapping(target = "timePlayed", source = "gameRecord.timePlayed")
    @Mapping(target = "tetrisNumber", source = "gameRecord.tetrisNumber")
    @Mapping(target = "triplesNumber", source = "gameRecord.triplesNumber")
    @Mapping(target = "twosNumber", source = "gameRecord.twosNumber")
    @Mapping(target = "linesCleared", source = "gameRecord.linesCleared")
    @Mapping(target = "username", source = "gameRecord.user.username")
    @Mapping(target = "date", source = "gameRecord.date")
    TetrisRecordDto mapRecordToDto(TetrisRecord gameRecord);

}
