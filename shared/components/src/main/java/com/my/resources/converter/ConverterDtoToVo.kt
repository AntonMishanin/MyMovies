package com.my.resources.converter

interface ConverterDtoToVo<Dto, Vo> {
    fun toValueObject(dto: Dto): Vo

    fun toValueObject(listOfDto: List<Dto>): List<Vo> {
        return listOfDto.map { dto -> toValueObject(dto) }
    }
}