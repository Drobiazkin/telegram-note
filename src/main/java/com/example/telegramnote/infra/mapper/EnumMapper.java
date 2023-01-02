package com.example.telegramnote.infra.mapper;


import com.example.telegramnote.infra.openSearchService.factory.RestClientEnum;
import org.mapstruct.Mapper;

@Mapper
public interface EnumMapper {
    RestClientEnum toRestClientEnum(String restEnum);
}
