package com.example.data.mapper

import com.example.data.entities.Entity
import com.example.domain.model.DomainData

internal interface Mapper<E : Entity, D : DomainData> {

    fun toData(entity: E): D

    fun toRaw(data: D): E
}