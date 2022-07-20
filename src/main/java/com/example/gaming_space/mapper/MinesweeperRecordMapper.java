package com.example.gaming_space.mapper;

import com.example.gaming_space.dto.MinesweeperRecordDto;
import com.example.gaming_space.model.AppUser;
import com.example.gaming_space.model.MinesweeperRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MinesweeperRecordMapper {

//    @Mapping(target = "recordId", source = "gameRecordDto.recordId")
    @Mapping(target = "score", source = "gameRecordDto.score")
    @Mapping(target = "timeSpent", source = "gameRecordDto.timeSpent")
    @Mapping(target = "flagWasUsed", source = "gameRecordDto.flagWasUsed")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "date", expression = "java(java.time.Instant.now())")
    MinesweeperRecord mapDtoToRecord(MinesweeperRecordDto gameRecordDto, AppUser user);

    @Mapping(target = "recordId", source = "gameRecord.recordId")
    @Mapping(target = "score", source = "gameRecord.score")
    @Mapping(target = "timeSpent", source = "gameRecord.timeSpent")
    @Mapping(target = "flagWasUsed", source = "gameRecord.flagWasUsed")
    @Mapping(target = "username", source = "gameRecord.user.username")
    @Mapping(target = "date", source = "gameRecord.date")
    MinesweeperRecordDto mapRecordToDto(MinesweeperRecord gameRecord);

}
